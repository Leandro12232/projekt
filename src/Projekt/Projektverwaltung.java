package Projekt;

import java.util.ArrayList;

public class Projektverwaltung {
    // Eigene Map (ähnlich HashMap) zur Verwaltung von Projekten, Schlüssel: Projektname
    private ProjektMap<String, Projekt> projektMap;

    // Konstruktor: Initialisiert die leere ProjektMap
    public Projektverwaltung() {
        projektMap = new ProjektMap<String, Projekt>();
    }

    // Fügt ein neues Projekt mit Daten hinzu
    public void Projekthinzufuegen(String projektname, int note, int abgabedatum, ArrayList<Student> schuelerliste) {
        Projekt projekt = new Projekt(projektname, note, abgabedatum, schuelerliste);
        projektMap.put(projektname, projekt);
    }

    // Fügt ein leeres Projekt mit leerem Namen hinzu (vielleicht Platzhalter)
    public void LeeresProjekthinzufuegen(){
        Projekt projekt = new Projekt();
        projektMap.put("", projekt);
    }

    // Gibt ein Projekt anhand des Projekt-Namens zurück
    public Projekt GetProjektName(String alterprojektname){
        return projektMap.get(alterprojektname);
    }

    // Projekt bearbeiten: altes Projekt ggf. entfernen, neues mit aktualisierten Daten hinzufügen
    public void ProjektBearbeiten(String alterProjektname, String neuerName, int neueNote, int neuesDatum, ArrayList<Student> neueStudentenListe){
        if (!alterProjektname.equals(neuerName)) {
            projektMap.remove(alterProjektname);
        }
        Projekt neuesProjekt = new Projekt(neuerName, neueNote, neuesDatum, neueStudentenListe);
        projektMap.put(neuerName, neuesProjekt);
    }

    // Gibt alle Projekte als Liste zurück
    public ArrayList<Projekt> getAlle() {
        return new ArrayList<>(projektMap.values());
    }

    // Löscht ein Projekt anhand des Namens
    public void Projektloeschen(String projektname) {
        projektMap.remove(projektname);
    }

    // Suche nach Projekten mit bestimmter Note (sortiert mit Bubble Sort, dann binäre Suche)
    public ArrayList<Projekt> NoteSuche(int ziel) {
        ArrayList<Projekt> notenListe = new ArrayList<>(projektMap.values());

        // Liste sortieren, damit binäre Suche funktioniert
        bubbleSortNote(notenListe);

        ArrayList<Projekt> result = new ArrayList<>();
        int index = binaereSuche(notenListe, ziel);

        if (index == -1) {
            return result; // Keine Treffer
        }

        // Suche nach weiteren Treffern links und rechts vom gefundenen Index
        int i = index;
        while (i >= 0 && notenListe.get(i).getNote() == ziel) {
            result.add(0, notenListe.get(i)); // vorne einfügen
            i--;
        }

        i = index + 1;
        while (i < notenListe.size() && notenListe.get(i).getNote() == ziel) {
            result.add(notenListe.get(i));
            i++;
        }
        return result;
    }

    // Binäre Suche in einer sortierten Liste von Projekten nach Note
    public static int binaereSuche(ArrayList<Projekt> liste, int ziel) {
        int links = 0;
        int rechts = liste.size() - 1;

        while (links <= rechts) {
            int mitte = (links + rechts) / 2;
            int wert = liste.get(mitte).getNote();

            if (wert == ziel) {
                return mitte;
            } else if (wert < ziel) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }
        return -1;
    }

    // Suche nach Projekt mit genauem Namen (lineare Suche)
    public ArrayList<Projekt> NamenSuche(String Ziel){
        ArrayList<Projekt> namenListe = new ArrayList<>(projektMap.values());
        ArrayList<Projekt> result = new ArrayList<>();
        int index = linearSearch(namenListe, Ziel);
        if (index >= 0) {
            result.add(namenListe.get(index));
        }
        return result;
    }

    // Lineare Suche nach Projektname in der Liste
    public static int linearSearch(ArrayList<Projekt> liste,String ziel) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getProjektname().equals(ziel)) {
                return i;
            }
        }
        return -1;
    }

    // Sortiert Projekte nach Name (Bubble Sort)
    public ArrayList<Projekt> NameSortieren(){
        ArrayList<Projekt> projekte = new ArrayList<>(projektMap.values());
        bubbleSortName(projekte);
        return projekte;
    }

    // Bubble Sort nach Projektname (alphabetisch)
    public static void bubbleSortName(ArrayList<Projekt> list) {
        int laenge = list.size();
        for (int i = laenge - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).getProjektname().compareTo(list.get(j + 1).getProjektname()) > 0) {
                    // Tauschen
                    Projekt temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // Sortiert Projekte nach Note (Bubble Sort)
    public ArrayList<Projekt> NoteSortieren(){
        ArrayList<Projekt> projekte = new ArrayList<>(projektMap.values());
        bubbleSortNote(projekte);
        return projekte;
    }

    // Bubble Sort nach Note (aufsteigend)
    public static void bubbleSortNote(ArrayList<Projekt> projekte) {
        int laenge = projekte.size();
        for (int i = laenge - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (projekte.get(j).getNote() > projekte.get(j + 1).getNote()) {
                    // Tauschen
                    Projekt temp = projekte.get(j);
                    projekte.set(j, projekte.get(j + 1));
                    projekte.set(j + 1, temp);
                }
            }
        }
    }

    // Sortiert Projekte nach Abgabedatum (Selection Sort)
    public ArrayList<Projekt> AbgabeDatumSortieren(){
        ArrayList<Projekt> projekte = new ArrayList<>(projektMap.values());
        SelectionSort(projekte);
        return projekte;
    }

    // Selection Sort nach Abgabedatum (aufsteigend)
    public static void SelectionSort(ArrayList<Projekt> projekt) {
        int laenge = projekt.size();
        for (int i = 0; i < laenge - 1; i++) {
            int min_index = i;

            for (int j = i + 1; j < laenge; j++) {
                if (projekt.get(j).getAbgabedatum() < projekt.get(min_index).getAbgabedatum()) {
                    min_index = j;
                }
            }

            // Tauschen, falls nötig
            if (min_index != i) {
                Projekt temp = projekt.get(i);
                projekt.set(i, projekt.get(min_index));
                projekt.set(min_index, temp);
            }
        }
    }

    // Prüft, ob ein Projektname bereits existiert
    public boolean projektnamenVorhanden(String Name){
        return projektMap.containsKey(Name);
    }
}