package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.dtos.RestaurantResponsibleDTO;
import ro.disi.dtos.builders.RestaurantBuilder;
import ro.disi.dtos.builders.RestaurantResponsibleBuilder;
import ro.disi.entities.Restaurant;
import ro.disi.entities.RestaurantResponsible;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.RestaurantRepository;
import ro.disi.repositories.RestaurantResponsibleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantResponsibleService {

    private final RestaurantResponsibleRepository restaurantResponsibleRepository;
    private final AccountRepository accountRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantResponsibleService(RestaurantResponsibleRepository restaurantResponsibleRepository, AccountRepository accountRepository, RestaurantRepository restaurantRepository) {
        this.restaurantResponsibleRepository = restaurantResponsibleRepository;
        this.accountRepository = accountRepository;
        this.restaurantRepository = restaurantRepository;
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
            throw new ResourceNotFoundException(RestaurantResponsible.class.getSimpleName() + "with id: " + responsibleID);
        }
        RestaurantResponsible foundRestaurantResponsible = optionalRestaurantResponsible.get();
        String existingPassword = foundRestaurantResponsible.getAccount().getPassword();
        foundRestaurantResponsible.setFirstName(restaurantResponsible.getFirstName());
        foundRestaurantResponsible.setLastName(restaurantResponsible.getLastName());
        foundRestaurantResponsible.setLocation(restaurantResponsible.getLocation());
        foundRestaurantResponsible.setPhoneNumber(restaurantResponsible.getPhoneNumber());
        foundRestaurantResponsible.setRestaurant(restaurantResponsible.getRestaurant());
        foundRestaurantResponsible.setAccount(restaurantResponsible.getAccount());
        foundRestaurantResponsible.getAccount().setPassword(existingPassword);
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

    public RestaurantDTO getRestaurant(String username) {
        Optional<RestaurantResponsible> optionalRestaurantResponsible = restaurantResponsibleRepository.findByAccountUsername(username);
        optionalRestaurantResponsible.orElseThrow(() -> new ResourceNotFoundException(RestaurantResponsible.class.getSimpleName() + " with username " + username));
        return RestaurantBuilder.toRestaurantDTO(optionalRestaurantResponsible.get().getRestaurant());
    }

    public RestaurantDTO setRestaurant(String username, UUID restaurantId) {
        Optional<RestaurantResponsible> optionalRestaurantResponsible = restaurantResponsibleRepository.findByAccountUsername(username);
        optionalRestaurantResponsible.orElseThrow(() -> new ResourceNotFoundException(RestaurantResponsible.class.getSimpleName() + " with username " + username));
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);
        foundRestaurant.orElseThrow(() -> new ResourceNotFoundException(Restaurant.class.getSimpleName() + " with id: " + restaurantId));
        optionalRestaurantResponsible.get().setRestaurant(foundRestaurant.get());
        restaurantResponsibleRepository.save(optionalRestaurantResponsible.get());
        return RestaurantBuilder.toRestaurantDTO(optionalRestaurantResponsible.get().getRestaurant());
    }

}
