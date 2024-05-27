package app.manager.client.repository;

import app.manager.client.model.Customer;
import app.manager.client.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByName(String name);
}
