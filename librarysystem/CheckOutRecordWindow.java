package librarysystem;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecordWindow extends JPanel {
    private JTextField empIdField;
    private JButton submitButton;
    private JPanel resultsPanel;

    public CheckOutRecordWindow() {
        // Create components

        setLayout(new BorderLayout());
        empIdField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultsPanel = new JPanel();

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Employee ID:"));
        topPanel.add(empIdField);
        topPanel.add(submitButton);
        add(topPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);

        String[] columnNames = {"Copy No.", "Book ISBN", "Checkout Date", "Due Date", "Library Member"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empId = empIdField.getText();
                CheckoutRecord record = new SystemController().getRecordByEmpId(empId);


                // Clear previous results
                tableModel.setRowCount(0);
                resultsPanel.removeAll();

                if (record != null) {
                    List<CheckoutEntry> ListEntry = record.readCheckoutEntries();
                    if(ListEntry.size() == 0){
                        JLabel messageLabel = new JLabel("No records found for employee ID: " + empId);
                        resultsPanel.add(messageLabel);
                    }
                    for(CheckoutEntry Entry : ListEntry){
                        tableModel.addRow(new Object[]{Entry.getBookCopyNumber(),
                                Entry.getBookIsbnNumber()+" " +Entry.getBookCopy().getBook().getTitle(),
                                Entry.getCheckoutDate(), Entry.getDueDate(), Entry.getLibraryMemberFullName()});
                    }
                    //
                } else {
                    JOptionPane.showMessageDialog(null, "No Record was found.............", "Warning", JOptionPane.WARNING_MESSAGE);

                }

                // Revalidate and repaint the panel
                resultsPanel.revalidate();
                resultsPanel.repaint();
            }
        });

        // Set window properties

        setVisible(true);


    }
}