package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.DisadvantagedPerson;

import java.util.UUID;

@Transactional
public interface DisadvantagedPersonRepository extends JpaRepository<DisadvantagedPerson, UUID> {

}