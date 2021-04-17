package ro.disi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.services.RestaurantResponsibleService;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/restaurant-responsible")
public class RestaurantResponsibleController {

    private final RestaurantResponsibleService restaurantResponsibleService;

    @Autowired
    public RestaurantResponsibleController(RestaurantResponsibleService restaurantResponsibleService) {
        this.restaurantResponsibleService = restaurantResponsibleService;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable("username") String username) {
        RestaurantDTO restaurantDTO = restaurantResponsibleService.getRestaurant(username);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{username}/{restaurant-id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<RestaurantDTO> setRestaurant(@PathVariable("username") String username, @PathVariable("restaurant-id") UUID restaurantId) {
        RestaurantDTO restaurantDTO = restaurantResponsibleService.setRestaurant(username, restaurantId);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

}
