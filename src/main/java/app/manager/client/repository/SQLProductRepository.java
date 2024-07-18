package app.manager.client.repository;

import app.manager.client.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLProductRepository extends JpaRepository<Product, String> {
}
