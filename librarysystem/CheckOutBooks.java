package librarysystem;
import business.BookCopy;
import business.CheckoutEntry;
import business.SystemController;
import Exception.BookNotFoundException;
import Exception.MemberNotFoundException;
import Exception.NoBooksCopiesException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutBooks extends JFrame {


    private JTextField textField;
    private JTextField textField2;
    private JButton button;
    private JTable table;
    private DefaultTableModel tableModel;

    public CheckOutBooks() {
        // Set up the JFrame
        setTitle("Check Out Books");
        setSize(1024, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        add(inputPanel, BorderLayout.NORTH);

        // Set up the table model and table
        tableModel = new DefaultTableModel(new Object[]{"OutPut"}, 0);
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
                        tableModel.addRow(new Object[]{
                              "Copy No. "+checkoutentry.getBookCopyNumber()+  "of the book: "+checkoutentry.getBookIsbnNumber()+" has been checked out on "+
                                      checkoutentry.getCheckoutDate() +" until "+checkoutentry.getDueDate()+" for  "+checkoutentry.getLibraryMemberFullName()
                        });

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

    public static void main(String[] args) {
        // Run the Swing application on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CheckOutBooks().setVisible(true);
            }
        });
    }
}
