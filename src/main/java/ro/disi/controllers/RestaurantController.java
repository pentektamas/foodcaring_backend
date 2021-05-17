package ro.disi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.services.RestaurantService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE', 'ROLE_DONOR')")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurantList = restaurantService.findAll();
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable("id") UUID id) {
        RestaurantDTO restaurantDTO = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<RestaurantDTO> insertRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO newRestaurantDTO = restaurantService.insertRestaurant(restaurantDTO);
        return new ResponseEntity<>(newRestaurantDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteRestaurant(@PathVariable("id") UUID id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO dto = restaurantService.updateRestaurant(restaurantDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
