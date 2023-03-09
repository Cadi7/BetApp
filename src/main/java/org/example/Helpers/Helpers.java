package org.example.Helpers;

import javax.swing.*;

public class Helpers {

    public static void hiddeColumn(JTable table, int column) {
        table.getColumnModel().getColumn(column).setMaxWidth(0);
        table.getColumnModel().getColumn(column).setMinWidth(0);
        table.getColumnModel().getColumn(column).setPreferredWidth(0);
    }

}
