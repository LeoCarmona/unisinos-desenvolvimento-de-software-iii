package com.gamestore.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author leonardo.carmona
 */
@FeignClient(name = "gamestore-security", url = "http://localhost:${server.port}", decode404 = true)
public interface SecurityClient {

    String CUSTOMER_AUTHORIZATION_HEADER = "Authorization=Basic Y3VzdG9tZXI6WTJ4cFpXNTBPa2dxUXprOWJscExSbVlyY1RsWVh5UnhPV0paYlc1YVRDMUNjMTlTVm5kZg==";

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/oauth/token?grant_type=password&username={username}&password={password}&scope=customer",
            headers = {CUSTOMER_AUTHORIZATION_HEADER},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Map<String, Object>> customerLogin(@PathVariable("username") String username, @PathVariable("password") String password);

}
