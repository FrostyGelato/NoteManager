package ui;

import model.Subject;
import model.Topic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// A menu window that displays a list of topics
public class TopicMenuGui extends MenuGui {

    Subject selectedSubject;

    //EFFECTS: constructs a menu window
    public TopicMenuGui(SubjectMenuGui parent, Subject subject) {
        super("Topic Menu for " + subject.getName(), "topic");
        selectedSubject = subject;
        loadList();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                parent.setEnabled(true);
            }
        });

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
        saveBtn.setEnabled(false);
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of load button
    @Override
    protected void handleLoadBtn() {
        loadBtn.setEnabled(false);
    }

    //MODIFIES: this
    //EFFECTS: add items to list
    @Override
    protected void loadList() {
        if (selectedSubject != null) {
            for (Topic t : selectedSubject.getListOfTopics()) {
                listModel.addElement(t.getName());
            }
        }
    }

    //EFFECTS: nothing
    @Override
    protected void init() {

    }

    // Handles event where user clicks on add button
    class AddListener implements ActionListener {

        //MODIFIES: TopicMenuGui.this
        //EFFECTS: add a topic with given name to list
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            if (name.equals("") || selectedSubject.containsDuplicateTopic(name)) {
                Toolkit.getDefaultToolkit().beep();
                nameField.requestFocusInWindow();
                nameField.selectAll();
                return;
            }

            selectedSubject.addTopic(name);
            listModel.addElement(name);
            nameField.setText("");
        }
    }

    // Handles event where user clicks on remove button
    class RemoveListener implements ActionListener {

        //MODIFIES: TopicMenuGui.this
        //EFFECTS: remove selected topic from list
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String nameToBeRemoved = (String) listModel.get(index);
            selectedSubject.removeTopic(nameToBeRemoved);
            listModel.remove(index);
            removeBtn.setEnabled(false);
            openBtn.setEnabled(false);
        }
    }

    // Handles event where user clicks on open button
    class OpenListener implements ActionListener {

        //EFFECTS: launches the note menu window for selected topic
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String selectedTopicName = (String) listModel.get(index);
            NoteMenuGui noteMenu = new NoteMenuGui(TopicMenuGui.this,
                    selectedSubject.getTopicByName(selectedTopicName));
        }
    }
}
