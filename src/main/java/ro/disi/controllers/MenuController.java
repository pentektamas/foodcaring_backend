package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.WeeklyMenuDTO;
import ro.disi.services.MenuService;
import ro.disi.services.WeeklyMenuService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/menu")
public class MenuController {
    private final MenuService menuService;
    private final WeeklyMenuService weeklyMenuService;

    @Autowired
    public MenuController(MenuService menuService, WeeklyMenuService weeklyMenuService) {
        this.menuService = menuService;
        this.weeklyMenuService = weeklyMenuService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE', 'ROLE_DISADVANTAGED_PERSON')")
    public ResponseEntity<List<MenuDTO>> getMenus() {
        List<MenuDTO> menuDTOS = menuService.findMenus();
        return new ResponseEntity<>(menuDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE', 'ROLE_DISADVANTAGED_PERSON', 'ROLE_DONOR')")
    public ResponseEntity<Set<MenuDTO>> getAllMenus(){
        List<MenuDTO> menuDTOS = menuService.findMenus();
        List<WeeklyMenuDTO> weeklyMenuDTOS = weeklyMenuService.findWeeklyMenus();

        Set<MenuDTO> allDTOS = new HashSet<>();
        allDTOS.addAll(weeklyMenuDTOS);
        allDTOS.addAll(menuDTOS);

        return new ResponseEntity<>(allDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable("id") UUID id) {
        MenuDTO menuDTO = menuService.findMenuById(id);
        return new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.FOUND);
    }

    @PostMapping(value = "/{restaurantId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> insertMenu(@PathVariable("restaurantId") UUID restaurantId, @Valid @RequestBody MenuDTO menuDTO) {
        UUID uuid = menuService.insertMenu(restaurantId, menuDTO);
        return new ResponseEntity<>(uuid, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteMenu(@PathVariable("id") UUID id) {
        UUID menuId = menuService.deleteMenu(id);
        return new ResponseEntity<>(menuId, HttpStatus.OK);
    }


    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<MenuDTO> updateMenu(@Valid @RequestBody MenuDTO menuDTO) {
        MenuDTO dto = menuService.updateMenu(menuDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
