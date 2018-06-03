package com.gamestore.microservice.data.repository.jdbc;

import com.gamestore.microservice.data.rest.basket.BasketProductResponse;
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

    public BasketProductResponse customerProducts(Long customerId) {
        BasketProductResponse response = new BasketProductResponse();

        this.jdbcTemplate.query("select p.*, b.quantity from basket b \n" +
                "inner join product p on b.product_id = p.id\n" +
                "where b.customer_id = " + customerId, rs -> {
            BasketProductResponse.Product product = new BasketProductResponse.Product();

            product.setId(rs.getLong("id"));
            product.setTitle(rs.getString("title"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setDescription(rs.getString("description"));
            product.setImage(rs.getString("image"));
            product.setQuantity(rs.getInt("quantity"));

            response.getProducts().add(product);
        });

        return response;
    }

    public Integer getBasketItemsCount(Long customerId) {
        String   sql  = "select count(*) from basket where customer_id = ?";
        Object[] args = new Object[]{customerId};

        return this.jdbcTemplate.queryForObject(sql, args, Integer.class);
    }

}
