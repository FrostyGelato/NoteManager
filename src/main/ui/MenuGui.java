package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// A generic menu window that displays a list of items
public abstract class MenuGui extends JFrame implements ListSelectionListener {

    protected JPanel mainPanel;

    protected JList list;
    protected DefaultListModel listModel;
    protected JPanel toolBarPane;
    protected JPanel bottomBarPane;

    protected JButton saveBtn;
    protected JButton loadBtn;
    protected JButton addBtn;
    protected JButton removeBtn;
    protected JButton openBtn;
    protected JTextField nameField;

    //REQUIRES: title and itemName are non-empty strings
    //EFFECTS: constructs a menu window
    protected MenuGui(String title, String itemName) {
        super(title);

        setPreferredSize(new Dimension(600, 600));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        init();
        initializeToolBar();
        initializeList();
        initalizeBottomBar(itemName);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: constructs the set of buttons at the top
    protected void initializeToolBar() {
        toolBarPane = new JPanel();
        toolBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        toolBarPane.setLayout(new FlowLayout());

        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        removeBtn = new JButton("Remove");
        openBtn = new JButton("Open");
        handleSaveBtn();
        handleLoadBtn();
        handleRemoveBtn();
        removeBtn.setEnabled(false);
        handleOpenBtn();
        openBtn.setEnabled(false);

        toolBarPane.add(saveBtn);
        toolBarPane.add(loadBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }

    //MODIFIES: this
    //EFFECTS: constructs a list
    protected void initializeList() {
        listModel = new DefaultListModel();

        loadList();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setSelectionBackground(Color.LIGHT_GRAY);

        JScrollPane listScrollPane = new JScrollPane(list);

        //Based off stackoverflow.com/questions/20048108/disabling-a-button-when-a-list-item-isnt-selected-in-java
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1) {
                        removeBtn.setEnabled(false);
                        openBtn.setEnabled(false);
                    } else {
                        removeBtn.setEnabled(true);
                        openBtn.setEnabled(true);
                    }
                }
            }
        });

        mainPanel.add(listScrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: constructs the ui elements for adding an item
    protected void initalizeBottomBar(String itemName) {
        bottomBarPane = new JPanel();
        bottomBarPane.setBorder(new EmptyBorder(2, 2, 2, 2));
        bottomBarPane.setLayout(new FlowLayout());

        JLabel nameFieldLabel = new JLabel("Enter " + itemName + " name:");
        nameField = new JTextField(10);
        addBtn = new JButton("Add");
        handleAddBtn();

        bottomBarPane.add(nameFieldLabel);
        bottomBarPane.add(nameField);
        bottomBarPane.add(addBtn);
        mainPanel.add(bottomBarPane, BorderLayout.PAGE_END);
    }

    //MODIFIES: this
    //EFFECTS: enables or disables remove button when value of selection changes
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                removeBtn.setEnabled(false);

            } else {
                removeBtn.setEnabled(true);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: set behaviour of save button
    protected abstract void handleSaveBtn();

    //MODIFIES: this
    //EFFECTS: set behaviour of load button
    protected abstract void handleLoadBtn();

    //MODIFIES: this
    //EFFECTS: set behaviour of add button
    protected abstract void handleAddBtn();

    //MODIFIES: this
    //EFFECTS: set behaviour of remove button
    protected abstract void handleRemoveBtn();

    //MODIFIES: this
    //EFFECTS: set behaviour of open button
    protected abstract void handleOpenBtn();

    //MODIFIES: this
    //EFFECTS: add items to list
    protected abstract void loadList();

    //MODIFIES: this
    //EFFECTS: sets up other fields
    protected abstract void init();
}
