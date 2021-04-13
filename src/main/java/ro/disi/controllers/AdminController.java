package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.RestaurantResponsibleDTO;
import ro.disi.dtos.builders.RestaurantResponsibleBuilder;
import ro.disi.entities.Restaurant;
import ro.disi.repositories.ItemRepository;
import ro.disi.repositories.MenuRepository;
import ro.disi.repositories.RestaurantRepository;
import ro.disi.services.RestaurantResponsibleService;
import ro.disi.services.RestaurantService;

@RestController
@CrossOrigin
@Configuration
@RequestMapping(value = "/admin")
public class AdminController {

    private final RestaurantResponsibleService restaurantResponsibleService;
    private final RestaurantService restaurantService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(RestaurantResponsibleService restaurantResponsibleService,
                           ItemRepository itemRepository, MenuRepository menuRepository, RestaurantRepository restaurantRepository, RestaurantService restaurantService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.restaurantResponsibleService = restaurantResponsibleService;
        this.restaurantService = restaurantService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/responsible/insert")
    public ResponseEntity<String> insertRestaurantResponsible(@RequestBody RestaurantResponsibleDTO restaurantResponsibleDTO) {
        restaurantResponsibleDTO.setPassword(bCryptPasswordEncoder.encode(restaurantResponsibleDTO.getPassword()));
        Restaurant restaurant = restaurantService.getRestaurantByName(restaurantResponsibleDTO.getRestaurantName());
        System.out.println("REst is: " + restaurant);
        boolean result = restaurantResponsibleService.insertRestaurantResponsible(RestaurantResponsibleBuilder.toRestaurantResponsible(restaurantResponsibleDTO, restaurant));
        if (result) {
            return new ResponseEntity<>("Restaurant responsible added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The account or the user already exists", HttpStatus.CONFLICT);
        }
    }
}
