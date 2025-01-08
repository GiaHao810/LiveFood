package app.manager.client.controller.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @GetMapping("/listed")
    public String getDashboard(){
        return "client/listed";
    }
    @GetMapping("/login")
    public String getLogin() {
        return "client/login";
    }
}
