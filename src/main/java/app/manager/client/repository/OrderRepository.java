package app.manager.client.repository;

import app.manager.client.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'date' : { $gte: ?0, $lte: ?1} }")
    List<Order> findByDateBetween(Date startDate, Date endDate);
}
