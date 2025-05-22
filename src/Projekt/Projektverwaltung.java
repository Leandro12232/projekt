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

    public void ProjektlisteAnzeigen(){
        System.out.println(projektMap.keySet());

    }

    public ArrayList<Projekt> getAlle(){
        return new ArrayList<>(projektMap.values());
    }

    public void Projektloeschen(String name){
        projektMap.remove(name);
        System.out.println(name+" wurde gelöscht");
        System.out.println(projektMap.containsKey(name));


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

}
