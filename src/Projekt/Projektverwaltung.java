package Projekt;

import java.util.List;

public class Projektverwaltung {
    private ProjektMap<String, Projekt> projektMap;

    public Projektverwaltung() {
        projektMap = new ProjektMap<String, Projekt>();
    }

    public void Projekthinzufuegen(String name, int note, int abgabedatum, List<Student> schuelerliste){
        Projekt projekt = new Projekt(name, note, abgabedatum, schuelerliste);

        projektMap.put(name, projekt);

        System.out.println("Projekt: "+projekt.getName()+" wurde erfolgreich hinzugefügt");
        System.out.println(projektMap.containsKey(name));

    }

    public void ProjektlisteAnzeigen(){
        System.out.println(projektMap.keySet());

    }

    public void Projektlöschen(String name){
        projektMap.remove(name);
        System.out.println(name+" wurde gelöscht");
        System.out.println(projektMap.containsKey(name));


    }

    public void NoteSortieren(int note){
        
    }

}
