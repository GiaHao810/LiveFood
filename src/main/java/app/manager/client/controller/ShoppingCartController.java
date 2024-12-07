package app.manager.client.controller;

import app.manager.client.dto.ShoppingCartDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.service.implement.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/shoppingcart")
@Validated
@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService service;

    @GetMapping("/")
    public ResponseEntity<?> getAllOrder(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        service.getAllCart())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        return service.findById(id)
                .map(cart -> ResponseEntity.status(HttpStatus.FOUND)
                        .body(new ResponseObject<>(true, cart))
                )
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Cart's ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        service.findById(id)
                .ifPresentOrElse(
                        order -> service.deleteCart(id),
                        () -> {
                            throw new ResourceNotFoundException("Can't find Cart's ID: " + id);
                        }
                );
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody ShoppingCartDTO shoppingCartDTO){
//        service.save(
//
//        );

        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}
