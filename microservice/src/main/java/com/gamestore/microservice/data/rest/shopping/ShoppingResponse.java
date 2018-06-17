package com.gamestore.microservice.data.rest.shopping;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author leonardo.carmona
 */
public class ShoppingResponse {

    /**
     * @author leonardo.carmona
     */
    @Data
    public static class Product {

        private Long          id;
        private String        platform;
        private String        title;
        private BigDecimal    price;
        private String        description;
        private String        image;
        private String        serial;
        private LocalDateTime time;

    }

}
