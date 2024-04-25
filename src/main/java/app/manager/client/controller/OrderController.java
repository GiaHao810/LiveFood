package app.manager.client.controller;

import app.manager.client.model.Order;
import app.manager.client.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitProducts(@RequestBody Order order){
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order Submit Successfully!!!");
    }
}
