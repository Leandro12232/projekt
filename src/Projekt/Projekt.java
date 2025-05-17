package Projekt;

import java.util.ArrayList;

public class Projekt {
    private String Name;
    private int Abgabedatum;
    private int Note;

    public Projekt() {
        this.Name = "";
        this.Note = 0;
        this.Abgabedatum = 0;
    }

    public Projekt(String name, int note, int abgabedatum) {
        Name = name;
        Note = note;
        Abgabedatum = abgabedatum;
    }

    public String getName() {
        return Name;
    }

    public int getAbgabedatum() {
        return Abgabedatum;
    }


    public int getNote() {
        return Note;
    }

    public void setNote(int note) {
        Note = note;
    }

    public void setAbgabedatum(int abgabedatum) {
        Abgabedatum = abgabedatum;
    }

    public void setName(String name) {
        Name = name;
    }
}
