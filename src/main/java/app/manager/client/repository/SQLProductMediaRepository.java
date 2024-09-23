package app.manager.client.repository;

import app.manager.client.dto.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SQLProductMediaRepository extends JpaRepository<ProductMedia, String> {
}
