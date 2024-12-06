package app.manager.client.repository;

import app.manager.client.entity.enums.Category;
import app.manager.client.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SQLProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT e FROM Product e ORDER BY e.name ASC")
    List<Product> findAll();
    Optional<Product> findByName(String name);
    Optional<Product> findByCode(String CODE);
    @Query("SELECT e FROM Product e WHERE e.category = :category")
    Page<Product> findAll(Pageable pageable, @Param("category") Category category);
}
