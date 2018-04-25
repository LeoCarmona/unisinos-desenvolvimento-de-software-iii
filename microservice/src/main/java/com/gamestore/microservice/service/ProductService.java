package com.gamestore.microservice.service;

import com.gamestore.microservice.data.Product;
import com.gamestore.microservice.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leonardo.carmona
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> readAll() {
        return this.productRepository.findAll();
    }

}
