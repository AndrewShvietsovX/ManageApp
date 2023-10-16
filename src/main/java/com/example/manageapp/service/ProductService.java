package com.example.manageapp.service;

import com.example.manageapp.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProducts();

    Product addProduct(Product product);
}
