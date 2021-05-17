package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.WeeklyMenuDTO;
import ro.disi.entities.WeeklyMenu;
import ro.disi.services.WeeklyMenuService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/weeklyMenu")
public class WeeklyMenuController {
    private final WeeklyMenuService weeklyMenuService;

    @Autowired
    public WeeklyMenuController(WeeklyMenuService weeklyMenuService) {
        this.weeklyMenuService = weeklyMenuService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<List<WeeklyMenuDTO>> getWeeklyMenus() {
        List<WeeklyMenuDTO> weeklyMenuDTOS = weeklyMenuService.findWeeklyMenus();
        return new ResponseEntity<>(weeklyMenuDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/restaurant/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<List<WeeklyMenuDTO>> getWeeklyMenusByRestaurant(@PathVariable("id") UUID id) {
        List<WeeklyMenuDTO> weeklyMenuDTOS = weeklyMenuService.findWeeklyMenusByRestaurant(id);
        return new ResponseEntity<>(weeklyMenuDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<WeeklyMenuDTO> getWeeklyMenu(@PathVariable("id") UUID id) {
        WeeklyMenuDTO weeklyMenuDTO = weeklyMenuService.findWeeklyMenuById(id);
        return new ResponseEntity<WeeklyMenuDTO>(weeklyMenuDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/{restaurantId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> insertWeeklyMenu(@PathVariable("restaurantId") UUID restaurantId, @Valid @RequestBody WeeklyMenuDTO weeklyMenuDTO) {
        UUID uuid = weeklyMenuService.insertWeeklyMenu(restaurantId, weeklyMenuDTO);
        return new ResponseEntity<>(uuid, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteWeeklyMenu(@PathVariable("id") UUID id) {
        UUID menuId = weeklyMenuService.deleteWeeklyMenu(id);
        return new ResponseEntity<>(menuId, HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<MenuDTO> updateWeeklyMenu(@Valid @RequestBody WeeklyMenuDTO weeklyMenuDTO) {
        MenuDTO dto = weeklyMenuService.updateMenu(weeklyMenuDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
