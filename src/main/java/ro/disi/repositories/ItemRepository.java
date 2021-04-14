package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Item;


import java.util.UUID;

@Transactional
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query(value = "DELETE FROM Item i where i.id=:uuid")
    @Modifying
    void deleteById(@Param("uuid") UUID uuid);
}
