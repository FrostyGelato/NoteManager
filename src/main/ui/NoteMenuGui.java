package ui;

import model.Topic;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteMenuGui extends JFrame {

    Topic selectedTopic;

    private JPanel mainPanel;
    private JPanel toolBarPane;
    private JButton addBtn;
    private JButton changeBtn;
    private JButton removeBtn;
    private JButton openBtn;

    public NoteMenuGui(Topic topic) {
        super("Note Menu for " + topic.getName());
        selectedTopic = topic;

        setPreferredSize(new Dimension(600, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        initializeToolBar();
        //initializeList();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    protected void initializeToolBar() {
        toolBarPane = new JPanel();
        toolBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        toolBarPane.setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        changeBtn = new JButton("Change Status");
        removeBtn = new JButton("Remove");
        openBtn = new JButton("Open");

        toolBarPane.add(addBtn);
        toolBarPane.add(changeBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }
}
