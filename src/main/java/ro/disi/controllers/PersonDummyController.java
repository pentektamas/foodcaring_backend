package ro.disi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.PersonDummyDTO;
import ro.disi.dtos.PersonDummyDetailsDTO;
import ro.disi.services.PersonDummyService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/personDummy")
public class PersonDummyController {

    private final PersonDummyService personDummyService;

    @Autowired
    public PersonDummyController(PersonDummyService personDummyService) {
        this.personDummyService = personDummyService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDummyDTO>> getPersons() {
        List<PersonDummyDTO> dtos = personDummyService.findPersons();
        for (PersonDummyDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonDummyController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDummyDetailsDTO personDTO) {
        UUID personID = personDummyService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDummyDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDummyDetailsDTO dto = personDummyService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource

}
