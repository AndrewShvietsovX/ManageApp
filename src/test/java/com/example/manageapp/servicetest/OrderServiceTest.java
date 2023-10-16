package com.example.manageapp.servicetest;

import com.example.manageapp.model.Order;
import com.example.manageapp.repository.OrderRepository;
import com.example.manageapp.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Captor
    private ArgumentCaptor<List<Order>> orderListCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setPaid(false);
        order.setCreatedTime(LocalDateTime.now());

        when(orderRepository.save(order)).thenReturn(order);

        Order placedOrder = orderService.placeOrder(order);

        assertNotNull(placedOrder);
        assertEquals(1L, placedOrder.getId());
        assertFalse(placedOrder.isPaid());
    }

    @Test
    public void testMarkOrderAsPaid() {
        Order order = new Order();
        order.setId(1L);
        order.setPaid(false);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order markedOrder = orderService.markOrderAsPaid(1L);

        assertNotNull(markedOrder);
        assertEquals(1L, markedOrder.getId());
        assertTrue(markedOrder.isPaid());
    }
}
