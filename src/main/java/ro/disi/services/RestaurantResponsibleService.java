package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.RestaurantResponsibleDTO;
import ro.disi.dtos.builders.RestaurantResponsibleBuilder;
import ro.disi.entities.RestaurantResponsible;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.RestaurantResponsibleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public UUID deleteRestaurantResponsible(UUID responsibleID) {
        Optional<RestaurantResponsible> optionalRestaurantResponsible = restaurantResponsibleRepository.findById(responsibleID);
        if (!optionalRestaurantResponsible.isPresent()) {
            throw new ResourceNotFoundException(RestaurantResponsible.class.getSimpleName() + " with id: " + responsibleID);
        }
        restaurantResponsibleRepository.deleteById(responsibleID);
        return responsibleID;
    }

    public List<RestaurantResponsibleDTO> getAllRestaurantResponsibles() {
        return restaurantResponsibleRepository.findAll().stream()
                .map(RestaurantResponsibleBuilder::toRestaurantResponsibleDTO)
                .collect(Collectors.toList());
    }

    public RestaurantResponsibleDTO getRestaurantResponsibleById(UUID responsibleID) {
        Optional<RestaurantResponsible> optionalRestaurantResponsible = restaurantResponsibleRepository.findById(responsibleID);
        if (!optionalRestaurantResponsible.isPresent()) {
            throw new ResourceNotFoundException(RestaurantResponsible.class.getSimpleName() + " with id: " + responsibleID);
        }
        return RestaurantResponsibleBuilder.toRestaurantResponsibleDTO(optionalRestaurantResponsible.get());
    }
}
