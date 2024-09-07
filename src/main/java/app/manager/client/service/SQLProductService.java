package app.manager.client.service;

import app.manager.client.model.Product;
import app.manager.client.repository.SQLProductRepository;
import app.manager.client.service.implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
