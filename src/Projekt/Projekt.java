package Projekt;

import java.util.ArrayList;

public class Projekt {
    // Attribute eines Projekts
    private String Projektname;
    private int Abgabedatum;
    private int Note;
    private ArrayList<Student> SchuelerListe;  // Liste der Schüler, die am Projekt beteiligt sind

    // Standardkonstruktor: setzt Standardwerte (leerer Name, Note 0, Datum 0, keine Schüler)
    public Projekt() {
        this.Projektname = "";
        this.Note = 0;
        this.Abgabedatum = 0;
        this.SchuelerListe = null;
    }

    // Konstruktor mit Parametern, um ein Projekt mit Daten zu erstellen
    public Projekt(String projektname, int note, int abgabedatum, ArrayList<Student> schuelerliste) {
        Projektname = projektname;
        Note = note;
        Abgabedatum = abgabedatum;
        SchuelerListe = schuelerliste;
    }

    // Getter-Methoden für die Attribute

    public String getProjektname() {
        return Projektname;
    }

    public int getAbgabedatum() {
        return Abgabedatum;
    }

    public int getNote() {
        return Note;
    }

    public ArrayList<Student> getSchuelerListe() {
        return SchuelerListe;
    }

    // Setter-Methoden, um die Attribute zu ändern

    public void setNote(int note) {
        Note = note;
    }

    public void setAbgabedatum(int abgabedatum) {
        Abgabedatum = abgabedatum;
    }

    public void setSchuelerListe(ArrayList<Student> schuelerListe) {
        SchuelerListe = schuelerListe;
    }

    public void setProjektname(String projektname) {
        Projektname = projektname;
    }
}
