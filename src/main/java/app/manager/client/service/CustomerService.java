package app.manager.client.service;

import app.manager.client.model.Customer;
import app.manager.client.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService
{
    @Autowired
    private final CustomerRepository customerRepository;

    /*
    * Get a Customer List
    * */
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
