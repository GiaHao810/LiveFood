package app.manager.client.repository;

import app.manager.client.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Configuration
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByMail(String mail);
    Optional<User> findByUsername(String username);
}
