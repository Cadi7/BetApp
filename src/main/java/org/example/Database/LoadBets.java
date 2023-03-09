package org.example.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;

import static org.example.Database.Con.connect;


public class LoadBets extends Component {

    Con con = new Con();
    public void loadData2(JTable table2) {
        try (Connection con = connect()) {

            // Create a new SQL statement
            assert con != null;
            Statement statement = con.createStatement();

            // Execute the SQL query and get the results
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM meciuri_pariate");

            // Clear the existing data from the table
            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
            model2.setRowCount(0);

            // Loop through the results and add them to the table
            while (resultSet2.next()) {
                model2.addRow(new Object[]{
                        resultSet2.getInt("idmeci"),
                        resultSet2.getString("meci_selectat"),
                        resultSet2.getString("pariu_selectat"),
                        resultSet2.getString("suma"),
                        resultSet2.getString("castig_posibil"),
                        resultSet2.getString("status"),
                });
            }

        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Nu am putut încărca datele din baza de date.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
