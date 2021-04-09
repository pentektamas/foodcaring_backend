package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.disi.entities.Restaurant;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
