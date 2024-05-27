package app.manager.client.controller;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.OrderRequest;
import app.manager.client.model.Customer;
import app.manager.client.model.Order;
import app.manager.client.model.Product;
import app.manager.client.service.CustomerService;
import app.manager.client.service.OrderService;
import app.manager.client.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                .orderDate(LocalDate.now())
                .productList(newProductList)
                .build()
        );

        return ResponseEntity.ok("Order Submit Successfully!!!");
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrder();
    }
}
