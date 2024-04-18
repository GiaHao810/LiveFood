package app.manager.client.controller;

import app.manager.client.dto.ResponseObject;
import app.manager.client.model.Product;
import app.manager.client.model.Role;
//import app.manager.client.model.User;
import app.manager.client.repository.ProductRepository;
//import app.manager.client.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
//    @Autowired
//    private LoginService loginService;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/home")
    public String getHome(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "admin/home";
    }

//    @GetMapping("/login")
//    public String getLogin(Model model){
//        model.addAttribute("user", new User());
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public void login(@ModelAttribute("user") User user){
//        user.setRole(Role.USER);
//
//        ResponseEntity<ResponseObject> response = loginService.login(user);
//
//        if(response.getBody().data() == null) {
////             Login Failed
//        } else {
////            Login Successed
//
//
//        }
//
////        RestTemplate restTemplate = new RestTemplate();
////
////        HttpHeaders headers = new HttpHeaders();
////
////        headers.set("Authorization", "Bearer ");
//
//    }
}
