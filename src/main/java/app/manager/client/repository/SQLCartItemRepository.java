package app.manager.client.repository;

import app.manager.client.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLCartItemRepository extends JpaRepository<CartItem, String> {
}
