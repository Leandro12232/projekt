package Projekt;

public class Student extends Mensch {
    private int Matrikelnummer;

    public Student(String name, int datum, int matrikelnummer) {
        super(name, datum);
        Matrikelnummer = matrikelnummer;
    }

    public int getMatrikelnummer() {
        return Matrikelnummer;
    }

    public void setMatrikelnummer(int matrikelnummer) {
        Matrikelnummer = matrikelnummer;
    }


}

