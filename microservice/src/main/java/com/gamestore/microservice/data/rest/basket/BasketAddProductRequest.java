package com.gamestore.microservice.data.rest.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketAddProductRequest {

    private Long    customerId;
    private Long    productId;
    private Integer quantity;

}
