package Projekt;


import Exceptions.DoppelterName;
import Exceptions.LeereNamenSIndNichtErlaubt;
import Exceptions.UngueltigeNote;
import Exceptions.UngueltigesDatum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUIProjektVerwaltung extends JFrame {
    ArrayList<Student> schuelerliste = new ArrayList<Student>();

    private JButton btnLoeschen;
    private JTable table;
    private DefaultTableModel tableModel;
    private Projektverwaltung projektVerwaltung;
    private JButton btnProjekt;
    private JButton btnAnzeigen;


    public GUIProjektVerwaltung() {
        setTitle("Projwktverwaltung");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        projektVerwaltung = new Projektverwaltung();

        initGui();
    }

    private void initGui() {
        JPanel inputpanel = new JPanel(new FlowLayout());

        projektVerwaltung = new Projektverwaltung();

        btnProjekt = new JButton("Projekt Hinzufügen");
        inputpanel.add(btnProjekt);

        btnAnzeigen = new JButton("Anzeigen");
        inputpanel.add(btnAnzeigen);



        add(inputpanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Projektname", "Note", "Abgabedatum", "Student"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        JPanel bottompanel = new JPanel(new BorderLayout());
        btnLoeschen = new JButton("Löschen");
        bottompanel.add(btnLoeschen, BorderLayout.WEST);
        add(bottompanel, BorderLayout.SOUTH);

        //btnLoeschen.addActionListener(e -> schuelerloeschen());
        btnProjekt.addActionListener(e -> projekthinzufuegen());
        btnAnzeigen.addActionListener(e -> anzeigen());

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
            ArrayList<Student> studentenListe = new ArrayList<>();

            for (int i = 0; i < anzahl; i++) {
                String sName = JOptionPane.showInputDialog("Name Student " + (i + 1) + ":");

                String GeburtText = JOptionPane.showInputDialog("Geburtsdatum (JJJJMMTT):");
                int geburt = Integer.parseInt(GeburtText);

                String MatrikelnummerText = JOptionPane.showInputDialog("Matrikelnummer:");
                int Matrikelnummer = Integer.parseInt(MatrikelnummerText);

                studentenListe.add(new Student(sName, geburt, Matrikelnummer));
            }

            projektVerwaltung.Projekthinzufuegen(Projektname, Note, Datum, studentenListe);
            JOptionPane.showMessageDialog(this,"Hinzugefügt");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Ungültige Zahleneingabe");;
        } catch (LeereNamenSIndNichtErlaubt | DoppelterName | UngueltigesDatum | UngueltigeNote ex) {
            JOptionPane.showMessageDialog(this,"Fehler"+ex.getMessage());
        }
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

   private void anzeigen(){
        tableModel.setRowCount(0);
        for(Projekt p : projektVerwaltung.getAlle()){
            ausgabeTabelle(p);
        }
    }

}