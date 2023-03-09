package org.example.Helpers;

import org.example.Database.Con;
import org.example.Database.LoadBets;
import org.example.Database.LoadMatches;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static org.example.Database.Con.connect;

public class Filters extends Component {
    static LoadBets lb = new LoadBets();

    public static void filterData(String filter, JTable table) {
        // Clear the existing data from the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (Connection con = connect()) {

            // Create a new SQL statement
            assert con != null;
            Statement statement = con.createStatement();

            // Define the SQL query based on the selected filter
            String query = switch (filter) {
                case "Toate" -> "SELECT * FROM meciuri_pariate";
                case "Castigat" -> "SELECT * FROM meciuri_pariate WHERE status LIKE 'Castigat'";
                case "Pierdut" -> "SELECT * FROM meciuri_pariate WHERE status LIKE 'Pierdut'";
                case "In curs" -> "SELECT * FROM meciuri_pariate WHERE status LIKE 'In curs'";
                default -> "";
            };

            // Execute the SQL query and get the results
            ResultSet resultset_r = statement.executeQuery(query);

            // Loop through the results and add them to the table
            while (resultset_r.next()) {
                model.addRow(new Object[]{
                        resultset_r.getInt("idmeci"),
                        resultset_r.getString("meci_selectat"),
                        resultset_r.getString("pariu_selectat"),
                        resultset_r.getString("suma"),
                        resultset_r.getString("castig_posibil"),
                        resultset_r.getString("status"),
                });
            }


        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Nu am putut încărca datele din baza de date.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static class FiltreazaToate implements ActionListener {
        private final JTable table;

        public FiltreazaToate(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            lb.loadData2(table);
        }
    }

    public static class FiltreazaCastigate implements ActionListener {
        private final JTable table;

        public FiltreazaCastigate(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            filterData("Castigat", table);
        }
    }

    public static class FiltreazaPierdute implements ActionListener {
        private final JTable table;

        public FiltreazaPierdute(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            filterData("Pierdut", table);
        }
    }


    public static class FiltreazaInCurs implements ActionListener {
        private final JTable table;

        public FiltreazaInCurs(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            filterData("In curs", table);
        }
    }


}
