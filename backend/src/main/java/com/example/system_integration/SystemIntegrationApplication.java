package com.example.system_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

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
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}