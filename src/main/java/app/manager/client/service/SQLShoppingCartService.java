package app.manager.client.service;

import app.manager.client.dto.CartItemDTO;
import app.manager.client.entity.CartItem;
import app.manager.client.entity.Product;
import app.manager.client.entity.ShoppingCart;
import app.manager.client.entity.User;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLShoppingCartRepository;
import app.manager.client.service.implement.CartItemService;
import app.manager.client.service.implement.ProductService;
import app.manager.client.service.implement.ShoppingCartService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLShoppingCartService implements ShoppingCartService {
    private final SQLShoppingCartRepository repository;
    private final UserService userService;
    private final CartItemService cartItemService;

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
    public void addCart(User owner) {
        save(ShoppingCart.builder()
                .user(owner)
                .quantity(0.0)
                .build());
    }

    @Override
    public void addCart(String user_mail) {
        save(ShoppingCart.builder()
                .user(userService.findByMail(user_mail))
                .quantity(0.0)
                .build());
    }

    @Override
    public void addItemToCart(CartItemDTO cartItemDTO) {
        cartItemService.addCartItem(cartItemDTO);
    }
}
