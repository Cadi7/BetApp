package org.example.Database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Con {

    public static final String URL = "jdbc:postgresql://localhost:5432/pariuri";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1234";

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
        return null;
    }

}