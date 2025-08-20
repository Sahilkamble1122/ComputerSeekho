package com.example.demo.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DatabaseHealthCheck implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHealthCheck.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting database health check...");
        
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                logger.info("✅ Database connection is healthy");
                logger.info("Database URL: {}", connection.getMetaData().getURL());
                logger.info("Database Product: {}", connection.getMetaData().getDatabaseProductName());
                logger.info("Database Version: {}", connection.getMetaData().getDatabaseProductVersion());
            } else {
                logger.error("❌ Database connection is not valid");
                throw new RuntimeException("Database connection validation failed");
            }
        } catch (SQLException e) {
            logger.error("❌ Database health check failed: {}", e.getMessage());
            logger.error("SQL State: {}", e.getSQLState());
            logger.error("Error Code: {}", e.getErrorCode());
            throw new RuntimeException("Database connection failed", e);
        }
        
        logger.info("Database health check completed successfully");
    }
} 