package org.example;


import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (Exception ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn = connect();
        if (conn != null) {
            System.out.println("Connected to the database!");
        }
    }
}
