package Projekt;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Projektverwaltung projekt = new Projektverwaltung();

        projekt.Projekthinzufuegen("Leandro", 1, 20040510, List.of(new Student("Hans", 20041005,55667778), new Student("Peter",20070213,667778)));
        projekt.Projekthinzufuegen("Leandr", 1, 20040511, List.of(new Student("Hans", 20041005, 122345), new Student("Peter",20070213, 690677)) );

        projekt.ProjektlisteAnzeigen();
        projekt.NamenSuche();


    }
}