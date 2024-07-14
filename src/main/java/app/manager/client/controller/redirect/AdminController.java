package app.manager.client.controller.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String getDashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/user")
    public String getUserManage(){
        return "admin/user_management";
    }

    @GetMapping("/product")
    public String getProductManage(){
        return "admin/product_management";
    }

    @GetMapping("/invoice")
    public String getInvoiceManage(){
        return "admin/invoice_management";
    }
}
