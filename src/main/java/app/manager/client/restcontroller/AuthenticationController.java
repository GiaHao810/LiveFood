package app.manager.client.restcontroller;

import app.manager.client.auth.AuthenticationRequest;
import app.manager.client.auth.AuthenticationResponse;
import app.manager.client.auth.RegisterRequest;
import app.manager.client.model.User;
import app.manager.client.service.AuthenticationService;
import app.manager.client.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = authenticationService.signup(registerRequest);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        User authenticatedUser = authenticationService.authenticate(authenticationRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .expiresAt(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(authenticationResponse);
    }
}
