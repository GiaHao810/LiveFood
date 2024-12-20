package app.manager.client.service;

import app.manager.client.dto.request.AddProductRequest;
import app.manager.client.dto.request.UpdateProductRequest;
import app.manager.client.entity.enums.Category;
import app.manager.client.entity.Product;
import app.manager.client.exeption.resource.ResourceExistException;
import app.manager.client.exeption.resource.ResourceNotFoundException;
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
        productRepository.deleteById(findById(id).getId());
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Product's ID: " + id));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Product's Name: " + name));
    }

    @Override
    public Product findByCode(String CODE) {
        return productRepository.findByCode(CODE)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Product's CODE: " + CODE));
    }

    @Override
    public Product updateProduct(String id, UpdateProductRequest updateProductRequest) {
        return productRepository.findById(id).map(
                product -> {
                    product.setCode(updateProductRequest.getCode());
                    product.setName(updateProductRequest.getName());
                    product.setPrice(updateProductRequest.getPrice());
                    product.setCategory(Category.valueOf(updateProductRequest.getCategory().toUpperCase()));
                    return productRepository.save(product);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("..."));
    }

    @Override
    public Page<Product> getPage(int page, int size, String category) {
        return productRepository.findAll(PageRequest.of(page, size), Category.valueOf(category));
    }

    @Override
    public void addProduct(AddProductRequest request) {
        save(Product.builder()
            .code("TEMP-CODE")
            .name(request.getName())
            .price(request.getPrice())
            .category(Category.valueOf(request.getCategory().toUpperCase()))
            .build());
    }


}
