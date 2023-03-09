package org.example.BetActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Database.Con.connect;

public class PlaceBet {

    public static void placebet(Integer idmeci, String meci_selectat, String pariu_selectat, String suma, String castig_posibil, String status) throws SQLException {
        String sql = "INSERT INTO meciuri_pariate(idmeci, meci_selectat, pariu_selectat, suma, castig_posibil, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = connect()) {
            assert con != null;
            PreparedStatement statement = con.prepareStatement(sql);
            {
                statement.setInt(1, idmeci);
                statement.setString(2, meci_selectat);
                statement.setString(3, pariu_selectat);
                statement.setString(4, suma);
                statement.setString(5, castig_posibil);
                statement.setString(6, status);

                statement.executeUpdate();
            }
        }
    }
}
