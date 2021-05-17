package ro.disi.dtos.builders;

import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.entities.Restaurant;

import java.util.stream.Collectors;

public class RestaurantBuilder {

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getLocation(), restaurant.getMenus().stream().map(MenuBuilder::toMenuDTO).collect(Collectors.toSet()));
    }

    public static Restaurant toEntity(RestaurantDTO restaurantDTO) {
        return new Restaurant(restaurantDTO.getName(), restaurantDTO.getLocation(), restaurantDTO.getMenus().stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet()));
    }

    public static Restaurant toEntityWithId(RestaurantDTO restaurantDTO) {
        return new Restaurant(restaurantDTO.getId(), restaurantDTO.getName(), restaurantDTO.getLocation(), restaurantDTO.getMenus().stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet()));
    }
}
