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

    public void Projekthinzufuegen(String projektname, int note, int abgabedatum, ArrayList<Student> schuelerliste) {
        Projekt projekt = new Projekt(projektname, note, abgabedatum, schuelerliste);

        projektMap.put(projektname, projekt);


    }

    public void LeeresProjekthinzufuegen(){
        Projekt projekt = new Projekt();

        projektMap.put("", projekt);
    }

    public Projekt GetProjektName(String projektname){
        return projektMap.get(projektname);

    }

    public void ProjektBearbeiten(String alterProjektname, String neuerName, int neueNote, int neuesDatum, ArrayList<Student> neueStudentenListe){
        // Wenn der Projektname geändert wurde
        if (!alterProjektname.equals(neuerName)) {
            projektMap.remove(alterProjektname); // altes Projekt entfernen
        }

        Projekt neuesProjekt = new Projekt(neuerName, neueNote, neuesDatum, neueStudentenListe);
        projektMap.put(neuerName, neuesProjekt); // neues Projekt hinzufügen
    }

    public ArrayList<Projekt> getAlle() {
        return new ArrayList<>(projektMap.values());
    }

    public void Projektloeschen(String projektname) {
        projektMap.remove(projektname);
    }


    public ArrayList<Projekt> NoteSuche(int ziel) {
        ArrayList<Projekt> notenListe = new ArrayList<>(projektMap.values());

        // Wichtig: Liste muss sortiert sein!
        bubbleSortNote(notenListe);

        ArrayList<Projekt> result = new ArrayList<>();
        int index = binaereSuche(notenListe, ziel);

        if (index == -1) {
            return result; // leer, keine Treffer
        }

        // Nach links scannen
        int i = index;
        while (i >= 0 && notenListe.get(i).getNote() == ziel) {
            result.add(0, notenListe.get(i)); // vorne einfügen
            i--;
        }

        // Nach rechts scannen (ohne das ursprüngliche index-Element doppelt zu nehmen)
        i = index + 1;
        while (i < notenListe.size() && notenListe.get(i).getNote() == ziel) {
            result.add(notenListe.get(i));
            i++;
        }
        return result;
    }


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

    public ArrayList<Projekt> NamenSuche(String Ziel){
        ArrayList<Projekt> namenListe = new ArrayList<>(projektMap.values());
        ArrayList<Projekt> result = new ArrayList<>();
        int index = linearSearch(namenListe, Ziel);
        if (index >= 0) {
            result.add(namenListe.get(index));
        }
            return result;
        }


    public static int linearSearch(ArrayList<Projekt> liste,String ziel) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getProjektname().equals(ziel)) {
                return i;

            }
        }
        return -1;
    }

    public ArrayList<Projekt> NameSortieren(){
        ArrayList<Projekt> projekte = new ArrayList<>(projektMap.values());
        bubbleSortName(projekte);
        return projekte;

    }

    public static void bubbleSortName(List<Projekt> list) {
        int laenge = list.size();

        for (int i = laenge - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).getProjektname().compareTo(list.get(j + 1).getProjektname()) > 0) {
                    // Elemente tauschen
                    Projekt temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public ArrayList<Projekt> NoteSortieren(){
        ArrayList<Projekt> projekte = new ArrayList<>(projektMap.values());
        bubbleSortNote(projekte);
        return projekte;
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
