package com.gamestore.microservice.service;

import com.gamestore.microservice.data.repository.jdbc.BasketJdbcRepository;
import com.gamestore.microservice.data.rest.basket.BasketAddProductRequest;
import com.gamestore.microservice.data.rest.basket.BasketAddProductResponse;
import com.gamestore.microservice.data.rest.basket.BasketProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leonardo.carmona
 */
@Service
public class BasketService {

    @Autowired
    private BasketJdbcRepository basketJdbcRepository;

    public BasketAddProductResponse addProduct(BasketAddProductRequest request) {
        this.basketJdbcRepository.setProductQuantity(request.getCustomerId(), request.getProductId(), request.getQuantity());

        BasketAddProductResponse response = new BasketAddProductResponse();

        response.setBasketItems(this.basketJdbcRepository.getBasketItemsCount(request.getCustomerId()));

        return response;
    }

    public BasketProductResponse customerProducts(Long customerId) {
        return this.basketJdbcRepository.customerProducts(customerId);
    }

}
