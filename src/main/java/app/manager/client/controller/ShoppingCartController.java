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
    public ResponseEntity<?> getAllShoppingCarts(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        service.getAllCart())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartByID(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                service.findById(id)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable String id) {
        service.deleteCart(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        service.addCart(shoppingCartDTO);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}
