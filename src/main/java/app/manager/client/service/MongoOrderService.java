package app.manager.client.service;

import app.manager.client.model.Order;
import app.manager.client.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoOrderService implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    public void saveOrder(Order order){orderRepository.save(order);}
    public void insertOrder(Order order){orderRepository.insert(order);}
    public List<Order> getOrder() { return orderRepository.findAll();}
}
