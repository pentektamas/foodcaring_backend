package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.PersonDummyDTO;
import ro.disi.dtos.PersonDummyDetailsDTO;
import ro.disi.dtos.builders.PersonDummyBuilder;
import ro.disi.entities.PersonDummy;
import ro.disi.repositories.PersonDummyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonDummyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDummyService.class);
    private final PersonDummyRepository personDummyRepository;

    @Autowired
    public PersonDummyService(PersonDummyRepository personDummyRepository) {
        this.personDummyRepository = personDummyRepository;
    }

    public List<PersonDummyDTO> findPersons() {
        List<PersonDummy> personList = personDummyRepository.findAll();
        return personList.stream()
                .map(PersonDummyBuilder::toPersonDummyDTO)
                .collect(Collectors.toList());
    }

    public PersonDummyDetailsDTO findPersonById(UUID id) {
        Optional<PersonDummy> prosumerOptional = personDummyRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(PersonDummy.class.getSimpleName() + " with id: " + id);
        }
        return PersonDummyBuilder.toPersonDummyDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDummyDetailsDTO personDTO) {
        PersonDummy person = PersonDummyBuilder.toEntity(personDTO);
        person = personDummyRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

}
