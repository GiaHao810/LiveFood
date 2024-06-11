package app.manager.client.service.implement;

import app.manager.client.model.Order;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    void insertOrder(Order order);
    List<Order> getOrder();
    List<Order> findByDateBetween(Date startDate, Date endDate);
}
