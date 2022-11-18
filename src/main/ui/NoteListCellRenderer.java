package ui;

import model.Note;

import javax.swing.*;
import java.awt.*;

// Represents a cell for a note in a list
// Based off stackoverflow.com/questions/63597631/is-there-a-list-gui-component-in-java-8-swing
public class NoteListCellRenderer extends JPanel implements ListCellRenderer<Note> {

    private JLabel fileNameLabel;
    private JLabel statusLabel;
    private JLabel pathLabel;

    //MODIFIES: this
    //EFFECTS: constructs the cell
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

    //MODIFIES: this
    //EFFECTS: sets value of labels based on fields in note and returns cell
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
