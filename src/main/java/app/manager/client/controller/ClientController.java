package app.manager.client.controller;

import app.manager.client.dto.ResponseObject;
import app.manager.client.model.Product;
import app.manager.client.model.Role;
import app.manager.client.model.User;
import app.manager.client.repository.ProductRepository;
import app.manager.client.service.LoginService;
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
@RequestMapping("/")
public class ClientController {
    @GetMapping("/home")
    public String getHome(){
        return "client/home";
    }
}
