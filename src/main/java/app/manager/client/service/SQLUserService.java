package app.manager.client.service;

import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.model.User;
import app.manager.client.repository.SQLUserRepository;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SQLUserService implements UserService {
    private final SQLUserRepository userRepository;

    @Override
    public Optional<User> findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsernameOrMail(String username, String mail) {
        return userRepository.findByUsernameOrMail(username, mail);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> searchUsers(String username, String mail) {
        return userRepository.findByUsernameOrMail(username, mail);
    }

    @Override
    public User updateUser(String id, UpdateUserRequest updateUserRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setMail(updateUserRequest.getMail());
                    user.setUsername(updateUserRequest.getUsername());
                    return userRepository.save(user);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Can't find User's ID " + id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
