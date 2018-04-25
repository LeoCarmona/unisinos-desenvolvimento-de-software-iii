package com.gamestore.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestore.microservice.data.Sale;
import com.gamestore.microservice.data.Sale.SaleResponse;
import com.gamestore.microservice.data.repository.BasketRepository;
import com.gamestore.microservice.data.repository.SaleRepository;
import com.gamestore.microservice.data.repository.jdbc.BasketJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author leonardo.carmona
 */
@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketJdbcRepository basketJdbcRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Sale finish(Long customerId) {
        if (this.basketJdbcRepository.getBasketItemsCount(customerId) == 0) {
            throw new RuntimeException();
        }

        Sale sale = new Sale();

        sale.setCustomerId(customerId);
        sale.setDateCreated(LocalDateTime.now());

        Sale.SaleResponse response = new SaleResponse();

        // TODO fazer o negócio retornar o sale response

        return sale;
    }

}
