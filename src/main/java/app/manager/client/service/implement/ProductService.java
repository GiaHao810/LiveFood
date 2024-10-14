package app.manager.client.service.implement;

import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.model.Product;
import app.manager.client.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void save(Product product);
    void deleteProduct(String id);
    List<Product> getAllProduct();
    Optional<Product> findById(String id);
    Optional<Product> findByName(String name);
    Optional<Product> updateProduct(String id, UpdateProductRequest updateProductRequest);
    Page<Product> getPage(int page, int size, String category);
}
