package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.disi.entities.Allergen;

import java.util.UUID;

public interface AllergenRepository extends JpaRepository<Allergen, UUID> {
}
