package com.example.manageapp.repository;

import com.example.manageapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.isPaid = :paid AND o.createdTime < :time")
    List<Order> findUnpaidOrdersCreatedBefore(@Param("paid") boolean paid, @Param("time") LocalDateTime time);
}
