package org.example.MatchActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Database.Con.connect;

public class AddMatch {
    public static void addpariu(String echipa1, String echipa2, double cotav1, double cotax, double cotav2) throws SQLException {
        String sql = "INSERT INTO meciuri_disponibile(echipa1, echipa2, cotav1, cotax, cotav2) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = connect()) {
            assert con != null;
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, echipa1);
            statement.setString(2, echipa2);
            statement.setDouble(3, cotav1);
            statement.setDouble(4, cotax);
            statement.setDouble(5, cotav2);

            statement.executeUpdate();
        }
    }
}
