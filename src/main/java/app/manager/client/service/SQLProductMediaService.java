package app.manager.client.service;

import app.manager.client.entity.ProductMedia;
import app.manager.client.entity.model.Category;
import app.manager.client.repository.SQLProductMediaRepository;
import app.manager.client.service.implement.ProductMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ProductMedia> findAll() {
        return sqlProductMediaRepository.findAll();
    }

    @Override
    public Page<ProductMedia> findWithPSC(int page, int size, String category) {
        return sqlProductMediaRepository.findWithPCS(PageRequest.of(page, size), Category.valueOf(category));
    }
}
