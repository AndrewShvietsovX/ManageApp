package com.example.manageapp.service;

import com.example.manageapp.model.Order;
import com.example.manageapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order placeOrder(Order order) {
        order.setCreatedTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void deleteUnpaidOrders() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        List<Order> allOrders = orderRepository.findOrdersCreatedBefore(tenMinutesAgo);
        List<Order> unpaidOrders = allOrders.stream()
                .filter(order -> !order.isPaid())
                .collect(Collectors.toList());

        orderRepository.deleteAll(unpaidOrders);
    }

    @Override
    public Order markOrderAsPaid(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setPaid(true);
            return orderRepository.save(order);
        }
        return null;
    }
}
