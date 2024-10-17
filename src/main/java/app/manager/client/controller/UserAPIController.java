package app.manager.client.controller;

import app.manager.client.dto.Response;
import app.manager.client.dto.request.AuthenticationRequest;
import app.manager.client.dto.request.RegisterRequest;
import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.model.User;
import app.manager.client.service.AuthenticationService;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final Logger log = LoggerFactory.getLogger(UserAPIController.class);

    /**
     * Get all user informantion
     * @return List of user
     */
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
//        Check if register information is valid
//        Check if register information is existed or not

        if(registerRequest.username().isBlank() ||
                registerRequest.mail().isBlank() ||
                registerRequest.password().isBlank()
        ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseObject("The register information is invalid!!!",
                            "FAIL",
                            registerRequest
                    ));
        }
        if(userService.findByUsernameOrMail(
                registerRequest.username(),
                registerRequest.mail()
        ).isPresent()
        ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseObject("Username or Mail already existed!!!",
                            "FAIL",
                            registerRequest
                    ));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject("User registered successfully!!!",
                        "SUCCESS",
                        authenticationService.register(registerRequest)
                ));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    /**
     * Get user information with ID
     * @param id
     * @return User Information
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUser(@PathVariable String id) {
        return ResponseEntity.ok(
                new ResponseObject("OK",
                        "OK",
                        userService.findById(id))
        );
    }

    /**
     * Send a delete id user
     * @param id
     * @return id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable String id) {
        if(userService.findById(id).isEmpty()){
            return ResponseEntity.status(409)
                    .body(
                            new ResponseObject("Cant find any user with this ID " + id,
                                    "FAIL"
                                    , id
                            )
                    );
        }
        userService.deleteUser(id);
        return ResponseEntity.status(200)
                .body(
                new ResponseObject("User Deleted",
                        "SUCCESS",
                        id)
        );
    }

    /**
     * Search with username or mail
     * @param username
     * @param mail
     * @return
     */
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

    @PutMapping("/updateWithNameAndMail/{id}")
    public ResponseEntity<ResponseObject> updateUserWithNameAndMail(
            @PathVariable(required = true) String id,
            @RequestBody(required = true) UpdateUserRequest updateUserRequest
            ) {

        if(updateUserRequest.username().isBlank() || updateUserRequest.mail().isBlank()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResponseObject("Invalid update data",
                                    "FAIL",
                                    updateUserRequest
                            )
                    );
        }
           Optional<User> userOptional = userService.findById(id);

            if(userOptional.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                new ResponseObject("Cant find any user with this ID " + id,
                                        "FAIL",
                                        id
                                )
                        );
            }

            userService.updateUser(id, updateUserRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                new ResponseObject("User updated",
                        "OK",
                        updateUserRequest)
        );
    }
}
