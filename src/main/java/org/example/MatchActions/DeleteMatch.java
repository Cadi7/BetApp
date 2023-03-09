package org.example.MatchActions;

import org.example.Database.Con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.Database.Con.connect;

public class DeleteMatch {

    public static void deletepariu(int id) throws SQLException {
        String sql = "DELETE FROM meciuri_disponibile WHERE id = ?";

        try (Connection con = connect()) {
            assert con != null;
            PreparedStatement statement = con.prepareStatement(sql);
            {
                statement.setInt(1, id);

                statement.executeUpdate();
            }
        }
    }
}
