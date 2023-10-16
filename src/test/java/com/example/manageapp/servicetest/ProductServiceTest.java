package com.example.manageapp.servicetest;

import com.example.manageapp.model.Product;
import com.example.manageapp.repository.ProductRepository;
import com.example.manageapp.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("iPhone X");
        product1.setPrice(999);
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Samsung Galaxy S21");
        product2.setPrice(899);
        product2.setQuantity(5);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertEquals("iPhone X", products.get(0).getName());
        assertEquals("Samsung Galaxy S21", products.get(1).getName());
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(499);
        product.setQuantity(20);
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.addProduct(product);
        assertEquals("Test Product", savedProduct.getName());
    }
}
