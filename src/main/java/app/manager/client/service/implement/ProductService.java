package app.manager.client.service.implement;

import app.manager.client.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void save(Product product);
    void deleteProduct(String id);
    List<Product> getAllProduct();
    Optional<Product> findById(String id);
}
