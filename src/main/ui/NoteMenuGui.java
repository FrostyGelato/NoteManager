package ui;

import model.Topic;

import javax.swing.*;
import java.awt.*;

public class NoteMenuGui extends JFrame {

    Topic selectedTopic;

    private JPanel mainPanel;

    public NoteMenuGui(Topic topic) {
        super("Note Menu for " + topic.getName());
        selectedTopic = topic;

        setPreferredSize(new Dimension(600, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
    }
}
