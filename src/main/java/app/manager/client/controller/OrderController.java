package app.manager.client.controller;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.service.implement.OrderService;
import app.manager.client.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/order")
@Validated
@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllOrder(){
        return ResponseEntity.ok(
                new ResponseObject<>(true,
                        orderService.getAllOrder())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseObject<>(true, orderService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody List<OrderDTO> orderDTO){
        orderService.save(orderDTO);
        return ResponseEntity.status(200)
                .body(new ResponseObject<>(true));
    }
}
