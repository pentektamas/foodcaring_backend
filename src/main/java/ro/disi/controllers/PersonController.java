package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.PersonDTO;
import ro.disi.dtos.builders.PersonBuilder;
import ro.disi.services.*;
import ro.disi.utils.Role;

import java.security.Principal;

@RestController
@CrossOrigin
@Configuration
public class PersonController {

    private final AdminService adminService;
    private final DisadvantagedPersonService disadvantagedPersonService;
    private final DonorService donorService;
    private final RestaurantResponsibleService restaurantResponsibleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PersonService personService;

    @Autowired
    public PersonController(AdminService adminService, DisadvantagedPersonService disadvantagedPersonService, DonorService donorService, RestaurantResponsibleService restaurantResponsibleService, BCryptPasswordEncoder bCryptPasswordEncoder, PersonService personService) {
        this.adminService = adminService;
        this.disadvantagedPersonService = disadvantagedPersonService;
        this.donorService = donorService;
        this.restaurantResponsibleService = restaurantResponsibleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.personService = personService;
    }

    @RequestMapping(value = "/login")
    public Principal login(Principal principal) {
        return principal;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody PersonDTO personDTO) {
        boolean result = false;
        personDTO.setPassword(bCryptPasswordEncoder.encode(personDTO.getPassword()));
        if (personDTO.getRole().equals(Role.ADMIN)) {
            result = adminService.insertAdmin(PersonBuilder.toAdmin(personDTO));
        }
        if (personDTO.getRole().equals(Role.DISADVANTAGED_PERSON)) {
            result = disadvantagedPersonService.insertDisadvantagedPerson(PersonBuilder.toDisadvantagedPerson(personDTO));
        }
        if (personDTO.getRole().equals(Role.DONOR)) {
            result = donorService.insertDonor(PersonBuilder.toDonor(personDTO));
        }
        if (personDTO.getRole().equals(Role.RESTAURANT_RESPONSIBLE)) {
            result = restaurantResponsibleService.insertRestaurantResponsible(PersonBuilder.toRestaurantResponsible(personDTO));
        }
        if (result) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/person/{role}/{username}")
    public ResponseEntity<PersonDTO> getByUsername(@PathVariable("role") String role, @PathVariable("username") String username) {
        PersonDTO personDTO = personService.findPersonByUsername(username,role);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
}
