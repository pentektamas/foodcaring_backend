package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.entities.RestaurantResponsible;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.RestaurantResponsibleRepository;

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
}
