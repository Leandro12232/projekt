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
    private JButton btnAbgabeDatumSortieren;
    private JButton btnProjektSuchen;
    private JButton btnNoteSuchen;
    private JButton btnLeeresProjekt;
    private JButton btnProjektBearbeiten;
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

        btnLeeresProjekt = new JButton("Leeres Projekt Hinzufügen");
        inputpanel.add(btnLeeresProjekt);

        btnProjektBearbeiten = new JButton("Projekt Bearbeiten");
        inputpanel.add(btnProjektBearbeiten);

        btnAnzeigen = new JButton("Anzeigen");
        inputpanel.add(btnAnzeigen);

        btnNameSortieren = new JButton("Nach Name sortieren");
        inputpanel.add(btnNameSortieren);

        btnNoteSortieren = new JButton("Nach Note Sortieren");
        inputpanel.add(btnNoteSortieren);

        btnAbgabeDatumSortieren = new JButton("Nach AbgabeDatum Sortieren");
        inputpanel.add(btnAbgabeDatumSortieren);

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
        btnLeeresProjekt.addActionListener(e -> leeresprojekthinzufuegen());
        btnProjektBearbeiten.addActionListener(e -> projektbearbeiten());
        btnAnzeigen.addActionListener(e -> anzeigen());
        btnNameSortieren.addActionListener(e -> namesortieren());
        btnNoteSortieren.addActionListener(e -> notesortieren());
        btnAbgabeDatumSortieren.addActionListener(e -> abgabedatumsortieren());
        btnProjektSuchen.addActionListener(e -> projektsuchen());
        btnNoteSuchen.addActionListener(e -> notesuchen());
        //Passwort
        btnPasswortaendern.addActionListener(e -> passwortAendern());


    }



    private void projekthinzufuegen() throws DoppelterName, LeereNamenSIndNichtErlaubt, UngueltigeNote, UngueltigesDatum {
        try {
            String Projektname = null;

            while (true) {
                Projektname = JOptionPane.showInputDialog("Projektname:");

                // Prüfen, ob Benutzer abgebrochen hat
                if (Projektname == null) {
                    JOptionPane.showMessageDialog(this, "Aktion abgebrochen.");
                    return;
                }

                // Prüfen, ob Eingabe leer ist
                if (Projektname.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Name darf nicht leer sein!");
                    continue;
                }

                // Prüfen, ob Projektname bereits vorhanden ist
                if (projektVerwaltung.projektnamenVorhanden(Projektname)) {
                    JOptionPane.showMessageDialog(this, "Projekt existiert bereits!");
                    continue;
                }

                // Gültiger Name
                break;
            }



            int Note = 0;
            while (true) {
                String NoteText = JOptionPane.showInputDialog("Note (1-6):");

                if (NoteText == null) {
                    JOptionPane.showMessageDialog(this, "Aktion abgebrochen.");
                    return;
                }

                try {
                    Note = Integer.parseInt(NoteText);
                    if (Note < 1 || Note > 6) {
                        JOptionPane.showMessageDialog(this, "Note muss zwischen 1 und 6 liegen!");
                        continue;
                    }
                    break; // gültige Note
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Bitte eine gültige Zahl zwischen 1 und 6 eingeben.");
                }
            }


            int Datum = -1;

            while (true) {
                String DatumText = JOptionPane.showInputDialog("Abgabedatum (JJJJMMTT):");

                // Benutzer klickt auf Abbrechen
                if (DatumText == null) {
                    JOptionPane.showMessageDialog(this, "Aktion abgebrochen.");
                    return;
                }

                try {
                    Datum = Integer.parseInt(DatumText);
                    if (Datum < 20230101 || Datum > 21001231) {
                        JOptionPane.showMessageDialog(this, "Das Datum muss zwischen 20230101 und 21001231 liegen!");
                        continue;
                    }
                    break; // gültiges Datum
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Bitte ein gültiges Datum im Format JJJJMMTT eingeben!");
                }
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
                        if(geburt >= 19900101 || geburt <= 20250101) {
                            gueltigesDatum = true;
                        }else{
                            JOptionPane.showMessageDialog(this, "Das Datum muss zwischen 19900101 und 20250101 liegen! ");
                        }
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }


                String MatrikelnummerText = JOptionPane.showInputDialog("Matrikelnummer:");
                int Matrikelnummer = Integer.parseInt(MatrikelnummerText);
                if(verwendeteMatrikelnummer.contains(Matrikelnummer)){
                    JOptionPane.showMessageDialog(this, "Diese Matrikelnummer wurde bereits eingegeben.");
                    JOptionPane.showMessageDialog(this, "Matrikelnummer");
                }else {

                    verwendeteMatrikelnummer.add(Matrikelnummer);
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

    private void leeresprojekthinzufuegen(){
        projektVerwaltung.LeeresProjekthinzufuegen();
        JOptionPane.showMessageDialog(this,"Hinzugefügt");
    }

    private void projektbearbeiten() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            // Projektname aus der Tabelle auslesen (angenommen Spalte 0 enthält den Namen)
            String alterProjektname = (String) tableModel.getValueAt(selectedRow, 0);

            Projekt altesProjekt = projektVerwaltung.GetProjektName(alterProjektname); // eigene Methode nötig

            if (altesProjekt == null) {
                JOptionPane.showMessageDialog(this, "Projekt nicht gefunden!");
                return;
            }

            try {
                // Neue Werte abfragen
                String neuerName = JOptionPane.showInputDialog("Neuer Projektname:", altesProjekt.getProjektname());
                if (neuerName == null || neuerName.isBlank()) {
                    throw new LeereNamenSIndNichtErlaubt("Name leer.");
                }

                String noteText = JOptionPane.showInputDialog("Neue Note (1-6):", altesProjekt.getNote());
                int neueNote = Integer.parseInt(noteText);
                if (neueNote < 1 || neueNote > 6) {
                    throw new UngueltigeNote("Ungültige Note.");

                }

                String datumText = JOptionPane.showInputDialog("Neues Abgabedatum (JJJJMMTT):", altesProjekt.getAbgabedatum());
                int neuesDatum = Integer.parseInt(datumText);
                if (neuesDatum < 20230101 || neuesDatum > 21001231) {
                    throw new UngueltigesDatum("Ungültiges Datum.");
                }

                // Studentenliste neu abfragen
                String anzahlText = JOptionPane.showInputDialog("Anzahl Studenten:");
                int anzahl = Integer.parseInt(anzahlText);

                ArrayList<Student> neueStudenten = new ArrayList<>();
                for (int i = 0; i < anzahl; i++) {
                    String sName = JOptionPane.showInputDialog("Name Student " + (i + 1) + ":");
                    String geburtText = JOptionPane.showInputDialog("Geburtsdatum (JJJJMMTT):");
                    int geburt = Integer.parseInt(geburtText);
                    String matrikelText = JOptionPane.showInputDialog("Matrikelnummer:");
                    int matrikel = Integer.parseInt(matrikelText);
                    neueStudenten.add(new Student(sName, geburt, matrikel));
                }

                // Bestehendes Projekt entfernen und neues einfügen (oder ersetzen)
                projektVerwaltung.ProjektBearbeiten(alterProjektname, neuerName, neueNote, neuesDatum, neueStudenten);
                anzeigen(); // Tabelle neu laden

                JOptionPane.showMessageDialog(this, "Projekt aktualisiert.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ungültige Zahleneingabe.");
            } catch (LeereNamenSIndNichtErlaubt | UngueltigeNote | UngueltigesDatum | DoppelterName ex) {
                JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(this, "Kein Projekt ausgewählt!");
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

    private void abgabedatumsortieren(){
        ArrayList<Projekt> sortierteListeAbgabeDatum = projektVerwaltung.AbgabeDatumSortieren();
        tableModel.setRowCount(0);
        for (Projekt p : sortierteListeAbgabeDatum) {
            ausgabeTabelle(p);
        }
        JOptionPane.showMessageDialog(this,"Nach Abgabedatum sortiert");
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