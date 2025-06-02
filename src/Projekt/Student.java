package Projekt;

// Student erbt von der Klasse Mensch
public class Student extends Mensch {
    private int Matrikelnummer;

    // Konstruktor mit Name, Geburtsdatum und Matrikelnummer
    public Student(String name, int datum, int matrikelnummer) {
        super(name, datum); // Aufruf des Konstruktors der Oberklasse Mensch
        Matrikelnummer = matrikelnummer;
    }

    // Getter für Matrikelnummer
    public int getMatrikelnummer() {
        return Matrikelnummer;
    }

    // Setter für Matrikelnummer
    public void setMatrikelnummer(int matrikelnummer) {
        Matrikelnummer = matrikelnummer;
    }
}
