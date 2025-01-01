package app.manager.client.controller;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.service.implement.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        service.getAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseObject<>(true, service.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(){
//        Code

        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}
