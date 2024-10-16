package app.manager.client.repository;

import app.manager.client.dto.ProductMedia;
import app.manager.client.model.Category;
import app.manager.client.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SQLProductMediaRepository extends JpaRepository<ProductMedia, String> {
    @Query("SELECT e FROM ProductMedia e JOIN e.product p WHERE p.category = :category")
    Page<ProductMedia> findWithPCS(Pageable pageable, @Param("category") Category category);
}
