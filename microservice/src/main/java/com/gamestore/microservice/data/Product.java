package com.gamestore.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @DecimalMin("0.00")
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotBlank
    @Column(name = "DESCRIPTION")
    private String description;

    @NotBlank
    @Column(name = "IMAGE")
    private String image;

}
