package app.manager.client.service;

import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.entity.model.Category;
import app.manager.client.entity.Product;
import app.manager.client.repository.SQLProductRepository;
import app.manager.client.service.implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                    product.setCode(updateProductRequest.getCode());
                    product.setName(updateProductRequest.getName());
                    product.setPrice(updateProductRequest.getPrice());
                    product.setCategory(Category.valueOf(updateProductRequest.getCategory().toUpperCase()));
                    return productRepository.save(product);
                }
        );
    }

    @Override
    public Page<Product> getPage(int page, int size, String category) {
        return productRepository.findAll(PageRequest.of(page, size), Category.valueOf(category));
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        return productRepository.save(Product.builder()
                .code("TEMP-CODE")
                .name(request.getName())
                .price(request.getPrice())
                .category(Category.valueOf(request.getCategory().toUpperCase()))
                .build());
    }


}
