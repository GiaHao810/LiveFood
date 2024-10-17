package app.manager.client.controller.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @GetMapping("/home")
    public String getDashboard(){
        return "client_page/home";
    }
    @GetMapping("/item")
    public String getItem(){
        return "";
    }
    @GetMapping("/test")
    public String get(){
        return "s";
    }
}
