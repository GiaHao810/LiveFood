package app.manager.client.service;

import app.manager.client.dto.request.UpdateRequest;
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
    private final PasswordEncoder passwordEncoder;

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
    public Optional<User> updateUser(String id, UpdateRequest updateRequest) {
        return userRepository.findById(id).map(
                user -> {
                    user.setMail(updateRequest.mail());
                    user.setUsername(updateRequest.username());
                    return userRepository.save(user);
                }
        );
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
