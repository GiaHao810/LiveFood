package app.manager.client.service.implement;

import app.manager.client.dto.CartItemDTO;
import app.manager.client.entity.ShoppingCart;
import app.manager.client.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {
    void save(ShoppingCart cart);
    List<ShoppingCart> getAllCart();
    ShoppingCart findById(String id);
    ShoppingCart findByUserId();
    void deleteCart(String id);
    void addCart(User owner);
    void addCart(String user_mail);
    void addItemToCart(CartItemDTO cartItemDTO);
}
