package app.manager.client.service;

import app.manager.client.dto.OrderDTO;
import app.manager.client.entity.Order;
import app.manager.client.entity.OrderItem;
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
        Double price = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .orderItem(orderItems)
                .orderDate(LocalDateTime.now())
                .owner(owner)
                .totalPrice(price)
                .build();

        for(int i = 0; i < orderDTO.size(); i++) {
            orderItems.add(
                    OrderItem.builder()
                            .price(orderDTO.get(i).price())
                            .quantity(orderDTO.get(i).quantity())
                            .order(order)
                            .product(productService.findByCode(orderDTO.get(i).CODE()).get())
                            .build()
            );
            order.setTotalPrice(price += (orderDTO.get(i).price() * orderDTO.get(i).quantity()));
        }
        sqlOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        sqlOrderRepository.deleteById(id);
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
    public Optional<Order> findById(String id) {
        return sqlOrderRepository.findById(id);
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
