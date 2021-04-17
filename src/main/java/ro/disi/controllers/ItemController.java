package ro.disi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.ItemDTO;
import ro.disi.services.ItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    //PreAuthorize
    public ResponseEntity<List<ItemDTO>> getItems() {
        List<ItemDTO> itemDTOS = itemService.findItems();
        return new ResponseEntity<>(itemDTOS, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> insertItem(@Valid @RequestBody ItemDTO itemDTO) {
        UUID uuid = itemService.insertItem(itemDTO);
        return new ResponseEntity<>(uuid, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<ItemDTO> getItem(@PathVariable("id") UUID itemId) {
        ItemDTO itemDTO = itemService.findItemById(itemId);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteItem(@PathVariable("id") UUID id) {
        UUID itemId = itemService.deleteItem(id);
        return new ResponseEntity<>(itemId, HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<ItemDTO> updateItem(@Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO dto = itemService.updateItem(itemDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
