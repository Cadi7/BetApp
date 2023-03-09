package org.example.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;

import static org.example.Database.Con.connect;

public class LoadMatches extends Component {

    public void loadData(JTable table) {
        try (Connection con = connect()) {


            // Create a new SQL statement
            assert con != null;
            Statement statement = con.createStatement();

            // Execute the SQL query and get the results
            ResultSet resultSet = statement.executeQuery("SELECT * FROM meciuri_disponibile");

            // Clear the existing data from the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            // Loop through the results and add them to the table
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("echipa1"),
                        resultSet.getString("echipa2"),
                        resultSet.getDouble("cotav1"),
                        resultSet.getDouble("cotax"),
                        resultSet.getDouble("cotav2"),
                });
            }

        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nu am putut încărca datele din baza de date.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
