package com.gamestore.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestore.microservice.data.rest.ShoppingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leonardo.carmona
 */
@Service
public class ShoppingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public List<ShoppingResponse.Product> getAllShoppingProductsByCustomerId(Long customerId) {
        String sql = "select response from sale";

        List<ShoppingResponse.Product> response = new ArrayList<>();

        this.jdbcTemplate.query(sql, rs -> {
            try {
                for (Map<String, Object> product : (List<Map<String, Object>>) this.objectMapper.readValue(rs.getString("response"), Object.class)) {
                    ShoppingResponse.Product _product = new ShoppingResponse.Product();

                    _product.setId(Long.valueOf(product.get("id").toString()));
                    _product.setTitle((String) product.get("title"));
                    _product.setPrice(new BigDecimal(product.get("price").toString()));
                    _product.setDescription((String) product.get("description"));
                    _product.setImage((String) product.get("image"));
                    _product.setSerial((String) product.get("serial"));
                    _product.setTime(LocalDateTime.parse(product.get("time").toString()));

                    response.add(_product);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        });

        return response;
    }

}
