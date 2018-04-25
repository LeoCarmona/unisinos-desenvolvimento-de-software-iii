package com.gamestore.microservice.data.rest.customer;

import com.gamestore.microservice.data.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CustomerCreateRequest {

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public Customer toCustomer() {
        Customer customer = new Customer();

        customer.setEmail(email);
        customer.setName(name);
        customer.setPassword(password);

        return customer;
    }

}
