package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.DonorDTO;
import ro.disi.dtos.builders.DonorBuilder;
import ro.disi.entities.Donor;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DonorRepository;

import java.util.Optional;

@Service
public class DonorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DonorService.class);

    private final DonorRepository donorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DonorService(DonorRepository donorRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.donorRepository = donorRepository;
    }

    public boolean insertDonor(Donor donor) {
        if (accountRepository.findByUsername(donor.getAccount().getUsername()).isPresent() ||
                donorRepository.findAll().stream().anyMatch(a -> a.equals(donor))) {
            return false;
        } else {
            donorRepository.save(donor);
            return true;
        }
    }

    public DonorDTO getDonorByUsername(String username) {
        Optional<Donor> donorOptional = donorRepository.findByAccountUsername(username);
        if (!donorOptional.isPresent()) {
            LOGGER.error("Donor with username {} was not found in db", username);
            throw new ResourceNotFoundException(Donor.class.getSimpleName() + "with username" + username);
        }
        return DonorBuilder.toDonorDTO(donorOptional.get());
    }
}