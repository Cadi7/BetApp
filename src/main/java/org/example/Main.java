package org.example;

import org.example.BetActions.PlaceBet;
import org.example.BetActions.RandomBet;
import org.example.Database.LoadBets;
import org.example.Database.LoadMatches;
import org.example.Helpers.Filters;
import org.example.Helpers.Helpers;
import org.example.MatchActions.AddMatch;
import org.example.MatchActions.DeleteMatch;
import org.example.MatchActions.UpdateMatch;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Math.round;
import static org.example.Helpers.CustomDialogs.showCustomInputDialog;
import static org.example.Helpers.CustomDialogs.showCustomInputDialog2;
// import data from Models.java


public class Main extends JFrame {
    public final JTable table;
    public final JTable table2;

    // Create the buttons
    JButton addButton = new JButton("Adaugă");
    JButton updateButton = new JButton("Actualizează");
    JButton deleteButton = new JButton("Șterge");
    JButton placeBetButton = new JButton("Plasează pariu");

    JButton random_bets = new JButton("Random results");
    JComboBox<String> filtre = new JComboBox<>(new String[]{"Toate", "Castigate", "Pierdute", "In curs"});



    public Main() {
        // Set the title of the window
        setTitle("Lista de pariuri sportive");

        // Create a new JPanel with a BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create the table
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                // set id as hidden
                new String[]{"Cod Meci", "Echipa 1", "Echipa 2", "Cota V1", "Cota X", "Cota V2"}

        ));


        table2 = new JTable();
        table2.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Cod meci", "Meci selectat", "Pariu selectat", "Pariat", "Castig Posibil", "Status",}
        ));

        // Create the scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        panel.add(scrollPane);

        Panel butoane = new Panel();
        butoane.setLayout(new GridLayout(1, 3));
        butoane.add(addButton);
        butoane.add(updateButton);
        butoane.add(deleteButton);
        Panel blank = new Panel();
        blank.setLayout(new GridLayout(1, 1));
        blank.add(new JLabel(" "));
        panel.add(butoane, BorderLayout.SOUTH);
        panel.add(blank);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(table2);
        panel.add(scrollPane2);

        Panel butoane2 = new Panel();
        butoane2.setLayout(new GridLayout(1, 1));
        butoane2.add(placeBetButton);
        butoane2.add(filtre);
        butoane2.add(random_bets);
        panel.add(butoane2, BorderLayout.SOUTH);


        // Set the content pane of the window
        setContentPane(panel);

        // Set the size and visibility of the window
        setSize(1000, 700);
        setVisible(true);

        // load on center
        setLocationRelativeTo(null);

        LoadMatches lm = new LoadMatches();
        LoadBets lb = new LoadBets();


        // Load the data into the table
        lm.loadData(table);
        lb.loadData2(table2);

        Helpers.hiddeColumn(table, 0);
        Helpers.hiddeColumn(table2, 0);

        addButton.addActionListener(e -> {

            String[] inputs = showCustomInputDialog(this, "Adaugă pariu", new String[5]);
            {

                String echipa1 = inputs[0];
                String echipa2 = inputs[1];
                String cotav1Str = inputs[2];
                String cotaxStr = inputs[3];
                String cotav2Str = inputs[4];

                boolean b = inputs[0] == null || inputs[1] == null || inputs[2] == null || inputs[3] == null || inputs[4] == null;
                if (b) {
                    return;
                }
                // Convertiți valorile introduse în numerele corespunzătoare
                double cotav1 = Double.parseDouble(cotav1Str);
                double cotax = Double.parseDouble(cotaxStr);
                double cotav2 = Double.parseDouble(cotav2Str);

                // Adăugați un nou pariu în baza de date
                try {
                    AddMatch.addpariu(echipa1, echipa2, cotav1, cotax, cotav2);

                    // Reîncărcați datele din baza de date și actualizați tabelul
                    lm.loadData(table);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Nu am putut adăuga pariul.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        updateButton.addActionListener(e -> {
            // Obțineți ID-ul rândului selectat
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            int id = (int) table.getValueAt(selectedRow, 0);

            // Obțineți datele din rândul selectat
            String[] initial_input = new String[5];
            initial_input[0] = (String) table.getValueAt(selectedRow, 1);
            initial_input[1] = (String) table.getValueAt(selectedRow, 2);
            initial_input[2] = String.valueOf(table.getValueAt(selectedRow, 3));
            initial_input[3] = String.valueOf(table.getValueAt(selectedRow, 4));
            initial_input[4] = String.valueOf(table.getValueAt(selectedRow, 5));



            String[] inputs = showCustomInputDialog(this, "Modifica pariu", initial_input);
            {
                boolean b = inputs[0] == null || inputs[1] == null || inputs[2] == null || inputs[3] == null || inputs[4] == null;
                if (b) {
                    return;
                }
                String echipa1 = inputs[0];
                String echipa2 = inputs[1];
                String cotav1Str = inputs[2];
                String cotaxStr = inputs[3];
                String cotav2Str = inputs[4];

                // Convertiți valorile introduse în numerele corespunzătoare
                double cotav1 = Double.parseDouble(cotav1Str);
                double cotax = Double.parseDouble(cotaxStr);
                double cotav2 = Double.parseDouble(cotav2Str);

                // Actualizați pariu în baza de date
                try {
                    UpdateMatch.updatepariu(id, echipa1, echipa2, cotav1, cotax, cotav2);

                    // Reîncărcați datele din baza de date și actualizați tabelul
                    lm.loadData(table);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Nu am putut actualiza pariul.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            // Obțineți ID-ul rândului selectat
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            int id = (int) table.getValueAt(selectedRow, 0);


            // Ștergeți pariu din baza de date
            try {
                DeleteMatch.deletepariu(id);

                // Reîncărcați datele din baza de date și actualizați tabelul
                lm.loadData(table);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Nu am putut șterge pariul.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });

        placeBetButton.addActionListener(e -> {
            // Obțineți ID-ul rândului selectat
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            int idmeci = (int) table.getValueAt(selectedRow, 0);

            // Deschidem un dialog modal pentru a permite utilizatorului să introducă datele pentru un nou pariu
            String[] inputs = showCustomInputDialog2(this, "Plasează pariu");
            {
                boolean b = inputs[0] == null || inputs[1] == null;
                if (b) {
                    return;
                }

                String pariu_selectat = inputs[0];
                int suma = Integer.parseInt(inputs[1]);


                // get odd from pariu_selectat
                double cota = switch (pariu_selectat) {
                    case "V1" -> Double.parseDouble(String.valueOf(table.getValueAt(selectedRow, 3)));
                    case "X" -> Double.parseDouble(String.valueOf(table.getValueAt(selectedRow, 4)));
                    case "V2" -> Double.parseDouble(String.valueOf(table.getValueAt(selectedRow, 5)));
                    default -> 0;
                };
                String pariu_cota = pariu_selectat + " - " + cota;
                String meci_selectat = table.getValueAt(selectedRow, 1) + " - " + table.getValueAt(selectedRow, 2);
                String pariat = suma + " MDL";
                String castig_posibil = round(cota * suma) + " MDL";
                String status = "In curs";

                try {
                    PlaceBet.placebet(idmeci, meci_selectat, pariu_cota, pariat, castig_posibil, status);

                    lb.loadData2(table2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Nu am putut adăuga pariul.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        random_bets.addActionListener(e -> {
                    int response = JOptionPane.showConfirmDialog(this, "Sunteți sigur că doriți să generați rezultate aleatorii pentru toate pariurile?",
                            "Generați rezultate aleatorii", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            RandomBet.randombets();
                            lb.loadData2(table2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Nu am putut genera rezultate aleatorii.", "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

        filtre.addActionListener(e -> {
            String selectedItem = Objects.requireNonNull(filtre.getSelectedItem()).toString();
            switch (selectedItem) {
                case "Toate" -> new Filters.FiltreazaToate(table2).actionPerformed(e);
                case "Castigate" -> new Filters.FiltreazaCastigate(table2).actionPerformed(e);
                case "Pierdute" -> new Filters.FiltreazaPierdute(table2).actionPerformed(e);
                case "In curs" -> new Filters.FiltreazaInCurs(table2).actionPerformed(e);
            }
        });
    }
        public static void main (String[]args){
            // Create a new instance of the main window
            Main main = new Main();
        }
    }


