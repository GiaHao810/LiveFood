package app.manager.client.controller;

import app.manager.client.dto.ResponseObject;
import app.manager.client.model.*;
import app.manager.client.repository.OrderRepository;
import app.manager.client.repository.ProductRepository;
import app.manager.client.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/")
    public String getHome(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        for (Product product : productList){
            System.out.println(product.toString());
        }
        return "home";
    }

    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitProducts(@RequestBody List<Order> order){

        for(Order orders : order){
            orderRepository.save(orders);
        }

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public void login(@ModelAttribute("user") User user){
        user.setRole(Role.USER);

        ResponseEntity<ResponseObject> response = loginService.login(user);

        if(response.getBody().data() == null) {
//             Login Failed
        } else {
//            Login Successed


        }

//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.set("Authorization", "Bearer ");

    }
}
