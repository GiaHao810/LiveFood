package app.manager.client.repository;

import app.manager.client.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
}
