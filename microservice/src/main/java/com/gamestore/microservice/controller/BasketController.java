package com.gamestore.microservice.controller;

import com.gamestore.microservice.data.Sale;
import com.gamestore.microservice.data.rest.basket.BasketAddProductRequest;
import com.gamestore.microservice.data.rest.basket.BasketAddProductResponse;
import com.gamestore.microservice.service.BasketService;
import com.gamestore.microservice.service.CustomerService;
import com.gamestore.microservice.service.SaleService;
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
@RequestMapping(path = "/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasketAddProductResponse addProduct(@Valid @RequestBody BasketAddProductRequest request) {
        return this.basketService.addProduct(request);
    }

    @PostMapping(path = "/finish", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Sale finish() {
        return this.saleService.finish(customerService.getCurrent().getId());
    }

}
