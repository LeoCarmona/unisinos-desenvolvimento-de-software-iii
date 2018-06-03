package com.gamestore.microservice.data.rest.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketAddProductRequest {

    private Long    customerId;

    @NotNull
    private Long    productId;

    @NotNull
    @Min(0)
    @Max(3)
    private Integer quantity;

}
