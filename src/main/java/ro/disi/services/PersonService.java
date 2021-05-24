package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.PersonDTO;
import ro.disi.dtos.builders.PersonBuilder;
import ro.disi.entities.Person;
import ro.disi.repositories.*;

import java.util.Optional;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final AdminRepository adminRepository;
    private final DisadvantagedPersonRepository disadvantagedPersonRepository;
    private final RestaurantResponsibleRepository restaurantResponsibleRepository;
    private final DonorRepository donorRepository;

    @Autowired
    public PersonService(AdminRepository adminRepository, DisadvantagedPersonRepository disadvantagedPersonRepository, RestaurantResponsibleRepository restaurantResponsibleRepository, DonorRepository donorRepository) {

        this.adminRepository = adminRepository;
        this.disadvantagedPersonRepository = disadvantagedPersonRepository;
        this.restaurantResponsibleRepository = restaurantResponsibleRepository;
        this.donorRepository = donorRepository;
    }

    public PersonDTO findPersonByUsername(String username, String role) {
        Optional<Person> personOptional;
        switch (role){
            case "ADMIN":
                personOptional = adminRepository.findByAccount_Username(username);
                break;
            case "DISADVANTAGED_PERSON":
                personOptional = disadvantagedPersonRepository.findByAccount_Username(username);
                break;
            case "DONOR":
                personOptional = donorRepository.findByAccount_Username(username);
                break;
            case "RESTAURANT_RESPONSIBLE":
                personOptional = restaurantResponsibleRepository.findByAccount_Username(username);
                break;
            default:
                throw new ResourceNotFoundException(Person.class.getSimpleName() + " with role: " + role+" not found");
        }
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with username {} was not found in db", username);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with username: " + username);
        }
        return PersonBuilder.toPerson(personOptional.get());
    }

}
