package app.manager.client.controller;

import app.manager.client.dto.CartItemDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.entity.User;
import app.manager.client.service.implement.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/shoppingcart")
@Validated
@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        service.getAllCart())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseObject<>(true,
                service.findById(id)
        ));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getByUserID() {
        return ResponseEntity.ok(new ResponseObject<>(true,
                service.findByUserId()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteCart(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody String user_mail){
        service.addCart(user_mail);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add-cart-item")
    public ResponseEntity<?> add(@RequestBody CartItemDTO cartItemDTO) {
        service.addItemToCart(cartItemDTO);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}
