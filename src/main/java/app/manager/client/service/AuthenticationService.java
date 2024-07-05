package app.manager.client.service;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AuthenticationRequest;
import app.manager.client.dto.request.RegisterRequest;
import app.manager.client.dto.response.FailAuthenticationResponse;
import app.manager.client.dto.response.SuccessAuthenticationResponse;
import app.manager.client.model.Role;
import app.manager.client.model.User;
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
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Response register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.username())
                .mail(registerRequest.mail())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
        userService.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new SuccessAuthenticationResponse(
                jwtToken,
                jwtService.getExpirationTime()
        );
    }

    public Response authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.mail(),
                            authenticationRequest.password()
                    )
            );
        } catch (AuthenticationException e) {

            logger.error(e.getMessage());

            return new FailAuthenticationResponse(
                    e.getMessage(),
                    "FAILED",
                    authenticationRequest
            );
        }

        var jwtToken = jwtService.generateToken(
                userService.findByMail(authenticationRequest.mail())
                        .orElseThrow()
        );

        return new SuccessAuthenticationResponse(
                jwtToken,
                jwtService.getExpirationTime()
        );
    }
}
