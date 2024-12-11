package com.example.sport2.service;

import com.example.sport2.entity.Order;
import com.example.sport2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public Order createOrder(String username, LocalDateTime dateTime) {
        Order order = new Order();
        order.setUsername(username);
        order.setDateTime(dateTime);
        order.setStatus("Підтверджено");
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, LocalDateTime newDateTime) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Замовлення не знайдено"));
        order.setDateTime(newDateTime);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
