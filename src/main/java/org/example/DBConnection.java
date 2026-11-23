package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String getRequiredEnv(String name) {
        String value = System.getenv(name);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing environment variable: " + name);
        }

        return value;
    }

    public static Connection getConnection() throws SQLException {

        String url = getRequiredEnv("DB_URL");
        String user = getRequiredEnv("DB_USER");
        String password = getRequiredEnv("DB_PASSWORD");

        return DriverManager.getConnection(url, user, password);
    }
}
