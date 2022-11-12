package ui;

import model.Note;
import model.Status;
import model.Topic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NoteMenuGui extends JFrame {

    Topic selectedTopic;
    private JList<Note> list;
    private DefaultListModel<Note> listModel;

    private JPanel mainPanel;
    private JPanel toolBarPane;
    private JButton addBtn;
    private JButton changeBtn;
    private JButton removeBtn;
    private JButton openBtn;
    private JFileChooser fc;

    public NoteMenuGui(Topic topic) {
        super("Note Menu for " + topic.getName());
        selectedTopic = topic;

        setPreferredSize(new Dimension(600, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

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

        addBtn = new JButton("Add");
        changeBtn = new JButton("Change Status");
        removeBtn = new JButton("Remove");
        openBtn = new JButton("Open");

        AddListener addListener = new AddListener();
        addBtn.addActionListener(addListener);
        ChangeListener changeListener = new ChangeListener();
        changeBtn.addActionListener(changeListener);
        RemoveListener removeListener = new RemoveListener();
        removeBtn.addActionListener(removeListener);

        toolBarPane.add(addBtn);
        toolBarPane.add(changeBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }

    private void initializeList() {
        listModel = new DefaultListModel();

        loadList();

        list = new JList(listModel);
        list.setCellRenderer(new NoteListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        mainPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    private void loadList() {
        for (Note n: selectedTopic.getListOfNotes()) {
            listModel.addElement(n);
        }
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Taken from FileChooserDemo
            fc = new JFileChooser();

            int returnVal = fc.showDialog(NoteMenuGui.this, "Import");

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                selectedTopic.addNote(file.toPath());
                listModel.clear();
                loadList();
            }
        }
    }

    class ChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Note selectedNote = (Note) listModel.get(index);
            Status s = (Status) JOptionPane.showInputDialog(NoteMenuGui.this, "Change note status to:",
                    "Status", JOptionPane.PLAIN_MESSAGE, null, Status.values(), Status.INCOMPLETE);
            selectedNote.setStatus(s);
            listModel.clear();
            loadList();
        }
    }

    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Note noteToBeRemoved = (Note) listModel.get(index);
            selectedTopic.removeNote(noteToBeRemoved);
            listModel.remove(index);
        }
    }
}
