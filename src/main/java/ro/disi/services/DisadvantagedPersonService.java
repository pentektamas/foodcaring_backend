package ro.disi.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.builders.DisadvantagedPersonBuilder;
import ro.disi.dtos.builders.MenuBuilder;
import org.slf4j.LoggerFactory;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Menu;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DisadvantagedPersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public UUID insertDisadvantagedPerson(DisadvantagedPersonDTO disadvantagedPersonDTO){
        DisadvantagedPerson disadvantagedPerson= DisadvantagedPersonBuilder.toEntity(disadvantagedPersonDTO);
        disadvantagedPerson.getAccount().setPassword(bCryptPasswordEncoder.encode(disadvantagedPersonDTO.getPassword()));
        disadvantagedPerson=disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getId();
    }

    public DisadvantagedPersonDTO updateDisadvantagedPerson(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        DisadvantagedPerson disadvantagedPerson = DisadvantagedPersonBuilder.toEntityWithId(disadvantagedPersonDTO);
        Optional<DisadvantagedPerson> itemOptional = disadvantagedPersonRepository.findById(disadvantagedPerson.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", disadvantagedPerson.getId());
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + " with id: " + disadvantagedPerson.getId());
        }
        DisadvantagedPerson updatedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        LOGGER.debug("Menu with id {} was updated in db", disadvantagedPerson.getId());
        return DisadvantagedPersonBuilder.todisadvantagedPersonDTO(disadvantagedPerson);
    }


    public List<DisadvantagedPersonDTO> findDisadvantagedPerson() {
        List<DisadvantagedPerson> disadvantagedPersons = disadvantagedPersonRepository.findAll();
        return disadvantagedPersons.stream()
                .map(DisadvantagedPersonBuilder::todisadvantagedPersonDTO)
                .collect(Collectors.toList());
    }


    public DisadvantagedPersonDTO findDisadvantagedPersonById(UUID uuid) {
        Optional<DisadvantagedPerson> prosumerOptional = disadvantagedPersonRepository.findById(uuid);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + "with id" + uuid);
        }
        return DisadvantagedPersonBuilder.todisadvantagedPersonDTO(prosumerOptional.get());
    }



}