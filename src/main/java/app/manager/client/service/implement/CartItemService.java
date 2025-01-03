package app.manager.client.service.implement;

import app.manager.client.dto.CartItemDTO;
import app.manager.client.entity.CartItem;
import app.manager.client.entity.ShoppingCart;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItemDTO cartItemDTO);
    List<CartItem> getAllCartItem();
    CartItem findById(String id);
    void deleteCartItemById(String id);
    void save(CartItem cartItem);
}
