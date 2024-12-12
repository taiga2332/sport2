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

    public void createOrder(String clientName, String username, LocalDateTime dateTime) {
        if (clientName == null || clientName.isEmpty()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім!");
        }
        Order newOrder = new Order();
        newOrder.setClientName(clientName);
        newOrder.setUsername(username);
        newOrder.setDateTime(dateTime);
        newOrder.setStatus("Підтверджено");
        orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
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
