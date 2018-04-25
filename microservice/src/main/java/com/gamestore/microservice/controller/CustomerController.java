package com.gamestore.microservice.controller;

import com.gamestore.microservice.data.Customer;
import com.gamestore.microservice.data.rest.customer.CustomerCreateRequest;
import com.gamestore.microservice.data.rest.customer.CustomerCreateResponse;
import com.gamestore.microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author leonardo.carmona
 */
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerCreateResponse create(@Valid @RequestBody CustomerCreateRequest request) {
        Customer customer = this.customerService.create(request);

        return new CustomerCreateResponse(customer);
    }

}
