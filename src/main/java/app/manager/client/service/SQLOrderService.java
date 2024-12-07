package app.manager.client.service;

import app.manager.client.dto.OrderDTO;
import app.manager.client.dto.response.ResponseObject;
import app.manager.client.entity.Order;
import app.manager.client.entity.OrderItem;
import app.manager.client.entity.Product;
import app.manager.client.entity.User;
import app.manager.client.entity.enums.OrderStatus;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLOrderRepository;
import app.manager.client.service.implement.OrderService;
import app.manager.client.service.implement.ProductService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class SQLOrderService implements OrderService {

    private final SQLOrderRepository sqlOrderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final AuthenticationUtil authenticationUtil;

    @Override
    public void save(List<OrderDTO> orderDTO) {
        User owner = userService.findByUsername(authenticationUtil.getCurrentUsername())
                .orElseThrow( () -> new RuntimeException("Something went wrong!!!"));
        double price = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .orderItem(orderItems)
                .orderDate(LocalDateTime.now())
                .owner(owner)
                .build();

        for (OrderDTO dto : orderDTO) {
            Product product = productService.findByCode(dto.CODE()).get();
            orderItems.add(
                    OrderItem.builder()
                            .quantity(dto.quantity())
                            .order(order)
                            .product(product)
                            .build()
            );
            order.setTotalPrice(price += (product.getPrice() * dto.quantity()));
        }
        sqlOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        sqlOrderRepository.deleteById(findById(id).getId());
    }

    @Override
    public List<Order> getAllOrder() {
        return sqlOrderRepository.findAll();
    }

    @Override
    public Optional<Order> findByOwner(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findByOrderDate(LocalDateTime date) {
        return Optional.empty();
    }

    @Override
    public Order findById(String id) {
        return sqlOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find Order's ID: " + id));
    }

    @Override
    public Optional<Order> findByStatus(OrderStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update() {
        return Optional.empty();
    }
}
