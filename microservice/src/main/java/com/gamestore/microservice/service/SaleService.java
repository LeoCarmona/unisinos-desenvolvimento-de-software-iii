package com.gamestore.microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamestore.microservice.data.Sale;
import com.gamestore.microservice.data.repository.BasketRepository;
import com.gamestore.microservice.data.repository.SaleRepository;
import com.gamestore.microservice.data.repository.jdbc.BasketJdbcRepository;
import com.gamestore.microservice.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author leonardo.carmona
 */
@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketJdbcRepository basketJdbcRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void finish(Long customerId) {
        if (this.basketJdbcRepository.getBasketItemsCount(customerId) == 0) {
            throw new BadRequestException("");
        }

        Sale sale = new Sale();

        sale.setCustomerId(customerId);

        try {
            sale.setResponse(this.objectMapper.writeValueAsString(this.createSaleResponse(customerId)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.saleRepository.save(sale);
        this.jdbcTemplate.execute("delete from basket where customer_id = " + customerId);
    }

    protected List<Sale.Response.Product> createSaleResponse(Long customerId) {
        Sale.Response response = new Sale.Response();

        String sql = "select p.*, b.quantity from basket b\n" +
                "inner join product p on  b.product_id = p.id\n" +
                "where b.customer_id = ?;";

        Object[] args = new Object[]{customerId};
        LocalDateTime now = LocalDateTime.now();

        this.jdbcTemplate.query(sql, args, rs -> {
            int quantity = rs.getInt("quantity");

            for (int i = 0; i < quantity; i++) {
                Sale.Response.Product product = new Sale.Response.Product();

                product.setId(rs.getLong("id"));
                product.setTitle(rs.getString("title"));
                product.setPlatform(rs.getString("platform"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setSerial(UUID.randomUUID().toString());
                product.setTime(now);

                response.getProducts().add(product);
            }
        });

        return response.getProducts();
    }

}
