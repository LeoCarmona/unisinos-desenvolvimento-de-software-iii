package com.gamestore.microservice.data.repository;

import com.gamestore.microservice.data.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonardo.carmona
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
