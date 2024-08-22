package app.manager.client.controller;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AuthenticationRequest;
import app.manager.client.dto.request.RegisterRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.model.User;
import app.manager.client.service.AuthenticationService;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final Logger log = LoggerFactory.getLogger(UserAPIController.class);

    @GetMapping("/")
    public ResponseEntity<Response> getAllUser() {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.getAllUser())
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Response> addUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        authenticationService.register(registerRequest))
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        authenticationService.authenticate(authenticationRequest))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUser(@PathVariable String id) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.findById(id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        null)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mail) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.searchUsers(username, mail))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.updateUser(id, updatedUser))
        );
    }

    @PutMapping("/updateWithNameAndMail/{id}")
    public ResponseEntity<ResponseObject> updateUserWithNameAndMail(
            @PathVariable(required = true) String id,
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String mail
            ) {
            User user = userService.findById(id)
                .map(existingUser -> User.builder()
                        .id(id)
                        .mail(mail)
                        .username(username)
                        .role(existingUser.getRole())
                        .password(existingUser.getPassword())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.updateUser(id, user))
        );
    }
}
