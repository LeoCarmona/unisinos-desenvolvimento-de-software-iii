package com.gamestore.microservice.data.rest.basket;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonardo.carmona
 */
@Data
public class BasketProductResponse {

    private List<Product> products = new ArrayList<>();

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : this.getProducts()) {
            total = total.add(product.getTotal());
        }

        return total;
    }

    /**
     * @author leonardo.carmona
     */
    @Data
    public static class Product {

        private Long       id;
        private String     title;
        private BigDecimal price;
        private String     description;
        private String     image;
        private Integer    quantity;

        public BigDecimal getTotal() {
            return price.multiply(new BigDecimal(quantity));
        }

    }

}
