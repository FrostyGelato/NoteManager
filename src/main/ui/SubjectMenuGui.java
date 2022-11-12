package ui;

import model.ListOfSubjects;
import model.Subject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SubjectMenuGui extends MenuGui {

    private ListOfSubjects listOfSubjects;
    private static final String JSON_STORE = "./data/subjectList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SubjectMenuGui() {
        super("Subject Menu", "subject");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int save = JOptionPane.showConfirmDialog(null,
                        "Would you like to save before exiting?", "Potentially Unsaved Data",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (save == JOptionPane.YES_OPTION) {
                    save();
                    dispose();
                }
            }
        });

        final ImageIcon icon = new ImageIcon("./data/load.png");
        int load = JOptionPane.showConfirmDialog(this,
                "Would you like to load data from file?", "Restore Data",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (load == JOptionPane.YES_OPTION) {
            load();
        }
    }

    @Override
    protected void handleAddBtn() {
        AddListener addListener = new AddListener();
        addBtn.addActionListener(addListener);
    }

    @Override
    protected void handleRemoveBtn() {
        RemoveListener removeListener = new RemoveListener();
        removeBtn.addActionListener(removeListener);
    }

    @Override
    protected void handleOpenBtn() {
        OpenListener openListener = new OpenListener();
        openBtn.addActionListener(openListener);
    }

    @Override
    protected void handleSaveBtn() {
        SaveListener saveListener = new SaveListener();
        saveBtn.addActionListener(saveListener);
    }

    @Override
    protected void handleLoadBtn() {
        LoadListener loadListener = new LoadListener();
        loadBtn.addActionListener(loadListener);
    }

    @Override
    protected void loadList() {
        for (Subject s: listOfSubjects.getListOfSubjects()) {
            listModel.addElement(s.getName());
        }
    }

    @Override
    protected void init() {
        listOfSubjects = new ListOfSubjects();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfSubjects);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(SubjectMenuGui.this,
                    "Unable to write to file: " + JSON_STORE,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
        //JOptionPane.showMessageDialog(SubjectMenuGui.this,
                //"Data has been saved to file.", "Annoying Message", JOptionPane.PLAIN_MESSAGE);
    }

    private void load() {
        try {
            listOfSubjects = jsonReader.read();
            //JOptionPane.showMessageDialog(SubjectMenuGui.this,
                    //"Data has been loaded from file.", "Annoying Message", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(SubjectMenuGui.this,
                    "Unable to read from file: " + JSON_STORE,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }

        listModel.clear();
        loadList();
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            if (name.equals("") || listOfSubjects.containsDuplicateSubject(name)) {
                Toolkit.getDefaultToolkit().beep();
                nameField.requestFocusInWindow();
                nameField.selectAll();
                return;
            }

            listOfSubjects.addSubject(name);
            listModel.addElement(name);
            nameField.setText("");
        }
    }

    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String nameToBeRemoved = (String) listModel.get(index);
            listOfSubjects.removeSubject(nameToBeRemoved);
            listModel.remove(index);
        }
    }

    class OpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String selectedSubjectName = (String) listModel.get(index);
            TopicMenuGui topicMenu = new TopicMenuGui(listOfSubjects.getSubjectByName(selectedSubjectName));
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            load();
        }
    }
}
