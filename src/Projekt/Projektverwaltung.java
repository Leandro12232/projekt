package Projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Projektverwaltung {
    private ProjektMap<String, Projekt> projektMap;
    Scanner scanner = new Scanner(System.in);

    public Projektverwaltung() {
        projektMap = new ProjektMap<String, Projekt>();
    }

    public void Projekthinzufuegen(String projektname, int note, int abgabedatum, ArrayList<Student> schuelerliste){
        Projekt projekt = new Projekt(projektname, note, abgabedatum, schuelerliste);

        projektMap.put(projektname, projekt);


    }

    public ArrayList<Projekt> getAlle(){
        return new ArrayList<>(projektMap.values());
    }

    public void Projektloeschen(String projektname){
        projektMap.remove(projektname);
    }

    public void NoteSuche(int note, List<Student> schuelerliste){
        System.out.print("Nach welcher Zahl soll gesucht werden? ");
        int ziel = scanner.nextInt();


        int index = 0;//binaereSearch(schuelerliste, ziel);
        if (index >= 0) {
            System.out.println("Zahl gefunden an Index: " + index);
        } else {
            System.out.println("Zahl nicht gefunden.");
        }
    }


   /* public static int binaereSearch(List<int> liste, int ziel) {
        int links = 0;
        int rechts = liste.size() - 1;

        while (links <= rechts) {
            int mitte = (links + rechts) / 2;
            int wert = liste.get(mitte);

            if (wert == ziel) {
                return mitte;
            } else if (wert < ziel) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }

        return -1;
    }*/

    public void NamenSuche(){
        List<String> keyListe = new ArrayList<>(projektMap.keySet());
        System.out.print("Nach welchem Projekt soll gesucht werden: ");
        String ziel = scanner.next();
        int index = linearSearch(keyListe, ziel);
        if (index >= 0) {
            System.out.println("Dies ist dein Projekt: " + projektMap.get(ziel));
        } else {
            System.out.println("Kein Projekt unter diesem Namen gefunden");
        }
    }

    public static int linearSearch(List liste,String ziel) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).equals(ziel)) {
                return i;
            }
        }
        return -1;
    }

    public void NameSortieren(){
        List<String> keyListe = new ArrayList<>(projektMap.keySet());
        bubbleSortName(keyListe);
        System.out.println(keyListe);

    }

    public static void bubbleSortName(List<String> list) {
        int laenge = list.size();

        for (int i = laenge - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // Elemente tauschen
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public void NoteSortieren(){
        List<Projekt> projekte = new ArrayList<>(projektMap.values());
        bubbleSortNote(projekte); // ✔ richtige Methode
        System.out.println("Projekte sortiert nach Note:");
        for (Projekt p : projekte) {
            System.out.println(p.getProjektname() + " - Note: " + p.getNote());
        }
    }

    public static void bubbleSortNote(List<Projekt> projekte) {
        int laenge = projekte.size();
        for (int i = laenge - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (projekte.get(j).getNote() > projekte.get(j + 1).getNote()) {
                    // Projekte tauschen
                    Projekt temp = projekte.get(j);
                    projekte.set(j, projekte.get(j + 1));
                    projekte.set(j + 1, temp);
                }
            }
        }

    }

}
