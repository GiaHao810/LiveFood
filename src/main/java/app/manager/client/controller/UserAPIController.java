package app.manager.client.controller;

import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.entity.User;
import app.manager.client.service.implement.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Validated
public class UserAPIController {
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserAPIController.class);

    /**
     * Get all user informantion
     * @return List of user
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        userService.getAllUser())
        );
    }

    /**
     * Get user information with ID
     * @return User Information
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.status(HttpStatus.FOUND)
                        .body(new ResponseObject<>(true, user))
                )
                .orElseThrow(() -> new ResourceNotFoundException("Can't find User's ID " + id));
    }

    /**
     * Send a delete id user
     * @param id
     * @return id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.findById(id)
                .ifPresentOrElse(
                        user -> userService.deleteUser(id),
                        () ->  {
                            throw new ResourceNotFoundException("Can't find User's with ID " + id);
                        }
                );
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    /**
     * Search with username or mail
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mail) {
        return userService.searchUsers(username, mail)
                .map(user ->
                        ResponseEntity.status(HttpStatus.FOUND).body(
                            new ResponseObject<>(true, user)
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException("Can't find User information"));
    }

    @PutMapping("/updateWithNameAndMail/{id}")
    public ResponseEntity<?> updateUserWithNameAndMail(
            @PathVariable(required = true) String id,
            @Valid @RequestBody(required = true) UpdateUserRequest updateUserRequest
            )
    {
        User user = userService.updateUser(id, updateUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject<>(true, user)
                );
    }
}
