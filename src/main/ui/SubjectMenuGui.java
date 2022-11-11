package ui;

import model.ListOfSubjects;
import model.Subject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectMenuGui extends MenuGui {

    private ListOfSubjects listOfSubjects;

    public SubjectMenuGui() {
        super("Subject Menu", "subject");
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
    protected void loadList() {
        for (Subject s: listOfSubjects.getListOfSubjects()) {
            listModel.addElement(s.getName());
        }
    }

    @Override
    protected void init() {
        listOfSubjects = new ListOfSubjects();

        //TODO: remove
        listOfSubjects.addSubject("math");
        listOfSubjects.addSubject("english");
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = subjectName.getText();

            if (name.equals("") || listOfSubjects.containsDuplicateSubject(name)) {
                Toolkit.getDefaultToolkit().beep();
                subjectName.requestFocusInWindow();
                subjectName.selectAll();
                return;
            }

            listOfSubjects.addSubject(name);
            listModel.addElement(name);
            subjectName.setText("");
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
}
