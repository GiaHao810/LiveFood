package app.manager.client.service.implement;

import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService{
    void save(User user);
    List<User> getAllUser();
    User findByUsername(String username);
    User findByMail(String mail);
    User findById(String id);
    User findByUsernameOrMail(String username, String mail);
    void deleteUser(String id);
    User updateUser(String id, UpdateUserRequest updateUserRequest);
}
