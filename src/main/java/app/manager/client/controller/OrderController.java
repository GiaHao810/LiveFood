package app.manager.client.controller;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.entity.Order;
import app.manager.client.entity.User;
import app.manager.client.entity.enums.OrderStatus;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.service.implement.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/order")
@Validated
@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<?> getAllOrder(){
        return ResponseEntity.ok(
                new ResponseObject<>("success",
                        orderService.getAllOrder())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        return orderService.findById(id)
                .map(order -> ResponseEntity.status(HttpStatus.FOUND)
                        .body(new ResponseObject<>("success", order))
                )
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Order's ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        orderService.findById(id)
                .ifPresentOrElse(
                        order -> orderService.deleteOrder(id),
                        () -> {
                            throw new ResourceNotFoundException("Can't find Order's ID: " + id);
                        }
                );
        return ResponseEntity.status(200)
                .body(new ResponseObject<>("success"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO){
        orderService.save(
                Order.builder()
                        .orderDate(LocalDateTime.now())
                        .totalPrice(orderDTO.totalPrice())
                        .orderItem(orderDTO.orderItems())
                        .orderStatus(OrderStatus.PENDING)
                        .build()
        );

        return ResponseEntity.status(200)
                .body(new ResponseObject<>("success"));
    }
}
