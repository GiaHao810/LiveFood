package app.manager.client.service;

import app.manager.client.dto.ProductMedia;
import app.manager.client.repository.SQLProductMediaRepository;
import app.manager.client.service.implement.ProductMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SQLProductMediaService implements ProductMediaService {
    @Autowired
    SQLProductMediaRepository sqlProductMediaRepository;

    @Override
    public ProductMedia save(ProductMedia productMedia) {
        return sqlProductMediaRepository.save(productMedia);
    }

    @Override
    public ProductMedia findById(String id) {
        return sqlProductMediaRepository.findById(id).get();
    }
}
