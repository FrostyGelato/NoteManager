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

// A menu window that displays a list of subjects
public class SubjectMenuGui extends MenuGui {

    private ListOfSubjects listOfSubjects;
    private static final String JSON_STORE = "./data/subjectList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs a menu window
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

    //MODIFIES: this
    //EFFECTS: set behaviour of add button
    @Override
    protected void handleAddBtn() {
        AddListener addListener = new AddListener();
        addBtn.addActionListener(addListener);
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of remove button
    @Override
    protected void handleRemoveBtn() {
        RemoveListener removeListener = new RemoveListener();
        removeBtn.addActionListener(removeListener);
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of open button
    @Override
    protected void handleOpenBtn() {
        OpenListener openListener = new OpenListener();
        openBtn.addActionListener(openListener);
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of save button
    @Override
    protected void handleSaveBtn() {
        SaveListener saveListener = new SaveListener();
        saveBtn.addActionListener(saveListener);
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of load button
    @Override
    protected void handleLoadBtn() {
        LoadListener loadListener = new LoadListener();
        loadBtn.addActionListener(loadListener);
    }

    //MODIFIES: this
    //EFFECTS: add items to list
    @Override
    protected void loadList() {
        for (Subject s: listOfSubjects.getListOfSubjects()) {
            listModel.addElement(s.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: initialize other fields
    @Override
    protected void init() {
        listOfSubjects = new ListOfSubjects();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: saves list of subjects to file
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

    //MODIFIES: this
    //EFFECTS: loads list of subjects from file
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

    // Handles event where user clicks on add button
    class AddListener implements ActionListener {

        //MODIFIES: SubjectMenuGui.this
        //EFFECTS: add a subject with given name to list
        //Taken from ListDemoProject
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

    // Handles event where user clicks on remove button
    class RemoveListener implements ActionListener {

        //MODIFIES: SubjectMenuGui.this
        //EFFECTS: remove selected subject from list
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String nameToBeRemoved = (String) listModel.get(index);
            listOfSubjects.removeSubject(nameToBeRemoved);
            listModel.remove(index);
            removeBtn.setEnabled(false);
            openBtn.setEnabled(false);
        }
    }

    // Handles event where user clicks on open button
    class OpenListener implements ActionListener {

        //EFFECTS: launches the topic menu window for selected subject
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String selectedSubjectName = (String) listModel.get(index);
            SubjectMenuGui.this.setEnabled(false);
            TopicMenuGui topicMenu = new TopicMenuGui(SubjectMenuGui.this,
                    listOfSubjects.getSubjectByName(selectedSubjectName));
        }
    }

    // Handles event where user clicks on save button
    class SaveListener implements ActionListener {

        //EFFECTS: saves list of subjects to file
        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    // Handles event where user clicks on load button
    class LoadListener implements ActionListener {

        //MODIFIES: SubjectMenuGui.this
        //EFFECTS: loads list of subjects from file
        @Override
        public void actionPerformed(ActionEvent e) {
            load();
        }
    }
}
