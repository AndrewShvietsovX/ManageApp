package com.example.manageapp.controller;

import com.example.manageapp.model.Order;
import com.example.manageapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/place")
    public Order placeOrder(Order order) {
        return orderService.placeOrder(order);
    }

    @DeleteMapping("/deleteUnPaid")
    public void deleteUnpaidOrders() {
        orderService.deleteUnpaidOrders();
    }

    @PostMapping("/{orderId}/markAsPaid")
    public Order markOrderAsPaid(@PathVariable Long orderId) {
        return orderService.markOrderAsPaid(orderId);
    }
}
