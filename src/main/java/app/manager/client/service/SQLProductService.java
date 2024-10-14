package app.manager.client.service;

import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.dto.request.UpdateUserRequest;
import app.manager.client.model.Category;
import app.manager.client.model.Product;
import app.manager.client.model.User;
import app.manager.client.repository.SQLProductRepository;
import app.manager.client.service.implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SQLProductService implements ProductService {
    @Autowired
    private final SQLProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> updateProduct(String id, UpdateProductRequest updateProductRequest) {
        return productRepository.findById(id).map(
                product -> {
                    product.setCode(updateProductRequest.code());
                    product.setName(updateProductRequest.name());
                    product.setPrice(updateProductRequest.price());
                    product.setCategory(Category.valueOf(updateProductRequest.category().toUpperCase()));
                    return productRepository.save(product);
                }
        );
    }

    @Override
    public Page<Product> getPage(int page, int size, String category) {
        return productRepository.findAll(PageRequest.of(page, size), Category.valueOf(category));
    }
}
