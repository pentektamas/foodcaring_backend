package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.PersonDTO;
import ro.disi.services.DisadvantagedPersonService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/disadvantagedPerson")
public class DisadvantagedPersonController {

    private final DisadvantagedPersonService disadvantagedPersonService;
    @Autowired
    public DisadvantagedPersonController(DisadvantagedPersonService disadvantagedPersonService) {
        this.disadvantagedPersonService = disadvantagedPersonService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    @PostMapping
    public ResponseEntity<UUID> insertDisadvantagedPerson(@RequestBody DisadvantagedPersonDTO disadvantagedPersonDTO) {
        UUID id=disadvantagedPersonService.insertDisadvantagedPerson(disadvantagedPersonDTO);
        return new ResponseEntity<UUID>( id,HttpStatus.CREATED);
    }


}
