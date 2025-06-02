package Projekt;

// Basisklasse Mensch mit grundlegenden Eigenschaften
public class Mensch {
    private String Name;
    private int Datum;

    // Konstruktor zum Initialisieren von Name und Datum
    public Mensch(String name, int datum) {
        Name = name;
        Datum = datum;
    }

    public String getName() {
        return Name;
    }

    public int getDatum() {
        return Datum;
    }

    public void setName(String name) {
        Name = name;
    }
}