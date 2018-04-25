package com.gamestore.microservice.data.repository;

import com.gamestore.microservice.data.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leonardo.carmona
 */
@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findByCustomerId(Long customerId);

}
