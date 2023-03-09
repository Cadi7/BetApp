package org.example.MatchActions;

import org.example.Database.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Database.Con.connect;

public class UpdateMatch {

    public static void updatepariu(int id, String echipa1, String echipa2, double cotav1, double cotax, double cotav2) throws SQLException {
        String sql = "UPDATE meciuri_disponibile SET echipa1 = ?, echipa2 = ?, cotav1 = ?, cotax = ?, cotav2 = ? WHERE id = ?";

        try (Connection con = connect()) {
            assert con != null;
            PreparedStatement statement = con.prepareStatement(sql);
            {
                statement.setInt(6, id);
                statement.setString(1, echipa1);
                statement.setString(2, echipa2);
                statement.setDouble(3, cotav1);
                statement.setDouble(4, cotax);
                statement.setDouble(5, cotav2);
            }
            statement.executeUpdate();
        }
    }
}
