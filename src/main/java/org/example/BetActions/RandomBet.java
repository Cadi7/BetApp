package org.example.BetActions;

import java.sql.*;
import java.util.Random;

import static org.example.Database.Con.connect;

public class RandomBet {
    public static void randombets() throws SQLException {
        // Load the PostgreSQL JDBC driver
        try (Connection con = connect()) {
            assert con != null;
            // Create a new SQL statement
            Statement statement = con.createStatement();

            // Execute the SQL query to select all ongoing matches
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meciuri_pariate WHERE status like 'In curs'");

            // Loop through the results and update the match results randomly
            while (resultSet.next()) {
                // Generate a random result for the match
                String[] results = {"Pierdut", "Castigat"};
                String randomResult = results[new Random().nextInt(results.length)];

                // Update the match result in the database
                int matchId = resultSet.getInt("idmeci");
                PreparedStatement updateStatement = con.prepareStatement("UPDATE meciuri_pariate SET status = ? WHERE idmeci = ?");
                updateStatement.setString(1, randomResult);
                updateStatement.setInt(2, matchId);
                updateStatement.executeUpdate();
                updateStatement.close();

            }
        }
}
}
