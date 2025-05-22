package Projekt;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Projektverwaltung p = new Projektverwaltung();
        ArrayList<Student> studentenliste = new ArrayList<>();
        Student s1 = new Student("L",20241213,1234567);
        studentenliste.add(s1);
        p.Projekthinzufuegen("B",3,20241012,studentenliste);
        p.Projekthinzufuegen("C",1,20241012,studentenliste);
        p.Projekthinzufuegen("A",2,20241012,studentenliste);
        p.NameSortieren();
        p.NoteSortieren();

       // javax.swing.SwingUtilities.invokeLater(() -> {
       //     GUIProjektVerwaltung gui = new GUIProjektVerwaltung();
       //     gui.setVisible(true);
       // });


    }
}