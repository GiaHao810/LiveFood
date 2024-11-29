package app.manager.client.service.implement;

import app.manager.client.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ShoppingCartService {
    void save(ShoppingCart cart);
    List<ShoppingCart> getAllCart();
    Optional<ShoppingCart> findById(String id);
    void deleteCart(String id);
//    ShoppingCart updateCart(String id, UpdateUserRequest updateUserRequest);
}
