package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getBestSalesMonthThisYear() {
        String sql;

        try {
            ClassPathResource resource = new ClassPathResource("get_best_sales_month.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file", e);
        }

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("month", rs.getString("month"));
            result.put("totalSales", rs.getInt("total_sales_value"));
            return result;
        });
    }

    public List<Map<String, Object>> getTopFiveProducts() {
        String sql;

        try {
            ClassPathResource resource = new ClassPathResource("get_top_five_products.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file: get_top_five_products.sql", e);
        }

        // Execute the SQL query and map the results to a list of maps
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("name", rs.getString("name"));
            result.put("price", rs.getBigDecimal("price"));
            result.put("brand", rs.getString("brand"));
            result.put("amountSold", rs.getInt("amount_sold"));
            return result;
        });
    }

    public List<Map<String, Object>> getOrderForCity(double threshold) {
        String sql;

        try {
            ClassPathResource resource = new ClassPathResource("get_order_for_city.sql");
            sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            // Replace the :threshold placeholder with the actual value
            sql = sql.replace(":threshold", String.valueOf(threshold));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file: get_cities_with_order_value_above.sql", e);
        }

        return jdbcTemplate.queryForList(sql);
    }
}
