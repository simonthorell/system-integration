package com.system_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class SystemIntegrationApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SystemIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            if (!isTableExists(connection, "Product")) {
                // Execute the scripts only if tables do not exist
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("create_tables.sql"));
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_data.sql"));
                System.out.println("Database setup completed successfully!");
            } else {
                System.out.println("Tables already exist. Skipping database setup.");
            }
        } catch (Exception e) {
            System.err.println("Error during database setup: " + e.getMessage());
        }
    }

    private boolean isTableExists(Connection connection, String tableName) {
        try {
            connection.createStatement().executeQuery("SELECT 1 FROM " + tableName + " LIMIT 1");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}