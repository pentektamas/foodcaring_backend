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

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<UUID> insertRestaurantResponsible(@RequestBody RestaurantResponsibleDTO restaurantResponsibleDTO) {
        restaurantResponsibleDTO.setPassword(bCryptPasswordEncoder.encode(restaurantResponsibleDTO.getPassword()));
        Restaurant restaurant = restaurantService.getRestaurantByName(restaurantResponsibleDTO.getRestaurantName());
        boolean result = restaurantResponsibleService.insertRestaurantResponsible(RestaurantResponsibleBuilder.toRestaurantResponsible(restaurantResponsibleDTO, restaurant));
        if (result) {
            return new ResponseEntity<>(restaurant.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/responsible/update/{id}")
    public ResponseEntity<UUID> updateRestaurantResponsible(@PathVariable("id") UUID responsibleID, @RequestBody RestaurantResponsibleDTO restaurantResponsibleDTO) {
        Restaurant restaurant = restaurantService.getRestaurantByName(restaurantResponsibleDTO.getRestaurantName());
        UUID updatedResponsibleID = restaurantResponsibleService.updateRestaurantResponsible(responsibleID, RestaurantResponsibleBuilder.toRestaurantResponsible(restaurantResponsibleDTO, restaurant));
        return new ResponseEntity<>(updatedResponsibleID, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/responsible/delete/{id}")
    public ResponseEntity<UUID> deleteRestaurantResponsible(@PathVariable("id") UUID responsibleID) {
        UUID deletedResponsibleID = restaurantResponsibleService.deleteRestaurantResponsible(responsibleID);
        return new ResponseEntity<>(deletedResponsibleID, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/responsibles")
    public ResponseEntity<List<RestaurantResponsibleDTO>> getRestaurantResponsibles() {
        List<RestaurantResponsibleDTO> restaurantResponsibleDTOS = restaurantResponsibleService.getAllRestaurantResponsibles();
        return new ResponseEntity<>(restaurantResponsibleDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/responsible/{id}")
    public ResponseEntity<RestaurantResponsibleDTO> getAllRestaurantResponsibleById(@PathVariable("id") UUID responsibleID) {
        RestaurantResponsibleDTO restaurantResponsibleDTO = restaurantResponsibleService.getRestaurantResponsibleById(responsibleID);
        return new ResponseEntity<>(restaurantResponsibleDTO, HttpStatus.OK);
    }
}
