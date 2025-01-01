package app.manager.client.repository;

import app.manager.client.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLReviewRepository extends JpaRepository<Review, String> {

}
