package com.gamestore.microservice.data.rest.customer;

import com.gamestore.microservice.data.Customer;
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
public class CustomerCreateResponse {

    private Long   id;
    private String email;

    public CustomerCreateResponse(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
    }

}
