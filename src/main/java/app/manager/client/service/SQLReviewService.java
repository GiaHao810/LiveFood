package app.manager.client.service;

import app.manager.client.dto.ReviewDTO;
import app.manager.client.entity.Review;
import app.manager.client.entity.User;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLReviewRepository;
import app.manager.client.service.implement.ProductService;
import app.manager.client.service.implement.ReviewService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLReviewService implements ReviewService {
    private final SQLReviewRepository repository;
    private final AuthenticationUtil authenticationUtil;
    private final UserService userService;
    private final ProductService productService;

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
    public void add(ReviewDTO reviewDTO) {
        User owner = userService.findByUsername(authenticationUtil.getCurrentUsername());

        Review review = Review.builder()
                .user(owner)
                .product(productService.findByCode(reviewDTO.getCode()))
                .reviewDate(LocalDateTime.now())
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .build();

        save(review);
    }
}
