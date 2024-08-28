package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getProductsPerCategory() {
        String sql;

        try {
            // Load the SQL script directly from the resources folder
            ClassPathResource resource = new ClassPathResource("get_products_per_category.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file: get_products_per_category.sql", e);
        }

        // Log the query
        System.out.println("SQL Query: " + sql);

        // Execute the SQL query and return the results as a list of maps
        return jdbcTemplate.queryForList(sql);
    }
}
