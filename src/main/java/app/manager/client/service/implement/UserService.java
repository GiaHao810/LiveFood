package app.manager.client.service.implement;

import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService{
    void save(User user);
    List<User> getAllUser();
    Optional<User> findByUsername(String username);
    Optional<User> findByMail(String mail);
    Optional<User> findById(String id);
    Optional<User> findByUsernameOrMail(String username, String mail);
    void deleteUser(String id);
    Optional<User> searchUsers(String username, String email);
    Optional<User> updateUser(String id, UpdateUserRequest updateUserRequest);
}
