package app.manager.client.service;

import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.entity.User;
import app.manager.client.repository.SQLUserRepository;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SQLUserService implements UserService {
    private final SQLUserRepository userRepository;

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find User's Mail: " + mail));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find User's ID " + id));
    }

    @Override
    public User findByUsernameOrMail(String username, String mail) {
        return userRepository.findByUsernameOrMail(username, mail)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find User Information"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Username: " + username));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(findById(id).getId());
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
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
