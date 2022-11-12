package ui;

import model.Note;

import javax.swing.*;
import java.awt.*;

public class NoteListCellRenderer extends JPanel implements ListCellRenderer<Note> {

    private JLabel fileNameLabel;
    private JLabel statusLabel;
    private JLabel pathLabel;

    public NoteListCellRenderer() {
        super(new FlowLayout(FlowLayout.LEADING, 15, 5));
        fileNameLabel = new JLabel();
        statusLabel = new JLabel();
        pathLabel = new JLabel();

        add(fileNameLabel);
        add(statusLabel);
        add(pathLabel);
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Note note,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        fileNameLabel.setText(note.getName());
        statusLabel.setText(note.getStatus().toString());
        pathLabel.setText(note.getFileLocation().toString());
        if (isSelected) {
            setBackground(Color.LIGHT_GRAY);
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
