package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Person;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface DisadvantagedPersonRepository extends JpaRepository<DisadvantagedPerson, UUID> {

    @Query(value = "DELETE FROM DisadvantagedPerson d where d.id=:uuid")
    @Modifying
    void deleteById(@Param("uuid") UUID uuid);

    Optional<DisadvantagedPerson> findByAccountUsername(String username);
    Optional<Person> findByAccount_Username(String username);

}
