package Projekt;

import java.util.ArrayList;
import java.util.List;

public class Projekt {
    private String Projektname;
    private int Abgabedatum;
    private int Note;
    private ArrayList<Student> SchuelerListe;

    public Projekt() {
        this.Projektname = "";
        this.Note = 0;
        this.Abgabedatum = 0;
        this.SchuelerListe = null;
    }

    public Projekt(String projektname, int note, int abgabedatum, ArrayList<Student> schuelerliste) {
        Projektname = projektname;
        Note = note;
        Abgabedatum = abgabedatum;
        SchuelerListe = schuelerliste;
    }

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
