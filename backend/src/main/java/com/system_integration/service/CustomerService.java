package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> findCustomersWhoBought(String productName, String color, String size, String brand) {
        String sql;
    
        try {
            // Load the SQL file from the resources folder
            ClassPathResource resource = new ClassPathResource("get_customers_who_bought.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file!", e);
        }
    
        // Map Parameters for SQL Query
        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("color", color);
        params.put("size", size);
        params.put("brand", brand);
    
        // Define the RowMapper to map result set rows to a list of customer names
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("name");
    
        // Use NamedParameterJdbcTemplate to execute the query and return as list
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        return namedParameterJdbcTemplate.query(sql, params, rowMapper);
    }
    
    public List<Map<String, Object>> getCustomerPurchaseTotals() {
        String sql;

        try {
            // Load the SQL file from the resources folder
            ClassPathResource resource = new ClassPathResource("get_customer_purchase_totals.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file", e);
        }

        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getCustomers() {
        String sql;

        try {
            ClassPathResource resource = new ClassPathResource("get_customers.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file", e);
        }

        System.out.println("Executing SQL query: " + sql);

        return jdbcTemplate.queryForList(sql);
    }
    
}
