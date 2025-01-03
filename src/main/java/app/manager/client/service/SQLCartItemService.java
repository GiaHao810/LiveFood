package app.manager.client.service;

import app.manager.client.dto.CartItemDTO;
import app.manager.client.entity.CartItem;
import app.manager.client.entity.Product;
import app.manager.client.entity.ShoppingCart;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLCartItemRepository;
import app.manager.client.service.implement.CartItemService;
import app.manager.client.service.implement.ProductService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLCartItemService implements CartItemService {
    private final SQLCartItemRepository repository;
    private final AuthenticationUtil authenticationUtil;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public void addCartItem(CartItemDTO cartItemDTO) {
        ShoppingCart owner_cart = userService.findByUsername(authenticationUtil.getCurrentUsername()).getShoppingCart();
        Product product = productService.findByCode(cartItemDTO.code());
        CartItem cartItem = CartItem.builder()
                .shoppingCart(owner_cart)
                .quantity(cartItemDTO.quantity())
                .product(List.of(product))
                .build();

        save(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return repository.findAll();
    }

    @Override
    public CartItem findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not found Cart Item's ID: " + id));
    }

    @Override
    public void deleteCartItemById(String id) {
        repository.delete(
                findById(id)
        );
    }

    @Override
    public void save(CartItem cartItem) {
        repository.save(cartItem);
    }
}
