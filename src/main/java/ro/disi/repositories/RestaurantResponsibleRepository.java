package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.RestaurantResponsible;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface RestaurantResponsibleRepository extends JpaRepository<RestaurantResponsible, UUID> {


    Optional<RestaurantResponsible> findByAccount_Username(String username);

}
