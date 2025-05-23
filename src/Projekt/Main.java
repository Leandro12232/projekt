package Projekt;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GUIProjektVerwaltung gui = new GUIProjektVerwaltung();
            gui.setVisible(true);
        });


    }
}