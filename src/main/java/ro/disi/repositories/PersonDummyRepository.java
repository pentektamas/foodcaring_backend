package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.disi.entities.PersonDummy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDummyRepository extends JpaRepository<PersonDummy, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    List<PersonDummy> findByName(String name);

    /**
     * Example: Write Custom Query
     */
    @Query(value = "SELECT p " +
            "FROM PersonDummy p " +
            "WHERE p.name = :name " +
            "AND p.age >= 60  ")
    Optional<PersonDummy> findSeniorsByName(@Param("name") String name);

}
