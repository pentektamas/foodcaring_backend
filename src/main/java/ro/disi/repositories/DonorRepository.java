package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Donor;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface DonorRepository extends JpaRepository<Donor, UUID> {
    Optional<Donor> findByAccountUsername(String username);
}