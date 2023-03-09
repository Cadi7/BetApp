package org.example.Helpers;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CustomDialogs {
    public static String[] showCustomInputDialog(Component parentComponent, String title, String[] initial_input) {
        String[] inputs = new String[5];
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Echipa 1:"));
        JTextField echipa1Field = new JTextField();
        panel.add(echipa1Field);
        panel.add(new JLabel("Echipa 2:"));
        JTextField echipa2Field = new JTextField();
        panel.add(echipa2Field);
        panel.add(new JLabel("Cota V1:"));
        JTextField cotav1Field = new JTextField();
        panel.add(cotav1Field);
        panel.add(new JLabel("Cota X:"));
        JTextField cotaxField = new JTextField();
        panel.add(cotaxField);
        panel.add(new JLabel("Cota V2:"));
        JTextField cotav2Field = new JTextField();
        panel.add(cotav2Field);

        echipa1Field.setText(initial_input[0]);
        echipa2Field.setText(initial_input[1]);
        cotav1Field.setText(initial_input[2]);
        cotaxField.setText(initial_input[3]);
        cotav2Field.setText(initial_input[4]);

        int result = JOptionPane.showConfirmDialog(parentComponent, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            inputs[0] = echipa1Field.getText();
            inputs[1] = echipa2Field.getText();
            inputs[2] = cotav1Field.getText();
            inputs[3] = cotaxField.getText();
            inputs[4] = cotav2Field.getText();
        }
        return inputs;
    }

public static String[] showCustomInputDialog2(Component parentComponent, String title) {
        String[] inputs = new String[5];
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Pariu selectat"));
        JComboBox<String> pariu_selectat = new JComboBox<>(new String[]{"V1", "X", "V2"});
        panel.add(pariu_selectat);
        panel.add(new JLabel("Suma:"));
        JTextField sumaField = new JTextField();
        panel.add(sumaField);
        int result = JOptionPane.showConfirmDialog(parentComponent, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            inputs[0] = Objects.requireNonNull(pariu_selectat.getSelectedItem()).toString();
            inputs[1] = sumaField.getText();
        }
        return inputs;
    }


}
