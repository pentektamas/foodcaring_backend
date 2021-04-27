package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.WeeklyMenu;

import java.util.UUID;

@Transactional
public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, UUID> {
}
