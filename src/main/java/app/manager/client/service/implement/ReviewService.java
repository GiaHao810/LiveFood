package app.manager.client.service.implement;

import app.manager.client.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    void save(Review review);
    List<Review> getAll();
    Review findById(String id);
    void deleteById(String id);
    void add(Review review);
}
