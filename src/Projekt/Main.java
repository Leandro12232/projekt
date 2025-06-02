package Projekt;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GUIProjektVerwaltung gui = new GUIProjektVerwaltung();  // GUI-Objekt erstellen
            gui.setVisible(true);  // GUI sichtbar machen
        });
    }
}