package librarysystem;

import Exception.BookNotFoundException;
import Exception.MemberNotFoundException;
import Exception.NoBooksCopiesException;
import business.BookCopy;
import business.CheckoutEntry;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutBooksWindow extends JPanel {


    private JTextField textField;
    private JTextField textField2;
    private JButton button;
    private JTable table;
    private DefaultTableModel tableModel;

    public CheckOutBooksWindow() {
        SystemController system = new SystemController();
        // Set up the JFrame
        //setTitle("Check Out Books");
        //setSize(1024, 500);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Create a JPanel for the input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Add a label
        JLabel label = new JLabel("Member ID:");
        inputPanel.add(label);

        // Add a text field
        textField = new JTextField(20);
        inputPanel.add(textField);

        JLabel label2 = new JLabel("ISBN:");
        inputPanel.add(label2);

        // Add a text field
        textField2 = new JTextField(20);
        inputPanel.add(textField2);

        // Add a button
        button = new JButton("-- Check Out --");
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
        system.getAllCheckOutRecord().forEach(checkoutentry -> {
            tableModel.addRow(new Object[]{
                    checkoutentry.getBookCopyNumber(),
                    checkoutentry.getBookIsbnNumber()+" " +checkoutentry.getBookCopy().getBook().getTitle(),
                    checkoutentry.getCheckoutDate(),
                    checkoutentry.getDueDate(),
                    checkoutentry.getLibraryMemberFullName()
            });
        });


        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                String inputText2 = textField2.getText();
                if (!inputText.isEmpty() && !inputText2.isEmpty()) {


                    try {

                        system.CheckBook(inputText2);
                        BookCopy bookcopy=system.CheckAvailability(inputText2);
                        CheckoutEntry checkoutentry=system.PutCheckOutEntry(bookcopy,system.CheckMemeber(inputText));
                        tableModel.addRow(new Object[]{checkoutentry.getBookCopyNumber(),
                                checkoutentry.getBookIsbnNumber()+" " +checkoutentry.getBookCopy().getBook().getTitle(),
                                checkoutentry.getCheckoutDate(), checkoutentry.getDueDate(), checkoutentry.getLibraryMemberFullName()});


                    } catch (BookNotFoundException | MemberNotFoundException | NoBooksCopiesException  ex ) {
                        JOptionPane.showMessageDialog(null, "Error:"+ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Fields Can't be Empty.......................", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
