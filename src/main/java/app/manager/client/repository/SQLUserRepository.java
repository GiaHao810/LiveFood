package app.manager.client.repository;

import app.manager.client.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SQLUserRepository extends JpaRepository<User, String>{
    @Query("SELECT e FROM User e ORDER BY e.username ASC")
    List<User> findAll();
    Optional<User> findByMail(String mail);
    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    Optional<User> findByUsernameOrMail(String username, String mail);
}
