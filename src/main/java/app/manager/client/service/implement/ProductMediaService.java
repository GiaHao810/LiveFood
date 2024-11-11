package app.manager.client.service.implement;

import app.manager.client.model.ProductMedia;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductMediaService {
    ProductMedia save(ProductMedia productMedia);
    ProductMedia findById(String id);
    List<ProductMedia> findAll();
    Page<ProductMedia> findWithPSC(int page, int size, String category);
}
