package com.gamestore.microservice.service;

import com.gamestore.microservice.data.Customer;
import com.gamestore.microservice.data.repository.CustomerRepository;
import com.gamestore.microservice.data.rest.customer.CustomerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leonardo.carmona
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(CustomerCreateRequest request) {
        return this.customerRepository.save(request.toCustomer());
    }

}
