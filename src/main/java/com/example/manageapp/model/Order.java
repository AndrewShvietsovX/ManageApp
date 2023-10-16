package com.example.manageapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdTime;

    @Column(name = "is_paid")
    private boolean isPaid;

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdAt) {
        this.createdTime = createdAt;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
