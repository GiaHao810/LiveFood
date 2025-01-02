package app.manager.client.service.implement;

import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void save(Product product);
    void deleteProduct(String id);
    List<Product> getAllProduct();
    Product findById(String id);
    Product findByName(String name);
    Product findByCode(String CODE);
    boolean existByCode(String CODE);
    Product updateProduct(String id, UpdateProductRequest updateProductRequest);
    Page<Product> getPage(int page, int size, String category);
    void addProduct(AddProductRequest request);
    String generateUniqueCode(String productName);
    String generateBaseCode(String productName);
}
