package app.manager.client.service;

import app.manager.client.entity.Review;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLReviewRepository;
import app.manager.client.service.implement.ReviewService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLReviewService implements ReviewService {
    private final SQLReviewRepository repository;

    @Override
    public void save(Review review) {
        repository.save(review);
    }

    @Override
    public List<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not found Review's ID: " + id));
    }

    @Override
    public void deleteById(String id) {
        repository.delete(
                findById(id)
        );
    }

    @Override
    public void add(Review review) {

    }
}
