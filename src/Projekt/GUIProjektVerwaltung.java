package Projekt;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIProjektVerwaltung extends JFrame {

    private JTextField tName;
    private JTextField tNote;
    private JTextField tAbgabedatum;
    private JTextField tProjektname;
    private JTextField tMatrikelnummer;
    private JButton btnHinzufuegen;
    private JButton btnLoeschen;
    private JTable table;
    private DefaultTableModel tableModel;
    private Projektverwaltung projektVerwaltung;

    public GUIProjektVerwaltung() {
        setTitle("Notenverwaltung");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        projektVerwaltung = new Projektverwaltung();

        initGui();
    }

    private void initGui() {
        JPanel inputpanel = new JPanel(new FlowLayout());

        inputpanel.add(new JLabel("Name"));
        tName = new JTextField(10);
        inputpanel.add(tName);

        inputpanel.add(new JLabel("Note"));
        tNote = new JTextField(5);
        inputpanel.add(tNote);

        inputpanel.add(new JLabel("Abgabedatum"));
        tAbgabedatum = new JTextField(10);
        inputpanel.add(tAbgabedatum);

        inputpanel.add(new JLabel("Projektname"));
        tProjektname = new JTextField(15);
        inputpanel.add(tProjektname);

        inputpanel.add(new JLabel("Matrikelnummer"));
        tMatrikelnummer = new JTextField(10);
        inputpanel.add(tMatrikelnummer);

        btnHinzufuegen = new JButton("Hinzufügen");
        inputpanel.add(btnHinzufuegen);

        add(inputpanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Note", "Abgabedatum", "Projektname", "Matrikelnummer"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        JPanel bottompanel = new JPanel(new BorderLayout());
        btnLoeschen = new JButton("Löschen");
        bottompanel.add(btnLoeschen, BorderLayout.WEST);
        add(bottompanel, BorderLayout.SOUTH);

        btnHinzufuegen.addActionListener(e -> schuelerhinzufuegen());
        btnLoeschen.addActionListener(e -> schuelerloeschen());

        tName.addActionListener(e -> btnHinzufuegen.doClick());
        tNote.addActionListener(e -> btnHinzufuegen.doClick());
        tAbgabedatum.addActionListener(e -> btnHinzufuegen.doClick());
        tProjektname.addActionListener(e -> btnHinzufuegen.doClick());
        tMatrikelnummer.addActionListener(e -> btnHinzufuegen.doClick());
    }

    private void schuelerhinzufuegen() {
        String name = tName.getText().trim();
        int note = Integer.parseInt(tNote.getText().trim());
        int abgabedatum = Integer.parseInt(tAbgabedatum.getText().trim());
        String projektname = tProjektname.getText().trim();
        int matrikelnummer = Integer.parseInt(tMatrikelnummer.getText().trim());

        Student student = new Student(name, abgabedatum, matrikelnummer);
        List<Student> liste = new ArrayList<>();
        liste.add(student);

        projektVerwaltung.Projekthinzufuegen(projektname, note, abgabedatum, liste);

        tableModel.addRow(new Object[]{name, note, abgabedatum, projektname, matrikelnummer});

        tName.setText("");
        tNote.setText("");
        tAbgabedatum.setText("");
        tProjektname.setText("");
        tMatrikelnummer.setText("");
    }

    private void schuelerloeschen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        }
    }
}
