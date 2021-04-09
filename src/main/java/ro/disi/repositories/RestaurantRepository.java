package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Restaurant;

import java.util.UUID;

@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
