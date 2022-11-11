package ui;

import model.ListOfSubjects;
import model.Subject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SubjectMenuGui extends JFrame {

    private JList list;
    private DefaultListModel listModel;
    private JPanel toolBarPane;

    private ListOfSubjects listOfSubjects;

    public SubjectMenuGui() {

        super("Notes Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));

        initializeToolBar();

        init();

        listModel = new DefaultListModel();
        for (Subject s: listOfSubjects.getListOfSubjects()) {
            listModel.addElement(s.getName());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        setContentPane(listScrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void initializeToolBar() {
        toolBarPane = new JPanel();
        toolBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        setContentPane(toolBarPane);
        toolBarPane.setLayout(new FlowLayout());

        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");

        toolBarPane.add(saveBtn);
        toolBarPane.add(loadBtn);
        toolBarPane.add(addBtn);
        toolBarPane.add(removeBtn);
    }

    private void init() {
        listOfSubjects = new ListOfSubjects();

        //TODO: remove
        listOfSubjects.addSubject("math");
        listOfSubjects.addSubject("english");
    }
}
