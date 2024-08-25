package com.system_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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
            try {
                jdbcTemplate.execute("SELECT 1");
                System.out.println("Database connection is successful!");
            } catch (Exception e) {
                System.err.println("Database connection failed: " + e.getMessage());
            }

            // Execute the SQL script from the file
            DataSource dataSource = jdbcTemplate.getDataSource();
            if (dataSource != null) {
                // Execute the SQL script from the file
                ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("queries.sql"));
                System.out.println("SQL script executed successfully!");
            } else {
                System.err.println("Error: DataSource is not configured.");
            }

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