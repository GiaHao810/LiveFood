package app.manager.client.service.implement;

import app.manager.client.dto.ProductMedia;
import app.manager.client.repository.SQLProductMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ProductMediaService {
    ProductMedia save(ProductMedia productMedia);
    ProductMedia findById(String id);
}
