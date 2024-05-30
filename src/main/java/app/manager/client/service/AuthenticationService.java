package app.manager.client.service;

import app.manager.client.auth.AuthenticationRequest;
import app.manager.client.auth.RegisterRequest;
import app.manager.client.model.Role;
import app.manager.client.model.User;
import app.manager.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.fullname())
                .mail(registerRequest.mail())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    public User authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );

        return userRepository.findByUsername(authenticationRequest.username())
                .orElseThrow();
    }
}
