package com.gamestore.microservice.controller;

import com.gamestore.microservice.data.rest.login.Login;
import com.gamestore.microservice.session.Session;
import com.gamestore.microservice.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author leonardo.carmona
 */
@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private SessionManager sessionManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> customerLogin(@Valid @RequestBody Login login) {
        Session session = this.sessionManager.login(login);

        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(session, HttpStatus.OK);
    }

}
