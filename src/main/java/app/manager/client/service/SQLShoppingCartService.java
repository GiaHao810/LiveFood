package app.manager.client.service;

import app.manager.client.entity.ShoppingCart;
import app.manager.client.repository.SQLShoppingCartRepository;
import app.manager.client.service.implement.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ShoppingCart> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteCart(String id) {
        repository.deleteById(id);
    }
}
