package Projekt;


import Exceptions.DoppelterName;
import Exceptions.LeereNamenSIndNichtErlaubt;
import Exceptions.UngueltigeNote;
import Exceptions.UngueltigesDatum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class GUIProjektVerwaltung extends JFrame {

    private JButton btnProjektloeschen;
    private JButton btnNameSortieren;
    private JButton btnNoteSortieren;
    private JButton btnProjektSuchen;
    private JButton btnNoteSuchen;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnProjekt;
    private JButton btnAnzeigen;
    private JButton btnPasswortaendern;

    private Projektverwaltung projektVerwaltung;
    private String passwort = "admin";



    public GUIProjektVerwaltung() {
        setTitle("Projektverwaltung");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        projektVerwaltung = new Projektverwaltung();

        initGui();
    }

    private void initGui() {
        JPanel inputpanel = new JPanel(new FlowLayout());


        btnProjekt = new JButton("Projekt Hinzufügen");
        inputpanel.add(btnProjekt);

        btnAnzeigen = new JButton("Anzeigen");
        inputpanel.add(btnAnzeigen);

        btnNameSortieren = new JButton("Nach Name sortieren");
        inputpanel.add(btnNameSortieren);

        btnNoteSortieren = new JButton("Nach Note Sortieren");
        inputpanel.add(btnNoteSortieren);

        btnProjektSuchen = new JButton("Projekt suchen");
        inputpanel.add(btnProjektSuchen);

        btnNoteSuchen = new JButton("Note suchen");
        inputpanel.add(btnNoteSuchen);

        add(inputpanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Projektname", "Note", "Abgabedatum", "Student"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        JPanel bottompanel = new JPanel(new BorderLayout());
        btnProjektloeschen = new JButton("Löschen");
        bottompanel.add(btnProjektloeschen, BorderLayout.WEST);
        //Passwort
        btnPasswortaendern = new JButton("Passwort ändern");
        bottompanel.add(btnPasswortaendern, BorderLayout.EAST);
        add(bottompanel, BorderLayout.SOUTH);

        btnProjektloeschen.addActionListener(e -> projektloeschen());
        btnProjekt.addActionListener(e -> projekthinzufuegen());
        btnAnzeigen.addActionListener(e -> anzeigen());
        btnNameSortieren.addActionListener(e -> namesortieren());
        btnNoteSortieren.addActionListener(e -> notesortieren());
        btnProjektSuchen.addActionListener(e -> projektsuchen());
        btnNoteSuchen.addActionListener(e -> notesuchen());
        //Passwort
        btnPasswortaendern.addActionListener(e -> passwortAendern());

    }



    private void projekthinzufuegen() throws DoppelterName, LeereNamenSIndNichtErlaubt, UngueltigeNote, UngueltigesDatum {
        try {
            //Eingabe des Projektnamens in ein OtionPane
            String Projektname = JOptionPane.showInputDialog("Projektname:");
            if (Projektname == null || Projektname.isBlank()) {
                throw new LeereNamenSIndNichtErlaubt("Name leer.");
            }
            //Eingabe der Note in ein OptionPane
            String NoteText = JOptionPane.showInputDialog("Note (1-6):");
            int Note = Integer.parseInt(NoteText);
            if (Note < 1 || Note > 6) {
                throw new UngueltigeNote("Ungültige Note.");
            }
            //Eingabe des Datums in ein OptionPane
            String DatumText = JOptionPane.showInputDialog("Abgabedatum (JJJJMMTT):");
            int Datum = Integer.parseInt(DatumText);
            if (Datum < 20230101 || Datum > 21001231) {
                throw new UngueltigesDatum("Ungültiges Datum.");
            }

            String AnzahlText = JOptionPane.showInputDialog("Anzahl Studenten:");
            int anzahl = Integer.parseInt(AnzahlText);
            //Temporäre Arraylist student
            ArrayList<Student> studentenListe = new ArrayList<>();

            //Hashmap wird verwendet um Duplikate zu verhindern
            Set<Integer> verwendeteMatrikelnummer = new HashSet<>();

            for (int i = 0; i < anzahl; i++) {
                String sName = JOptionPane.showInputDialog("Name Student " + (i + 1) + ":");


                int geburt = 0;
                boolean gueltigesDatum = false;
                while (!gueltigesDatum) {
                    String GeburtText = JOptionPane.showInputDialog("Geburtsdatum (JJJJMMTT):");

                    try{
                        geburt = Integer.parseInt(GeburtText);
                        if(geburt >= 19900101 && geburt <= 20250101) {
                            gueltigesDatum = true;
                        }else{
                            JOptionPane.showMessageDialog(this, "Das Datum muss zwischen 19900101 und 20250101 liegen! ");
                        }
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }

//Matrikelnummer prüfen ob 7 stellig & eindeutig
                int Matrikelnummer = 0;
                boolean gueltigeMartrikelnr = false;

                while (!gueltigeMartrikelnr) {
                    String MatrikelnummerText = JOptionPane.showInputDialog("Matrikelnummer:");
                    if(MatrikelnummerText != null && MatrikelnummerText.matches("\\d{7}")){
                        int matrikelnummer = Integer.parseInt(MatrikelnummerText);
                        if(verwendeteMatrikelnummer.contains(matrikelnummer)){
                           JOptionPane.showMessageDialog(this, "Diese Matrikelnummer wurde bereits eingegeben.");
                        }else {
                            gueltigeMartrikelnr = true;
                            verwendeteMatrikelnummer.add(matrikelnummer);
                        }

                    }else{
                        JOptionPane.showMessageDialog(this, "Matrikelnummer muss genau 7 Ziffern enthalten.");
                    }
                }



                studentenListe.add(new Student(sName, geburt, Matrikelnummer));
            }

            projektVerwaltung.Projekthinzufuegen(Projektname, Note, Datum, studentenListe);
            JOptionPane.showMessageDialog(this,"Hinzugefügt");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Ungültige Zahleneingabe");;
        } catch (LeereNamenSIndNichtErlaubt | DoppelterName | UngueltigesDatum | UngueltigeNote ex) {
            JOptionPane.showMessageDialog(this,"Fehler "+ex.getMessage());
        }
    }

    private void namesortieren(){
        ArrayList<Projekt> sortierteListeName = projektVerwaltung.NameSortieren();
        tableModel.setRowCount(0);
        for (Projekt p : sortierteListeName) {
            ausgabeTabelle(p);
        }
        JOptionPane.showMessageDialog(this,"Nach Name sortiert");
    }

    private void notesortieren(){
        ArrayList<Projekt> sortierteListeNote = projektVerwaltung.NoteSortieren();
        tableModel.setRowCount(0);
        for (Projekt p : sortierteListeNote) {
            ausgabeTabelle(p);
        }
        JOptionPane.showMessageDialog(this,"Nach Note sortiert");
    }


    private void ausgabeTabelle(Projekt p) {
        StringBuilder studenten = new StringBuilder();
        ArrayList<Student> liste = p.getSchuelerListe();
        if (liste != null) {
            for (Student s : p.getSchuelerListe()) {
                studenten.append(s.getName()).append("( ").append(s.getMatrikelnummer()).append(", ").append(s.getDatum()).append("), ");
            }
        }
        tableModel.addRow(new Object[]{p.getProjektname(), p.getNote(), p.getAbgabedatum(), studenten.toString()});
    }

    private void projektsuchen(){
        String Ziel = JOptionPane.showInputDialog("Projektname:");
        ArrayList<Projekt> suchListe = projektVerwaltung.NamenSuche(Ziel);
        tableModel.setRowCount(0);
        if (suchListe.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kein Projekt mit diesem Namen gefunden.");
            return;
        }
        for (Projekt p : suchListe) {
            ausgabeTabelle(p);
        }
        JOptionPane.showMessageDialog(this,"Gewünschte Namen");
    }

    private void notesuchen(){
        Integer Ziel = Integer.valueOf(JOptionPane.showInputDialog("Projektnote:"));
        ArrayList<Projekt> suchListe = projektVerwaltung.NoteSuche(Ziel);
        tableModel.setRowCount(0);
        if (suchListe.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keine Projekte mit dieser Note gefunden.");
            return;
        }
        for (Projekt p : suchListe) {
            ausgabeTabelle(p);
        }
        JOptionPane.showMessageDialog(this,"Gewünschte Note");
    }

    private void anzeigen(){
        tableModel.setRowCount(0);
        for(Projekt p : projektVerwaltung.getAlle()){
            ausgabeTabelle(p);
        }
    }

    private void projektloeschen(){
        int selectedRow = table.getSelectedRow();

        if(selectedRow >= 0){

            // Projektname aus der Spalte holen
            String projektname = (String) table.getValueAt(selectedRow, 0);

            //Passwortabfrage
            String eingabe = JOptionPane.showInputDialog(this,"Bitte Passwort eingeben!");

           if(eingabe != null && passwort.equals(eingabe)){
               //Projekt in der Verwaltung löschen
               projektVerwaltung.Projektloeschen(projektname);

               //Zeile aus der Tabelle entfernen
               tableModel.removeRow(selectedRow);

               JOptionPane.showMessageDialog(this,"Projekt " + projektname + " wurde gelöscht.");
           }else {
               JOptionPane.showMessageDialog(this,"Falsches Passwort. Löschung wird abgebrochen!");
           }


        } else {
            JOptionPane.showMessageDialog(this,"Kein Projekt ausgewählt!");
        }
    }

    public void passwortAendern(){
        String altesPasswort = JOptionPane.showInputDialog(this, "Aktuelles Passwort:");

        if (passwort.equals(altesPasswort)) {
            String neuesPasswort = JOptionPane.showInputDialog(this, "Neues Passwort:");
            if (neuesPasswort != null && !neuesPasswort.isBlank()) {
                passwort = neuesPasswort;
                JOptionPane.showMessageDialog(this, "Passwort erfolgreich geändert.");
            } else {
                JOptionPane.showMessageDialog(this, "Neues Passwort darf nicht leer sein.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Falsches aktuelles Passwort.");
        }
    }
}