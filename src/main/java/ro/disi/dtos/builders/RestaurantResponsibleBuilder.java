package ro.disi.dtos.builders;

import ro.disi.dtos.RestaurantResponsibleDTO;
import ro.disi.entities.Account;
import ro.disi.entities.Restaurant;
import ro.disi.entities.RestaurantResponsible;
import ro.disi.utils.Role;

public class RestaurantResponsibleBuilder {

    public static RestaurantResponsible toRestaurantResponsible(RestaurantResponsibleDTO restaurantResponsibleDTO, Restaurant restaurant) {
        Account restaurantResponsibleAccount = new Account(restaurantResponsibleDTO.getUsername(), restaurantResponsibleDTO.getPassword(), Role.RESTAURANT_RESPONSIBLE);
        return new RestaurantResponsible(restaurantResponsibleDTO.getFirstName(), restaurantResponsibleDTO.getLastName(),
                restaurantResponsibleDTO.getLocation(), restaurantResponsibleDTO.getPhoneNumber(),
                restaurantResponsibleAccount, restaurant);
    }
}
