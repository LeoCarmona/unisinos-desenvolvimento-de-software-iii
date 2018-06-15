package com.gamestore.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @NotNull
    @Column(name = "RESPONSE")
    private String response;

    /**
     * @author leonardo.carmona
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {

        private List<Product> products = new ArrayList<>();

        /**
         * @author leonardo.carmona
         */
        @Data
        public static class Product {

            private Long          id;
            private String        title;
            private BigDecimal    price;
            private String        description;
            private String        image;
            private String        serial;
            private LocalDateTime time;

        }

    }

}
