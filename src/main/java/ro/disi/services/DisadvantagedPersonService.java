package ro.disi.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.builders.DisadvantagedPersonBuilder;
import org.slf4j.LoggerFactory;
import ro.disi.dtos.builders.MenuBuilder;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Menu;
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
    private DonationService donationService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountRepository accountRespository;

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
        Optional<DisadvantagedPerson> itemOptional = disadvantagedPersonRepository.findById(disadvantagedPerson.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", disadvantagedPerson.getId());
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + " with id: " + disadvantagedPerson.getId());
        }
        String existingPassword = itemOptional.get().getAccount().getPassword();
        disadvantagedPerson.getAccount().setPassword(existingPassword);
        disadvantagedPerson.setAllergies(disadvantagedPersonDTO.getAllergies());
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
        Optional<DisadvantagedPerson> disaOptional = disadvantagedPersonRepository.findById(id);
        if (!disaOptional.isPresent()) {
            LOGGER.error("DisadvantagedPerson with id {} was not found in db", id);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + " with id: " + id);
        }

        donationService.cancelAllDonations(disaOptional.get());
        disadvantagedPersonRepository.deleteById(id);
        accountRespository.deleteByUsername(disaOptional.get().getAccount().getUsername());
        return id;
    }

    public DisadvantagedPersonDTO updatePriorityOfDisadvantagedPerson(UUID disadvantagedPersonID, int priority) {
        Optional<DisadvantagedPerson> disadvantagedPersonOptional = disadvantagedPersonRepository.findById(disadvantagedPersonID);
        if (!disadvantagedPersonOptional.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", disadvantagedPersonID);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with id" + disadvantagedPersonID);
        }
        DisadvantagedPerson disadvantagedPerson = disadvantagedPersonOptional.get();
        int newPriority = disadvantagedPerson.getPriority() + priority;
        if (newPriority < 0) {
            newPriority = 0;
        }
        disadvantagedPerson.setPriority(newPriority);
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return DisadvantagedPersonBuilder.toDisadvantagedPersonDtoWithPriority(disadvantagedPerson);
    }

    public List<DisadvantagedPersonDTO> getUnHelpedDisadvantagedPersons(int nrPersons) {
        List<DisadvantagedPersonDTO> sortedDisadvantagedPersons = getSortedDisadvantagedPersons();
        return sortedDisadvantagedPersons.stream().limit(nrPersons).collect(Collectors.toList());
    }

    public DisadvantagedPersonDTO findDisadvantagedPersonByUsername(String username) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        optionalDisadvantagedPerson.orElseThrow(() -> new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + " with username " + username));
        return DisadvantagedPersonBuilder.toDisadvantagedPersonDTO(optionalDisadvantagedPerson.get());
    }

    public Set<MenuDTO> getWishListByUsername(String username) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        if (!optionalDisadvantagedPerson.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", optionalDisadvantagedPerson);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with username" + username);
        }
        return optionalDisadvantagedPerson.get().getWishList().stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toSet());
    }

    public Set<MenuDTO> addWishListForDisadvantagedPersonByUsername(String username, Set<MenuDTO> wishListDTO) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        if (!optionalDisadvantagedPerson.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", optionalDisadvantagedPerson);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with username" + username);
        }
        Set<Menu> wishList = wishListDTO.stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet());
        DisadvantagedPerson disadvantagedPerson = optionalDisadvantagedPerson.get();
        disadvantagedPerson.setWishList(wishList);
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getWishList().stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toSet());
    }

    public Set<MenuDTO> appendMenuToWishListForDisadvantagedPersonByUsername(String username, MenuDTO wishListMenuDTO) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        if (!optionalDisadvantagedPerson.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", optionalDisadvantagedPerson);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with username" + username);
        }
        DisadvantagedPerson disadvantagedPerson = optionalDisadvantagedPerson.get();
        disadvantagedPerson.getWishList().add(MenuBuilder.toEntityWithId(wishListMenuDTO));
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getWishList().stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toSet());
    }

    public Set<MenuDTO> deleteMenuFromWishListByDisadvantagedPersonUsername(String username, MenuDTO wishListMenuDTO) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        if (!optionalDisadvantagedPerson.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", optionalDisadvantagedPerson);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with username" + username);
        }
        DisadvantagedPerson disadvantagedPerson = optionalDisadvantagedPerson.get();
        disadvantagedPerson.getWishList().remove(MenuBuilder.toEntityWithId(wishListMenuDTO));
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getWishList().stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toSet());
    }

    public UUID deleteWishListByDisadvantagedPersonUsername(String username) {
        Optional<DisadvantagedPerson> optionalDisadvantagedPerson = disadvantagedPersonRepository.findByAccountUsername(username);
        if (!optionalDisadvantagedPerson.isPresent()) {
            LOGGER.error("Disadvantaged person with id {} was not found in db", optionalDisadvantagedPerson);
            throw new ResourceNotFoundException(DisadvantagedPerson.class.getSimpleName() + "with username" + username);
        }
        DisadvantagedPerson disadvantagedPerson = optionalDisadvantagedPerson.get();
        disadvantagedPerson.setWishList(new HashSet<>());
        disadvantagedPerson = disadvantagedPersonRepository.save(disadvantagedPerson);
        return disadvantagedPerson.getId();
    }
}
