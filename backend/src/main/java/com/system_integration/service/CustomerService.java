package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> findCustomersWhoBought(String productName, String color, String size, String brand) {
        String sql = "SELECT CONCAT(c.first_name, ' ', c.last_name) as name " +
                     "FROM Customer c " +
                     "JOIN `Order` o ON c.id = o.customer_id " +
                     "JOIN OrderItem oi ON o.id = oi.order_id " +
                     "JOIN Product p ON oi.product_id = p.id " +
                     "WHERE p.name = ? AND p.color = ? AND p.size = ? AND p.brand = ?";

        return jdbcTemplate.queryForList(sql, new Object[]{productName, color, size, brand}, String.class);
    }
}
