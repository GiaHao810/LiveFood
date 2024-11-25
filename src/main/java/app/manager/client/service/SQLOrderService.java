package app.manager.client.service;

import app.manager.client.entity.Order;
import app.manager.client.entity.User;
import app.manager.client.entity.enums.OrderStatus;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLOrderRepository;
import app.manager.client.service.implement.OrderService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SQLOrderService implements OrderService {

    private final SQLOrderRepository sqlOrderRepository;
    private final UserService userService;
    private final AuthenticationUtil authenticationUtil;

    @Override
    public void save(Order order) {
        String username = authenticationUtil.getCurrentUsername();

        userService.findByUsername(username)
                        .ifPresentOrElse(
                                user -> {
                                    order.setOwner(user);
                                    sqlOrderRepository.save(order);
                                }, () -> {
                                    throw new ResourceNotFoundException("Can't find User's Name: " + username);
                                }
                        );
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
