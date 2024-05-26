package app.manager.client.service;

import app.manager.client.model.Product;
import app.manager.client.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){return productRepository.findAll();}
    public void save(Product product){productRepository.save(product);}
}
