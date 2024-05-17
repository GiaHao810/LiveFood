package app.manager.client.controller;

import app.manager.client.model.Order;
import app.manager.client.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitProducts(@RequestBody Order order){
        LocalDate currDate = LocalDate.now();

        order.setOrderDate(dateFormatter.format(currDate));

        orderService.saveOrder(order);
        return ResponseEntity.ok("Order Submit Successfully!!!");
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrder();
    }
}
