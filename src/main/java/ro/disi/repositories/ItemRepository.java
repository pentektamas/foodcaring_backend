package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.disi.entities.Item;


import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
