package ui;

import model.Subject;
import model.Topic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopicMenuGui extends MenuGui {

    Subject selectedSubject;

    public TopicMenuGui(Subject subject) {
        super("Topic Menu for " + subject.getName(), "topic");
        selectedSubject = subject;
        loadList();
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
        saveBtn.setEnabled(false);
    }

    @Override
    protected void handleLoadBtn() {
        loadBtn.setEnabled(false);
    }

    @Override
    protected void loadList() {
        if (selectedSubject != null) {
            for (Topic t : selectedSubject.getListOfTopics()) {
                listModel.addElement(t.getName());
            }
        }
    }

    @Override
    protected void init() {

    }

    class AddListener implements ActionListener {

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

    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String nameToBeRemoved = (String) listModel.get(index);
            selectedSubject.removeTopic(nameToBeRemoved);
            listModel.remove(index);
        }
    }

    class OpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String selectedTopicName = (String) listModel.get(index);
            NoteMenuGui noteMenu = new NoteMenuGui(selectedSubject.getTopicByName(selectedTopicName));
        }
    }
}
