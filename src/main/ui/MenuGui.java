package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

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
        handleOpenBtn();

        toolBarPane.add(saveBtn);
        toolBarPane.add(loadBtn);
        toolBarPane.add(removeBtn);
        toolBarPane.add(openBtn);
        mainPanel.add(toolBarPane, BorderLayout.PAGE_START);
    }

    protected void initializeList() {
        listModel = new DefaultListModel();

        loadList();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        mainPanel.add(listScrollPane, BorderLayout.CENTER);
    }

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeBtn.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeBtn.setEnabled(true);
            }
        }
    }

    protected abstract void handleSaveBtn();

    protected abstract void handleLoadBtn();

    protected abstract void handleAddBtn();

    protected abstract void handleRemoveBtn();

    protected abstract void handleOpenBtn();

    protected abstract void loadList();

    protected abstract void init();
}
