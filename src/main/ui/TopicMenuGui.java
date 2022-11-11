package ui;

import model.Subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopicMenuGui extends MenuGui {

    Subject selectedSubject;

    public TopicMenuGui(Subject subject) {
        super("Topic Menu", "topic");
        selectedSubject = subject;
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

    }

    @Override
    protected void loadList() {

    }

    @Override
    protected void init() {

    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = subjectName.getText();
/*
            if (name.equals("") || listOfSubjects.containsDuplicateSubject(name)) {
                Toolkit.getDefaultToolkit().beep();
                subjectName.requestFocusInWindow();
                subjectName.selectAll();
                return;
            }*/

            //listOfSubjects.addSubject(name);
            listModel.addElement(name);
            subjectName.setText("");
        }
    }

    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            String nameToBeRemoved = (String) listModel.get(index);
            //listOfSubjects.removeSubject(nameToBeRemoved);
            listModel.remove(index);
        }
    }
}
