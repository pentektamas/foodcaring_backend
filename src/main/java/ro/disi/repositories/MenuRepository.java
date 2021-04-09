package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.disi.entities.Menu;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
