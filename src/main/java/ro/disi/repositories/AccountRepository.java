package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Account;

import java.util.Optional;
import java.util.UUID;


@Transactional
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUsername(String username);
}
