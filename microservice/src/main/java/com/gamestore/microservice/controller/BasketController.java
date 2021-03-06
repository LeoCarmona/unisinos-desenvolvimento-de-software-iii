package com.gamestore.microservice.controller;

import com.gamestore.microservice.data.Sale;
import com.gamestore.microservice.data.rest.basket.BasketAddProductRequest;
import com.gamestore.microservice.data.rest.basket.BasketAddProductResponse;
import com.gamestore.microservice.data.rest.basket.BasketProductResponse;
import com.gamestore.microservice.service.BasketService;
import com.gamestore.microservice.service.CustomerService;
import com.gamestore.microservice.service.SaleService;
import com.gamestore.microservice.session.Session;
import com.gamestore.microservice.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SessionManager sessionManager;

    @PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasketAddProductResponse addProduct(@Valid @RequestBody BasketAddProductRequest request, HttpServletRequest httpServletRequest) {
        this.sessionManager.checkAuthorization(httpServletRequest, session -> session.getCustomer().getId() == request.getCustomerId());

        return this.basketService.addProduct(request);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BasketProductResponse customerProducts(HttpServletRequest httpServletRequest) {
        return this.basketService.customerProducts(this.sessionManager.checkAuthorization(httpServletRequest).getCustomer().getId());
    }

    @PostMapping(path = "/finish", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void finish(HttpServletRequest httpServletRequest) {
        Session session = this.sessionManager.checkAuthorization(httpServletRequest);

        this.saleService.finish(session.getCustomer().getId());
    }

}
