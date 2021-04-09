package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DisadvantagedPersonRepository;

@Service
public class DisadvantagedPersonService {

    private final DisadvantagedPersonRepository disadvantagedPersonRepository;
    private final AccountRepository accountRepository;

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
}