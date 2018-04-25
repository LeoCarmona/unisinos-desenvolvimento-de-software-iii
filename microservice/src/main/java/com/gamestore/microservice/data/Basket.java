package com.gamestore.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @NotNull
    @Min(1)
    @Column(name = "QUANTITY")
    private Integer quantity;

}
