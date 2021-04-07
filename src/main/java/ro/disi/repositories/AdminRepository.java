package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Admin;

import java.util.UUID;

@Transactional
public interface AdminRepository extends JpaRepository<Admin, UUID> {

}