package ui;

import model.ListOfSubjects;
import model.Subject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectMenuGui extends JFrame implements ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;
    private JPanel mainPanel;
    private JPanel toolBarPane;
    private JPanel bottomBarPane;

    private JButton saveBtn;
    private JButton loadBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton openBtn;
    private JTextField subjectName;

    private ListOfSubjects listOfSubjects;

    public SubjectMenuGui() {

        super("Notes Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        init();
        initializeToolBar();
        initializeList();
        initalizeBottomBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void initializeToolBar() {
        toolBarPane = new JPanel();
        toolBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        toolBarPane.setLayout(new FlowLayout());

        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        removeBtn = new JButton("Remove");
        openBtn = new JButton("Open");

        RemoveListener removeListener = new RemoveListener();
        removeBtn.addActionListener(removeListener);

        toolBarPane.add(saveBtn);
        toolBarPane.add(loadBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }

    private void initalizeBottomBar() {
        bottomBarPane = new JPanel();
        bottomBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        bottomBarPane.setLayout(new FlowLayout());

        JLabel forSubjectName = new JLabel("Enter subject name:");
        subjectName = new JTextField(10);
        addBtn = new JButton("Add");
        AddListener addListener = new AddListener();
        addBtn.addActionListener(addListener);

        bottomBarPane.add(forSubjectName);
        bottomBarPane.add(subjectName);
        bottomBarPane.add(addBtn);
        mainPanel.add(bottomBarPane, BorderLayout.PAGE_END);
    }

    private void initializeList() {
        listModel = new DefaultListModel();

        for (Subject s: listOfSubjects.getListOfSubjects()) {
            listModel.addElement(s.getName());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        mainPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    private void init() {
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeBtn.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeBtn.setEnabled(true);
            }
        }
    }
}
