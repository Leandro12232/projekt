package Projekt;

import java.util.ArrayList;
import java.util.List;

public class Projekt {
    private String Name;
    private int Abgabedatum;
    private int Note;
    private List<Student> SchuelerListe;

    public Projekt() {
        this.Name = "";
        this.Note = 0;
        this.Abgabedatum = 0;
        this.SchuelerListe = null;
    }

    public Projekt(String name, int note, int abgabedatum, List schuelerliste) {
        Name = name;
        Note = note;
        Abgabedatum = abgabedatum;
        SchuelerListe = schuelerliste;
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

    public List<Student> getSchuelerListe() {
        return SchuelerListe;
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

    public void setSchuelerListe(List<Student> schuelerListe) {
        SchuelerListe = schuelerListe;
    }
}
