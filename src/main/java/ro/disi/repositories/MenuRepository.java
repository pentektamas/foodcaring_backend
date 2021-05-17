package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.disi.entities.Menu;
import ro.disi.entities.Restaurant;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

@Transactional
public interface MenuRepository extends JpaRepository<Menu, UUID> {
    ArrayList<Menu> findAllByRestaurant(Restaurant restaurant);
}
