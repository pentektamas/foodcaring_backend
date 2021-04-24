package ro.disi.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.builders.DisadvantagedPersonBuilder;
import org.slf4j.LoggerFactory;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DisadvantagedPersonRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisadvantagedPersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);
    private final DisadvantagedPersonRepository disadvantagedPersonRepository;
    private final AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DisadvantagedPersonService(DisadvantagedPersonRepository disadvantagedPersonRepository, AccountRepository accountRepository) {
        this.disadvantagedPersonRepository = disadvantagedPersonRepository;
        this.accountRepository = accountRepository;
    }

    public boolean insertDisadvantagedPerson(DisadvantagedPerson disadvantagedPerson) {
        if (accountRepository.findByUsername(disadvantagedPerson.getAccount().getUsername()).isPresent() ||
                disadvantagedPersonRepository.findAll().stream().anyMatch(a -> a.equals(disadvantagedPerson))) {
            return false;
        } else {
            disadvantagedPersonRepository.save(disadvantagedPerson);
            return true;
        }
    }

    public UUID insertDisadvantagedPerson(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        DisadvantagedPerson disadvantagedPerson = DisadvantagedPersonBuilder.toEntity(disadvantagedPersonDTO);
        disadvantagedPerson.getAccount().setPassword(bCryptPasswordEncoder.encode(disadvantagedPersonDTO.getPassword()));
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getId();
    }

    public DisadvantagedPersonDTO updateDisadvantagedPerson(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        DisadvantagedPerson disadvantagedPerson = DisadvantagedPersonBuilder.toEntityWithId(disadvantagedPersonDTO);
        String password = bCryptPasswordEncoder.encode(disadvantagedPerson.getAccount().getPassword());
        disadvantagedPerson.getAccount().setPassword(password);
        Optional<DisadvantagedPerson> itemOptional = disadvantagedPersonRepository.findById(disadvantagedPerson.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", disadvantagedPerson.getId());
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + " with id: " + disadvantagedPerson.getId());
        }
        DisadvantagedPerson updatedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        LOGGER.debug("Disadvantaged person with id {} was updated in db", disadvantagedPerson.getId());
        return DisadvantagedPersonBuilder.toDisadvantagedPersonDTO(disadvantagedPerson);
    }


    public List<DisadvantagedPersonDTO> findDisadvantagedPerson() {
        List<DisadvantagedPerson> disadvantagedPersons = disadvantagedPersonRepository.findAll();
        return disadvantagedPersons.stream()
                .map(DisadvantagedPersonBuilder::toDisadvantagedPersonDTO)
                .collect(Collectors.toList());
    }


    public DisadvantagedPersonDTO findDisadvantagedPersonById(UUID uuid) {
        Optional<DisadvantagedPerson> prosumerOptional = disadvantagedPersonRepository.findById(uuid);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with id" + uuid);
        }
        return DisadvantagedPersonBuilder.toDisadvantagedPersonDTO(prosumerOptional.get());
    }

    public List<DisadvantagedPersonDTO> getSortedDisadvantagedPersons() {
        List<DisadvantagedPerson> disadvantagedPersons = disadvantagedPersonRepository.findAll();
        disadvantagedPersons.sort(Comparator.comparingInt(DisadvantagedPerson::getPriority).reversed());
        return disadvantagedPersons.stream()
                .map(DisadvantagedPersonBuilder::toDisadvantagedPersonDtoWithPriority)
                .collect(Collectors.toList());
    }

    public UUID deleteDisadvantagedPerson(UUID id) {
        Optional<DisadvantagedPerson> menuOptional = disadvantagedPersonRepository.findById(id);
        if (!menuOptional.isPresent()) {
            LOGGER.error("DisadvantagedPerson with id {} was not found in db", id);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + " with id: " + id);
        }
        disadvantagedPersonRepository.deleteById(id);
        return id;
    }

    public DisadvantagedPersonDTO updatePriorityOfDisadvantagedPerson(UUID disadvantagedPersonID, int priority) {
        Optional<DisadvantagedPerson> disadvantagedPersonOptional = disadvantagedPersonRepository.findById(disadvantagedPersonID);
        if (!disadvantagedPersonOptional.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", disadvantagedPersonID);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with id" + disadvantagedPersonID);
        }
        DisadvantagedPerson disadvantagedPerson = disadvantagedPersonOptional.get();
        disadvantagedPerson.setPriority(disadvantagedPerson.getPriority() + priority);
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return DisadvantagedPersonBuilder.toDisadvantagedPersonDtoWithPriority(disadvantagedPerson);
    }

    public List<DisadvantagedPersonDTO> getUnHelpedDisadvantagedPersons(int nrPersons) {
        List<DisadvantagedPersonDTO> sortedDisadvantagedPersons = getSortedDisadvantagedPersons();
        return sortedDisadvantagedPersons.stream().limit(nrPersons).collect(Collectors.toList());
    }
}