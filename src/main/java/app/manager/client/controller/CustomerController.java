package app.manager.client.controller;

import app.manager.client.model.Customer;
import app.manager.client.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /*
    * API to get all customer by a list
    * */
    @GetMapping("/getAll")
    public List<Customer> getAllCustomer(){
        return customerService.getAll();
    }
}
