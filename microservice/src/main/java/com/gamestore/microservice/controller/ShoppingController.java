package com.gamestore.microservice.controller;

import com.gamestore.microservice.data.rest.shopping.ShoppingResponse;
import com.gamestore.microservice.service.ShoppingService;
import com.gamestore.microservice.session.Session;
import com.gamestore.microservice.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author leonardo.carmona
 */
@RestController
@RequestMapping(path = "/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private SessionManager sessionManager;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ShoppingResponse.Product> shopping(HttpServletRequest httpServletRequest) {
        Session session = this.sessionManager.checkAuthorization(httpServletRequest);

        return this.shoppingService.getAllShoppingProductsByCustomerId(session.getCustomer().getId());
    }

}
