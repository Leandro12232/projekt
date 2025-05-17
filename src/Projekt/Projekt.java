package Projekt;

import java.util.ArrayList;

public class Projekt {
    private String Name;
    private int Abgabedatum;
    private ArrayList<Student> Liste;
    private int Note;

    public Projekt() {
        this.Name = "";
        this.Note = 0;
        this.Liste = null;
        this.Abgabedatum = 0;
    }

    public Projekt(String name, int note, int abgabedatum, ArrayList<Student> liste) {
        Name = name;
        Note = note;
        Abgabedatum = abgabedatum;
        Liste = liste;
    }

    public String getName() {
        return Name;
    }

    public int getAbgabedatum() {
        return Abgabedatum;
    }

    public ArrayList<Student> getListe() {
        return Liste;
    }

    public int getNote() {
        return Note;
    }

    public void setNote(int note) {
        Note = note;
    }

    public void setListe(ArrayList<Student> liste) {
        Liste = liste;
    }

    public void setAbgabedatum(int abgabedatum) {
        Abgabedatum = abgabedatum;
    }

    public void setName(String name) {
        Name = name;
    }
}
