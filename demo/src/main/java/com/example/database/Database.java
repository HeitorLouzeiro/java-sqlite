package com.example.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    private Connection connection = null;
    private static Database INSTANCE = null;
    
    private Database() throws IOException {
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:demo/sample.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            String sql = FileUltils.loadTextFile("demo/src/main/java/com/example/resource/description.sql");
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("Error closing database connection");
            e.printStackTrace();
        }
    }

    public static Database getInstance() throws IOException {
        // Create a singleton instance of the database
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
}
