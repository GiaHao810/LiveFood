package app.manager.client.service;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AuthenticationRequest;
import app.manager.client.dto.request.RegisterRequest;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.entity.model.Role;
import app.manager.client.entity.User;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Response register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .mail(registerRequest.getMail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userService.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new Response(jwtToken);
    }

    public Response authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return new Response(e.getMessage());
        }

        var jwtToken = jwtService.generateToken(
                userService.findByUsername(authenticationRequest.getUsername())
                        .orElseThrow(() -> new ResourceNotFoundException("User does not exist"))
        );

        return new Response(jwtToken);
    }
}
