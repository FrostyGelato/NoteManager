package ui;

import model.ListOfSubjects;
import model.Subject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SubjectMenuGui extends JFrame {

    private JList list;
    private DefaultListModel listModel;
    private JPanel mainPanel;
    private JPanel toolBarPane;

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

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void initializeToolBar() {
        toolBarPane = new JPanel();
        toolBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        toolBarPane.setLayout(new FlowLayout());

        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton openBtn = new JButton("Open");

        toolBarPane.add(saveBtn);
        toolBarPane.add(loadBtn);
        toolBarPane.add(addBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
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
}
