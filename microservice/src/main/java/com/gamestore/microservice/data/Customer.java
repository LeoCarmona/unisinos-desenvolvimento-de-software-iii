package com.gamestore.microservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author leonardo.carmona
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "PASSWORD")
    private String password;

}
