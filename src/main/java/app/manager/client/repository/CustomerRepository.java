package app.manager.client.repository;

import app.manager.client.model.Customer;
import app.manager.client.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
