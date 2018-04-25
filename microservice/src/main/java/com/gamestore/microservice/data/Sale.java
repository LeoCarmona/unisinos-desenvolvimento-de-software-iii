package com.gamestore.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
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

    @NotNull
    @DecimalMin("0.00")
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @NotNull
    @Column(name = "DATE_CREATED", updatable = false)
    private LocalDateTime dateCreated;

    /**
     * @author leonardo.carmona
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SaleResponse {

        private List<Product> products = new ArrayList<>();

        /**
         * @author leonardo.carmona
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Product {

            private Long       id;
            private String     title;
            private BigDecimal price;
            private Integer    quantity;
            private String     description;
            private String     image;

            public BigDecimal getTotal() {
                return this.price.multiply(new BigDecimal(quantity));
            }

        }

    }

}
