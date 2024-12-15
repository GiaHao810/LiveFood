package app.manager.client.service;

import app.manager.client.dto.ShoppingCartDTO;
import app.manager.client.entity.ShoppingCart;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLShoppingCartRepository;
import app.manager.client.service.implement.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLShoppingCartService implements ShoppingCartService {
    private final SQLShoppingCartRepository repository;

    @Override
    public void save(ShoppingCart cart) {
        repository.save(cart);
    }

    @Override
    public List<ShoppingCart> getAllCart() {
        return repository.findAll();
    }

    @Override
    public ShoppingCart findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Cart's ID: " + id));
    }

    @Override
    public void deleteCart(String id) {
        repository.deleteById(findById(id).getId());
    }

    @Override
    public void addCart(ShoppingCart cart) {

    }
}
