package com.gamestore.microservice.session;

import lombok.Data;

/**
 * @author leonardo.carmona
 */
@Data
public class Session {

    private String   token;
    private Customer customer;

    /**
     * @author leonardo.carmona
     */
    @Data
    public static class Customer {

        private Long   id;
        private String email;
        private String name;

    }

}
