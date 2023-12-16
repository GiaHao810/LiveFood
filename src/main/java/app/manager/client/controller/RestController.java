package app.manager.client.controller;

import app.manager.client.auth.AuthenticationRequest;
import app.manager.client.auth.AuthenticationResponse;
import app.manager.client.auth.RegisterRequest;
import app.manager.client.configuration.AuthenticationService;
import app.manager.client.dto.ResponseObject;
import app.manager.client.model.User;
import app.manager.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {
    private Logger log = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterRequest registerRequest
            ) {
        Optional<User> iUser = userRepository.findByEmail(registerRequest.email());

        if(iUser.isEmpty()){
            AuthenticationResponse response = authenticationService.register(registerRequest);

            log.info(response.toString());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("User Created", response.token())
            );
        } else {
            log.info("User existed");

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Error", null)
            );
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
            ) {
        Optional<User> iUser = userRepository.findByEmail(authenticationRequest.email());

        if(iUser.isEmpty()){

            log.info("User is not in database");

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Error", null)
            );
        } else {
            AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

            log.info(authenticationResponse.toString());

            if(authenticationResponse.token() == null) {
                log.info("Bad Credentials");

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Bad Credentials", authenticationResponse.token())
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("User Authenticated", authenticationResponse.token())
            );
        }
    }
}
