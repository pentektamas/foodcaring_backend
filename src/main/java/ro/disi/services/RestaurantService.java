package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.dtos.builders.MenuBuilder;
import ro.disi.dtos.builders.RestaurantBuilder;
import ro.disi.entities.Menu;
import ro.disi.entities.Restaurant;
import ro.disi.repositories.MenuRepository;
import ro.disi.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public RestaurantDTO insertRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantBuilder.toEntity(restaurantDTO);
        return RestaurantBuilder.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO findRestaurantById(UUID id) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(id);
        foundRestaurant.orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist!"));
        return RestaurantBuilder.toRestaurantDTO(foundRestaurant.get());
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurantName);
        if (!restaurantOptional.isPresent()) {
            throw new ResourceNotFoundException(Restaurant.class.getSimpleName() + " with name: " + restaurantName);
        }
        return restaurantOptional.get();
    }

    public void deleteRestaurant(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantDTO.getId());
        foundRestaurant.orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist!"));
        Restaurant restaurant = foundRestaurant.get();
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setName(restaurantDTO.getName());
        Set<Menu> oldMenus = restaurant.getMenus();
        Set<Menu> newMenus = restaurantDTO.getMenus().stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet());
        for (Menu menu : newMenus) {
            if (menu.getId() == null) {
                menuRepository.save(menu);
            }
        }
        oldMenus.removeAll(oldMenus.stream().filter((menu) -> !newMenus.contains(menu)).collect(Collectors.toSet()));
        oldMenus.addAll(newMenus);
        return RestaurantBuilder.toRestaurantDTO(restaurantRepository.save(foundRestaurant.get()));
    }


    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        if (restaurantList.isEmpty()) {
            LOGGER.info("There are no restaurants available!");
        } else {
            LOGGER.info("The restaurant list was retrieved!");
        }
        return restaurantList.stream().map(RestaurantBuilder::toRestaurantDTO).collect(Collectors.toList());
    }
}
