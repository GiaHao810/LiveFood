package app.manager.client.controller;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.OrderRequest;
import app.manager.client.model.Order;
import app.manager.client.model.Product;
import app.manager.client.service.implement.CustomerService;
import app.manager.client.service.implement.OrderService;
import app.manager.client.service.implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CustomerService customerService;

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(@RequestBody @NotNull OrderRequest orderRequest){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");

        List<Product> productList = productService.findAll();
        List<Product> newProductList = new ArrayList<>();
        double subTotal = 0.0;

        for(OrderDTO orderDTO : orderRequest.orderDTO()){
            for(Product product : productList){
                if(orderDTO.id().equals(product.getId())){
                    subTotal += product.getPrice();
                    newProductList.add(product);
                    break;
                }
            }
        }

        orderService.insertOrder(
                Order.builder()
                .customer((orderRequest.customer().getName().isEmpty())
                        ? customerService.findByName("Guest")
                        : customerService.findByName(orderRequest.customer().getName())
                )
                .totalPrice(subTotal + (Math.round((subTotal * 0.1) * Math.pow(10, 1)) / Math.pow(10, 1)))
                .subTotal(subTotal)
                .disCount(0.0)
                .orderDate(Date.from(Instant.now()))
                .productList(newProductList)
                .build()
        );

        return ResponseEntity.ok("Order Submit Successfully!!!");
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrder();
    }

    @GetMapping("/getOrderChartData")
    public ResponseEntity<String> getOrderChartData(){
        Date startDate = new Date(2024 - 1900, 5 - 1, 26);
        Date endDate = new Date(2024 - 1900, 6 - 1, 6);

        List<Order> list = orderService.findByDateBetween(startDate, endDate);

        for (Order or : list) {
            System.out.print(or.toString());
        }
        return ResponseEntity.ok("A");
    }
}
