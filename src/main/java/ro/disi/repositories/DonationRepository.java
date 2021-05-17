package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Donation;
import ro.disi.entities.Donor;

import java.util.List;
import java.util.UUID;

@Transactional
public interface DonationRepository extends JpaRepository<Donation, UUID> {

    List<Donation> findAllByDisadvantagedPersonListContains(DisadvantagedPerson disadvantagedPerson);

    List<Donation> findAllByDonor(Donor donor);
}
