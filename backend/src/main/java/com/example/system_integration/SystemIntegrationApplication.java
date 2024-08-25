package com.example.system_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SystemIntegrationApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SystemIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Execute a simple query to check the connection
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database connection is successful!");

            // Step 1: Create the Clothes Table
            String createTableSql = "CREATE TABLE IF NOT EXISTS clothes (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                    "name VARCHAR(50), " +
                                    "size VARCHAR(10), " +
                                    "color VARCHAR(20), " +
                                    "price DECIMAL(10, 2)" +
                                    ")";
            jdbcTemplate.execute(createTableSql);
            System.out.println("Clothes table created successfully!");

            // Step 2: Insert Items into the Clothes Table
            String insertItemsSql = "INSERT INTO clothes (name, size, color, price) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertItemsSql, "T-shirt", "M", "Red", 19.99);
            jdbcTemplate.update(insertItemsSql, "Jeans", "L", "Blue", 49.99);
            jdbcTemplate.update(insertItemsSql, "Jacket", "XL", "Black", 89.99);
            jdbcTemplate.update(insertItemsSql, "Sneakers", "10", "White", 59.99);
            System.out.println("Items inserted into clothes table successfully!");

            // Step 3: Retrieve and Print the Clothes Items
            String selectItemsSql = "SELECT * FROM clothes";
            List<Map<String, Object>> clothes = jdbcTemplate.queryForList(selectItemsSql);

            for (Map<String, Object> row : clothes) {
                System.out.println("ID: " + row.get("id"));
                System.out.println("Name: " + row.get("name"));
                System.out.println("Size: " + row.get("size"));
                System.out.println("Color: " + row.get("color"));
                System.out.println("Price: $" + row.get("price"));
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

// package com.example.system_integration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.jdbc.core.JdbcTemplate;

// @SpringBootApplication
// public class SystemIntegrationApplication implements CommandLineRunner {

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     public static void main(String[] args) {
//         SpringApplication.run(SystemIntegrationApplication.class, args);
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         try {
//             // Execute a simple query to check the connection
//             jdbcTemplate.execute("SELECT 1");
//             System.out.println("Database connection is successful!");
//         } catch (Exception e) {
//             System.err.println("Database connection failed: " + e.getMessage());
//         }
//     }
// }