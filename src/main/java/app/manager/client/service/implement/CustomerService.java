package app.manager.client.service.implement;

import app.manager.client.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService{
    public List<Customer> getAll();
    public Customer findByName(String name);
}
