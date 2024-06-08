package librarysystem;

import business.SystemController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OverDueBooksWindow extends JPanel {


    private JTextField textField;
    private JTextField textField2;
    private JButton button;
    private JTable table;
    private DefaultTableModel tableModel;
    SystemController system;

    public OverDueBooksWindow() {
        system= new SystemController();
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBorder(new EmptyBorder(10,10,10,10));
        JLabel label2 = new JLabel("ISBN:");
        inputPanel.add(label2);

        // Add a text field
        textField2 = new JTextField(20);
        inputPanel.add(textField2);

        // Add a button
        button = new JButton("Search");
        inputPanel.add(button);

        // Add the input panel to the top of the frame
        add(inputPanel, BorderLayout.PAGE_START);

        // Set up the table model and table
        String[] columnNames = {"Library Member","Book title","Copy No.", "ISBN", "Checkout Date", "Due Date"};
        //tableModel = new DefaultTableModel(columnNames, 0);
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the button
        button.addActionListener(e -> searchOverDue(textField2.getText()));
    }

    private void searchOverDue(String isbn) {
        tableModel.setRowCount(0);
        table.removeAll();
        system.getOverDueEntries(isbn).forEach(checkoutentry -> {
            tableModel.addRow(new Object[]{
                    checkoutentry.getLibraryMemberFullName(),
                    checkoutentry.getBookCopy().getBook().getTitle(),
                    checkoutentry.getBookCopyNumber(),
                    checkoutentry.getBookIsbnNumber(),
                    checkoutentry.getCheckoutDate(),
                    checkoutentry.getDueDate()
            });
        });

        if(tableModel.getRowCount() ==0)
            JOptionPane.showMessageDialog(null, "No Record was found.............", "Warning", JOptionPane.WARNING_MESSAGE);

    }
}
