package com.system_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> findCustomersWhoBought(String productName, String color, String size, String brand) {
        StringBuilder sql = new StringBuilder("SELECT CONCAT(c.first_name, ' ', c.last_name) as name " +
                     "FROM Customer c " +
                     "JOIN `Order` o ON c.id = o.customer_id " +
                     "JOIN OrderItem oi ON o.id = oi.order_id " +
                     "JOIN Product p ON oi.product_id = p.id " +
                     "WHERE 1=1");
    
        List<Object> params = new ArrayList<>();
    
        if (productName != null && !productName.trim().isEmpty()) {
            sql.append(" AND p.name = ?");
            params.add(productName);
        }
        if (color != null && !color.trim().isEmpty()) {
            sql.append(" AND p.color = ?");
            params.add(color);
        }
        if (size != null && !size.trim().isEmpty()) {
            sql.append(" AND p.size = ?");
            params.add(size);
        }
        if (brand != null && !brand.trim().isEmpty()) {
            sql.append(" AND p.brand = ?");
            params.add(brand);
        }
    
        // Log the query and parameters
        System.out.println("SQL Query: " + sql.toString());
        System.out.println("Parameters: " + params);
    
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("name");
        
        System.out.println("HOT RELOAD TEST222");
        return jdbcTemplate.query(sql.toString(), rowMapper, params.toArray());
    }
}
