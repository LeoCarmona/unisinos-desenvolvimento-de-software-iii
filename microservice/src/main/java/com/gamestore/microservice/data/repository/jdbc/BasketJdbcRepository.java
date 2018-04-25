package com.gamestore.microservice.data.repository.jdbc;

import com.gamestore.microservice.data.rest.basket.BasketAddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author leonardo.carmona
 */
@Repository
public class BasketJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setProductQuantity(Long customerId, Long productId, Integer quantity) {
        String sql;

        if (quantity > 0) {
            sql = "insert into basket (customer_id, product_id, quantity) values (" + customerId + ", " + productId + ", " + quantity + ")" +
                    " on conflict (customer_id, product_id) do update set quantity = " + quantity + ";";
        } else {
            sql = "delete from basket where customer_id = " + customerId + " and product_id = " + productId;
        }

        this.jdbcTemplate.execute(sql);
    }

    public Integer getBasketItemsCount(Long customerId) {
        String   sql  = "select count(*) from basket where customer_id = ?";
        Object[] args = new Object[]{customerId};

        return this.jdbcTemplate.queryForObject(sql, args, Integer.class);
    }

}
