package app.manager.client.service;

import app.manager.client.auth.AuthenticationRequest;
import app.manager.client.auth.AuthenticationResponse;
import app.manager.client.auth.RegisterRequest;
import app.manager.client.model.Role;
import app.manager.client.model.User;
import app.manager.client.repository.UserRepository;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.fullname())
                .mail(registerRequest.mail())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
        userService.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .expiresAt(jwtService.getExpirationTime())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.username(),
                            authenticationRequest.password()
                    )
            );
        } catch (AuthenticationException e){
            logger.error(e.toString());
            return AuthenticationResponse.builder()
                    .token(null)
                    .build();
    }
        var jwtToken = jwtService.generateToken(
                userService.findByUsername(authenticationRequest.username())
                        .orElseThrow()
        );

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .expiresAt(jwtService.getExpirationTime())
                .build();
    }
}
