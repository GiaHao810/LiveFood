package app.manager.client.service.implement;

import app.manager.client.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService{
    Optional<User> findByMail(String mail);
    Optional<User> findByUsername(String username);
    void save(User user);
}
