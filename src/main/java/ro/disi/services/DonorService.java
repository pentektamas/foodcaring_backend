package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.entities.Donor;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.DonorRepository;

@Service
public class DonorService {

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
}