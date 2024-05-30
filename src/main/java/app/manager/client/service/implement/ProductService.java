package app.manager.client.service.implement;

import app.manager.client.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> findAll();
    public void save(Product product);
}
