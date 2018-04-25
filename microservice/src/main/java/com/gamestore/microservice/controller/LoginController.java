package com.gamestore.microservice.controller;

import com.gamestore.microservice.client.SecurityClient;
import com.gamestore.microservice.data.rest.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author leonardo.carmona
 */
@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private SecurityClient securityClient;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, Object>> customerLogin(@Valid @RequestBody Login login) {
        TokenEndpoint endpoint = new TokenEndpoint();


        return this.securityClient.customerLogin(login.getUsername(), login.getPassword());
    }

}
