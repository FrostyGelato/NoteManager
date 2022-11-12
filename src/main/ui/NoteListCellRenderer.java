package ui;

import model.Note;

import javax.swing.*;
import java.awt.*;

public class NoteListCellRenderer extends JPanel implements ListCellRenderer<Note> {

    private JLabel fileNameLabel;
    private JLabel statusLabel;
    private JLabel pathLabel;

    public NoteListCellRenderer() {
        super(new BorderLayout());
        fileNameLabel = new JLabel();
        statusLabel = new JLabel();
        pathLabel = new JLabel();

        add(fileNameLabel, BorderLayout.WEST);
        add(statusLabel, BorderLayout.CENTER);
        add(pathLabel, BorderLayout.EAST);
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Note note, int index, boolean isSelected, boolean cellHasFocus) {
        fileNameLabel.setText(note.getName());
        statusLabel.setText(note.getStatus().toString());
        pathLabel.setText(note.getFileLocation().toString());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
