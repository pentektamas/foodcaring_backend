package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.disi.dtos.MenuDTO;
import ro.disi.services.MenuService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    //preauthorize
    public ResponseEntity<List<MenuDTO>> getMenus(){
        List<MenuDTO> menuDTOS = menuService.findMenus();
        return new ResponseEntity<>(menuDTOS, HttpStatus.OK);
    }
}
