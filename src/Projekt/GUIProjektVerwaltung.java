package Projekt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GUIProjektVerwaltung extends JFrame {

    private JTextField tName;
    private JTextField tNote;
    private JTextField tAbgabedatum;
    private JTextField tProjektname;
    private JButton btnHinzufuegen;
    private JButton btnLoeschen;
    private JButton btnSuche;
    private ArrayList<Student> schuelerlist;
    private JTable Table;
    private DefaultTableModel TableModel;



    public GUIProjektVerwaltung() {
        setTitle("Notenverwaltung");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        schuelerlist = new ArrayList<>();

        initGui();

    }
    private void initGui() {

        //Eingabe
        JPanel inputpanel = new JPanel(new FlowLayout());
        inputpanel.add(new JLabel("Name"));
        tName = new JTextField(15);
        inputpanel.add(tName);

        //Note
        inputpanel.add(new JLabel("Note"));
        tNote = new JTextField(5);
        inputpanel.add(tNote);

        //Abgabedatum
        inputpanel.add(new JLabel("Abgabedatum"));
        tAbgabedatum = new JTextField(11);
        inputpanel.add(tAbgabedatum);

        //Projektname
        inputpanel.add(new JLabel("Projektname"));
        tProjektname = new JTextField(25);
        inputpanel.add(tProjektname);

        btnHinzufuegen = new JButton("Hinzufügen");
        inputpanel.add(btnHinzufuegen);

        add(inputpanel, BorderLayout.NORTH);

        //Table
        TableModel = new DefaultTableModel(new Object[]{"Name", "Note", "Abgabedatum", "Projektname"}, 0);
        Table = new JTable(TableModel);
        JScrollPane scrollpane = new JScrollPane(Table);
        add(scrollpane, BorderLayout.CENTER);

        //Button + Durchsnitt
        JPanel bottompanel = new JPanel(new BorderLayout());
        btnLoeschen = new JButton("Löschen");
        bottompanel.add(btnLoeschen, BorderLayout.WEST);

        btnSuche = new JButton("Suche");
        bottompanel.add(btnSuche, BorderLayout.EAST);

        add(bottompanel, BorderLayout.SOUTH);

        //Action Listener
        btnHinzufuegen.addActionListener(e -> schuelerhinzufuegen());
        btnLoeschen.addActionListener(e -> schuelerloechen());

        tNote.addActionListener(e -> btnHinzufuegen.doClick());
        tName.addActionListener(e -> btnHinzufuegen.doClick());
        tAbgabedatum.addActionListener(e -> btnHinzufuegen.doClick());
        tProjektname.addActionListener(e -> btnHinzufuegen.doClick());
    }

    private void schuelerhinzufuegen() {
        String name = tName.getText().trim();
        String noteText = tNote.getText().trim();
        String abgabedatumText = tAbgabedatum.getText().trim();
        String projektname = tProjektname.getText().trim();

        if (name.isEmpty() || noteText.isEmpty() || abgabedatumText.isEmpty() || projektname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte alle Felder ausfüllen!");
            return;
        }

        try {
            double note = Double.parseDouble(noteText);
            int abgabedatum = Integer.parseInt(abgabedatumText); // z. B. 20240519

            if (note < 1 || note > 6) {
                JOptionPane.showMessageDialog(this, "Note muss zwischen 1 und 6 liegen!");
                return;
            }

            // Neuen Student erzeugen
            Student student = new Student(name, note, abgabedatum);
            schuelerlist.add(student);

            // Projekt existiert schon? Wenn ja, ergänzen. Wenn nicht, neu anlegen.
            if (projektMap.containsKey(projektname)) {
                projektMap.get(projektname).getSchuelerliste().add(student);
            } else {
                List<Student> neueListe = new ArrayList<>();
                neueListe.add(student);
                Projekt projekt = new Projekt(projektname, (int) note, abgabedatum, neueListe);
                projektMap.put(projektname, projekt);
            }

            // GUI aktualisieren
            TableModel.addRow(new Object[]{name, note, abgabedatum, projektname});

            // Eingabefelder leeren
            tName.setText("");
            tNote.setText("");
            tAbgabedatum.setText("");
            tProjektname.setText("");

            System.out.println("Projekt gespeichert: " + projektname);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte gültige Zahlen für Note und Abgabedatum eingeben!");
        }
    }
    private void schuelerloechen() {
        int selectedRow = Table.getSelectedRow();
        if(selectedRow >= 0) {
            schuelerlist.remove(selectedRow);
            TableModel.removeRow(selectedRow);

        }else{
            JOptionPane.showMessageDialog(this, "Bitte einen Schüler auswählen!");
        }
    }

}
