package com.gamestore.microservice.data.repository;

import com.gamestore.microservice.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonardo.carmona
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
