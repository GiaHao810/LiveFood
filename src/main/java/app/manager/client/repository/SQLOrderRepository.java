package app.manager.client.repository;

import app.manager.client.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLOrderRepository extends JpaRepository<Order, String> {
}
