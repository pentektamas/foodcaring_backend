package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Admin;
import ro.disi.entities.Person;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Person> findByAccount_Username(String username);
}