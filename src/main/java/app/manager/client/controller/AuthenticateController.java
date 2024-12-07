package app.manager.client.controller;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AuthenticationRequest;
import app.manager.client.dto.request.RegisterRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceExistException;
import app.manager.client.service.AuthenticationService;
import app.manager.client.service.implement.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authentication")
@RestController
@RequiredArgsConstructor
public class AuthenticateController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                authenticationService.register(registerRequest)
        ));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                authenticationService.authenticate(authenticationRequest)
        ));
    }
}
