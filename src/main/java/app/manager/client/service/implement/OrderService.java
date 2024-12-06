package app.manager.client.service.implement;

import app.manager.client.dto.OrderDTO;
import app.manager.client.entity.Order;
import app.manager.client.entity.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    void save(List<OrderDTO> order);
    void deleteOrder(String id);
    List<Order> getAllOrder();
    Optional<Order> findByOwner(String username);
    Optional<Order> findByOrderDate(LocalDateTime date);
    Optional<Order> findById(String id);
    Optional<Order> findByStatus(OrderStatus status);
    Optional<Order> update();
}
