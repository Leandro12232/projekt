package Projekt;

import java.util.ArrayList;

public class Projektverwaltung {
    private ProjektMap<String, Projekt> projektMap;

    public Projektverwaltung() {
        projektMap = new ProjektMap<String, Projekt>();
    }

    public void Projekthinzufuegen(String name, int note, int abgabedatum){
        Projekt projekt = new Projekt(name, note, abgabedatum);

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

}
