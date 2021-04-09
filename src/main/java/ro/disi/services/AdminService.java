package ro.disi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.entities.Admin;
import ro.disi.repositories.AccountRepository;
import ro.disi.repositories.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.adminRepository = adminRepository;
    }

    public boolean insertAdmin(Admin admin) {
        if (accountRepository.findByUsername(admin.getAccount().getUsername()).isPresent() ||
                adminRepository.findAll().stream().anyMatch(a -> a.equals(admin))) {
            return false;
        } else {
            adminRepository.save(admin);
            return true;
        }
    }
}