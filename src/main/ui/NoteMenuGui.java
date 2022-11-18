package ui;

import model.Note;
import model.Status;
import model.Topic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// A menu window that displays a list of notes
public class NoteMenuGui extends JDialog {

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

    //EFFECTS: constructs a menu window
    public NoteMenuGui(TopicMenuGui parent, Topic topic) {
        super(parent, "Note Menu for " + topic.getName(), ModalityType.APPLICATION_MODAL);
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

    //MODIFIES: this
    //EFFECTS: constructs the set of buttons at the top
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
        OpenListener openListener = new OpenListener();
        openBtn.addActionListener(openListener);
        enableButtons(false);

        toolBarPane.add(addBtn);
        toolBarPane.add(changeBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }

    //MODIFIES: this
    //EFFECTS: constructs the list
    private void initializeList() {
        listModel = new DefaultListModel();

        loadList();

        list = new JList(listModel);
        list.setCellRenderer(new NoteListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1) {
                        enableButtons(false);
                    } else {
                        enableButtons(true);
                    }
                }
            }
        });

        mainPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: add items to list
    private void loadList() {
        for (Note n: selectedTopic.getListOfNotes()) {
            listModel.addElement(n);
        }
    }

    // Handles event where user clicks on add button
    class AddListener implements ActionListener {

        //MODIFIES: NoteMenuGui.this
        //EFFECTS: adds new note
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

    // Handles event where user clicks on change button
    class ChangeListener implements ActionListener {

        //MODIFIES: NoteMenuGui.this
        //EFFECTS: change the status of the selected note
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Note selectedNote = (Note) listModel.get(index);
            Status s = (Status) JOptionPane.showInputDialog(NoteMenuGui.this, "Change note status to:",
                    "Status", JOptionPane.PLAIN_MESSAGE, null, Status.values(), Status.INCOMPLETE);
            selectedNote.setStatus(s);
            listModel.clear();
            loadList();
            enableButtons(false);
        }
    }

    // Handles event where user clicks on remove button
    class RemoveListener implements ActionListener {

        //MODIFIES: NoteMenuGui.this
        //EFFECTS: remove selected note from list
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Note noteToBeRemoved = (Note) listModel.get(index);
            selectedTopic.removeNote(noteToBeRemoved);
            listModel.remove(index);
            enableButtons(false);
        }
    }

    // Handles event where user clicks on open button
    class OpenListener implements ActionListener {

        //MODIFIES: NoteMenuGui.this
        //EFFECTS: opens selected note
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Note selectedNote = (Note) listModel.get(index);
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(selectedNote.getFileLocation().toFile());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(NoteMenuGui.this,
                        "Unable to open file",
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: If b is true, enables remove, change, and open buttons; otherwise, disables them
    private void enableButtons(boolean b) {
        removeBtn.setEnabled(b);
        changeBtn.setEnabled(b);
        openBtn.setEnabled(b);
    }
}
