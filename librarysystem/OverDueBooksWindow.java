package librarysystem;

import Exception.BookNotFoundException;
import Exception.MemberNotFoundException;
import Exception.NoBooksCopiesException;
import business.BookCopy;
import business.CheckoutEntry;
import business.SystemController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverDueBooksWindow extends JPanel {


    private JTextField textField;
    private JTextField textField2;
    private JButton button;
    private JTable table;
    private DefaultTableModel tableModel;

    public OverDueBooksWindow() {
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.X_AXIS));
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
        String[] columnNames = {"Copy No.", "Book ISBN", "Checkout Date", "Due Date", "Library Member"};
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                String inputText2 = textField2.getText();
                if (!inputText.isEmpty() && !inputText2.isEmpty()) {

                    SystemController system = new SystemController();
                    try {

                        system.CheckBook(inputText2);
                        system.CheckMemeber(inputText);
                        BookCopy bookcopy=system.CheckAvailability(inputText2);
                        CheckoutEntry checkoutentry=system.PutCheckOutEntry(bookcopy,inputText);
                        tableModel.addRow(new Object[]{checkoutentry.getBookCopyNumber(),
                                checkoutentry.getBookIsbnNumber(),
                                checkoutentry.getCheckoutDate(), checkoutentry.getDueDate(), checkoutentry.getLibraryMemberFullName()});


                    } catch (BookNotFoundException | MemberNotFoundException | NoBooksCopiesException  ex ) {
                        tableModel.addRow(new Object[]{ex.getMessage()});
                    }
                }
                else {
                    tableModel.addRow(new Object[]{"Fields Can't be Empty......................."});

                }
            }
        });
    }
}
