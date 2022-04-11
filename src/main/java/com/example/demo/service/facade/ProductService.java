package com.example.demo.service.facade;

import com.example.demo.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product addProduct(Product product);

    Page<Product> findAll(Pageable pageable);

}
