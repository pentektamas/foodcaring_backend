package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.services.DisadvantagedPersonService;

import javax.validation.Valid;
import java.util.List;
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
        UUID id = disadvantagedPersonService.insertDisadvantagedPerson(disadvantagedPersonDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<DisadvantagedPersonDTO> updateDisadvantagedPerson(@Valid @RequestBody DisadvantagedPersonDTO disadvantagedPersonDTO) {
        DisadvantagedPersonDTO dto = disadvantagedPersonService.updateDisadvantagedPerson(disadvantagedPersonDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<List<DisadvantagedPersonDTO>> getDisadvantagedPersons() {
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOS = disadvantagedPersonService.findDisadvantagedPerson();
        return new ResponseEntity<>(disadvantagedPersonDTOS, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<DisadvantagedPersonDTO> getDisadvantagedPerson(@PathVariable("id") UUID id) {
        DisadvantagedPersonDTO disadvantagedPersonDTO = disadvantagedPersonService.findDisadvantagedPersonById(id);
        return new ResponseEntity<>(disadvantagedPersonDTO, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteMenu(@PathVariable("id") UUID id) {
        UUID disadvantagedPerson = disadvantagedPersonService.deleteDisadvantagedPerson(id);
        return new ResponseEntity<>(disadvantagedPerson, HttpStatus.OK);
    }

    @GetMapping(value = "/sorted")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<List<DisadvantagedPersonDTO>> getSortedDisadvantagedPersons() {
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOS = disadvantagedPersonService.getSortedDisadvantagedPersons();
        return new ResponseEntity<>(disadvantagedPersonDTOS, HttpStatus.OK);
    }

    @PutMapping(value = "/priority/{id}/{priority}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<DisadvantagedPersonDTO> updatePriorityOfDisadvantagedPerson(@PathVariable("id") UUID id, @PathVariable("priority") int priority) {
        DisadvantagedPersonDTO disadvantagedPersonDTO = disadvantagedPersonService.updatePriorityOfDisadvantagedPerson(id, priority);
        return new ResponseEntity<>(disadvantagedPersonDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/unhelped/{nrPersons}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE','ROLE_DONOR')")
    public ResponseEntity<List<DisadvantagedPersonDTO>> getUnHelpedDisadvantagedPersons(@PathVariable("nrPersons") int nrPersons) {
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOs = disadvantagedPersonService.getUnHelpedDisadvantagedPersons(nrPersons);
        return new ResponseEntity<>(disadvantagedPersonDTOs, HttpStatus.OK);
    }
}
