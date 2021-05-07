package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Donation;

import java.util.UUID;

@Transactional
public interface DonationRepository extends JpaRepository<Donation, UUID> {
}
