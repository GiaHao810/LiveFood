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
    public ResponseEntity<?> getAll() {
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
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseObject<>(true, userService.findById(id)));
    }

    /**
     * Send a delete id user
     * @param id
     * @return id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    /**
     * Search with username or mail
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchByUsernameOrMail(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String mail) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseObject<>(true, userService.findByUsernameOrMail(username, mail)));
    }

    @PutMapping("/updateWithNameAndMail/{id}")
    public ResponseEntity<?> updateWithNameAndMail(
            @PathVariable(required = true) String id,
            @Valid @RequestBody(required = true) UpdateUserRequest updateUserRequest
            )
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject<>(true,
                        userService.updateUser(id, updateUserRequest)
                        )
                );
    }
}
