package com.example.manageapp.service;

import com.example.manageapp.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getAllOrders();

    Order placeOrder(Order order);

    void deleteUnpaidOrders();

    Order markOrderAsPaid(Long orderId);
}
