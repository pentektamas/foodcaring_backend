package ro.disi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.disi.entities.Account;

import java.util.Optional;
import java.util.UUID;


@Transactional
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUsername(String username);

    @Query(value = "DELETE FROM Account d where d.username=:username")
    @Modifying
    void deleteByUsername(@Param("username") String username);

}
