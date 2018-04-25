package com.gamestore.microservice.data.repository;

import com.gamestore.microservice.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonardo.carmona
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByEmail(String email);

}
