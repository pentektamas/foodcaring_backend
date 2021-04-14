package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.entities.RestaurantResponsible;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.RestaurantResponsibleRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantResponsibleService {

    private final RestaurantResponsibleRepository restaurantResponsibleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RestaurantResponsibleService(RestaurantResponsibleRepository restaurantResponsibleRepository, AccountRepository accountRepository) {
        this.restaurantResponsibleRepository = restaurantResponsibleRepository;
        this.accountRepository = accountRepository;
    }

    public boolean insertRestaurantResponsible(RestaurantResponsible restaurantResponsible) {
        if (accountRepository.findByUsername(restaurantResponsible.getAccount().getUsername()).isPresent() ||
                restaurantResponsibleRepository.findAll().stream().anyMatch(a -> a.equals(restaurantResponsible))) {
            return false;
        } else {
            restaurantResponsibleRepository.save(restaurantResponsible);
            System.out.println(restaurantResponsible.getId());
            return true;
        }
    }

    public UUID updateRestaurantResponsible(UUID responsibleID, RestaurantResponsible restaurantResponsible) {
        Optional<RestaurantResponsible> optionalRestaurantResponsible = restaurantResponsibleRepository.findById(responsibleID);
        if (!optionalRestaurantResponsible.isPresent()) {
            throw new ResourceNotFoundException(RestaurantResponsible.class + "with id: " + responsibleID);
        }
        RestaurantResponsible foundRestaurantResponsible = optionalRestaurantResponsible.get();
        foundRestaurantResponsible.setFirstName(restaurantResponsible.getFirstName());
        foundRestaurantResponsible.setLastName(restaurantResponsible.getLastName());
        foundRestaurantResponsible.setLocation(restaurantResponsible.getLocation());
        foundRestaurantResponsible.setPhoneNumber(restaurantResponsible.getPhoneNumber());
        foundRestaurantResponsible.setRestaurant(restaurantResponsible.getRestaurant());
        foundRestaurantResponsible.setAccount(restaurantResponsible.getAccount());
        restaurantResponsibleRepository.save(foundRestaurantResponsible);
        return foundRestaurantResponsible.getId();
    }
}
