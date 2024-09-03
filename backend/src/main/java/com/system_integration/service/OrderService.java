package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void placeOrder(int customerId, List<Map<String, Object>> products) {
        String insertOrderSql = "INSERT INTO `Order` (customer_id, order_date) VALUES (?, CURDATE())";
        String insertOrderItemSql = "INSERT INTO OrderItem (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try {
            // Start a transaction
            jdbcTemplate.execute("START TRANSACTION");
    
            // Insert the order and retrieve the order ID
            jdbcTemplate.update(insertOrderSql, customerId);
            Integer orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    
            // Insert order items
            for (Map<String, Object> product : products) {
                Integer productId = Integer.parseInt(product.get("product_id").toString());
                Integer quantity = Integer.parseInt(product.get("quantity").toString());
    
                jdbcTemplate.update(insertOrderItemSql, orderId, productId, quantity);
            }
    
            // Commit the transaction
            jdbcTemplate.execute("COMMIT");
        } catch (Exception e) {
            jdbcTemplate.execute("ROLLBACK");
            throw new RuntimeException("Failed to create order: " + e.getMessage());
        }
    }
}



