package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.entities.Restaurant;
import ro.disi.repositories.RestaurantRepository;

import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean insertRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findById(restaurant.getId()).isPresent()) {
            return false;
        } else {
            restaurantRepository.save(restaurant);
            return true;
        }
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantName);
        if (!restaurantOptional.isPresent()) {
            throw new ResourceNotFoundException(Restaurant.class.getSimpleName() + " with name: " + restaurantName);
        }
        return restaurantOptional.get();
    }
}