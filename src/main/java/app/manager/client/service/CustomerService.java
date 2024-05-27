package app.manager.client.service;

import app.manager.client.model.Customer;
import app.manager.client.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Customer findByName(String name){
        return customerRepository.findByName(name);
    }
}
