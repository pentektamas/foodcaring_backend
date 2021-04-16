package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.builders.DisadvantagedPersonBuilder;
import ro.disi.entities.Account;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DisadvantagedPersonRepository;

import java.util.UUID;

@Service
public class DisadvantagedPersonService {

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


}