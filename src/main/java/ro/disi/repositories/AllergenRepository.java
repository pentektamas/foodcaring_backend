package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Allergen;

import java.util.UUID;

@Transactional
public interface AllergenRepository extends JpaRepository<Allergen, UUID> {
}
