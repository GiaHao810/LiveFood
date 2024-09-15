package app.manager.client.repository;

import app.manager.client.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SQLProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT e FROM Product e ORDER BY e.name ASC")
    List<Product> findAll();
    Optional<Product> findByName(String name);
}
