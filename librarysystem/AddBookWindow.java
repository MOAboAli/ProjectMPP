package librarysystem;

import business.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JFrame implements LibWindow  {
    ControllerInterface ci = new SystemController();
    public final static AddBookWindow INSTANCE =new AddBookWindow();

    JPanel mainPanel;
    JMenuBar menuBar;
    JMenu options;
    JMenuItem login, allBookIds, allMemberIds;
    String pathToImage;
    private boolean isInitialized = false;


    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorFirstNameField;
    private JTextField authorLastNameField;
    private JTextField authorTelephoneField;
    private JTextField authorBioField;
    private JTextField addressStreetField;
    private JTextField addressCityField;
    private JTextField addressStateField;
    private JTextField addressZipField;
    private JTextField maxCheckoutLengthField;
    private JTextField numberOfCopiesField;
    private JButton addAuthorButton;
    private JButton addButton;


    private List<Author> authors;


    public AddBookWindow()
    {
        this.authors = new ArrayList<>();

        setTitle("Add Book");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        isbnField = new JTextField(15);
        titleField = new JTextField(15);
        authorFirstNameField = new JTextField(15);
        authorLastNameField = new JTextField(15);
        authorTelephoneField = new JTextField(15);
        authorBioField = new JTextField(15);
        addressStreetField = new JTextField(15);
        addressCityField = new JTextField(15);
        addressStateField = new JTextField(15);
        addressZipField = new JTextField(15);
        maxCheckoutLengthField = new JTextField(15);
        numberOfCopiesField = new JTextField(15);
        addAuthorButton = new JButton("Add Author");
        addButton = new JButton("Add Book");

        JPanel panel = new JPanel(new GridLayout(14, 2, 5, 5));  // 14 rows, 2 columns, 5px gaps
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author First Name:"));
        panel.add(authorFirstNameField);
        panel.add(new JLabel("Author Last Name:"));
        panel.add(authorLastNameField);
        panel.add(new JLabel("Author Telephone:"));
        panel.add(authorTelephoneField);
        panel.add(new JLabel("Author Bio:"));
        panel.add(authorBioField);
        panel.add(new JLabel("Address Street:"));
        panel.add(addressStreetField);
        panel.add(new JLabel("Address City:"));
        panel.add(addressCityField);
        panel.add(new JLabel("Address State:"));
        panel.add(addressStateField);
        panel.add(new JLabel("Address Zip:"));
        panel.add(addressZipField);
        panel.add(new JLabel(""));
        panel.add(addAuthorButton);
        panel.add(new JLabel("Max Checkout Length:"));
        panel.add(maxCheckoutLengthField);
        panel.add(new JLabel("Number of Copies:"));
        panel.add(numberOfCopiesField);
        panel.add(new JLabel(""));
        panel.add(addButton);

        add(panel);

        addAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthor();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    private void addAuthor() {
        String firstName = authorFirstNameField.getText();
        String lastName = authorLastNameField.getText();
        String telephone = authorTelephoneField.getText();
        String bio = authorBioField.getText();
        String street = addressStreetField.getText();
        String city = addressCityField.getText();
        String state = addressStateField.getText();
        String zip = addressZipField.getText();
        Address address = new Address(street, city, state, zip);
        authors.add(new Author(firstName, lastName, telephone, address, bio));
        JOptionPane.showMessageDialog(this, "Author added: " + firstName + " " + lastName);
        authorFirstNameField.setText("");
        authorLastNameField.setText("");
        authorTelephoneField.setText("");
        authorBioField.setText("");
        addressStreetField.setText("");
        addressCityField.setText("");
        addressStateField.setText("");
        addressZipField.setText("");
    }

    private void addBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        int maxCheckoutLength = Integer.parseInt(maxCheckoutLengthField.getText());
        int numberOfCopies = Integer.parseInt(numberOfCopiesField.getText());

        Book book = new Book(isbn, title,maxCheckoutLength, authors, numberOfCopies);
        ci.addBook(book);

        JOptionPane.showMessageDialog(this, "Book added successfully!");

        // Clear the fields
        isbnField.setText("");
        titleField.setText("");
        maxCheckoutLengthField.setText("");
        numberOfCopiesField.setText("");
        authors.clear();
    }


/*
    private void addBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        //String authors = authorsField.getText();
        List<Author> authors = new ArrayList<>();
        int maxCheckoutLength = Integer.parseInt(maxCheckoutLengthField.getText());
        int numberOfCopies = Integer.parseInt(numberOfCopiesField.getText());

        Book book = new Book(isbn, title, maxCheckoutLength,authors, numberOfCopies);
        ci.addBook(book);

        JOptionPane.showMessageDialog(this, "Book added successfully!");

        // Clear the fields
        isbnField.setText("");
        titleField.setText("");
        authorsField.setText("");
        maxCheckoutLengthField.setText("");
        numberOfCopiesField.setText("");
    }

 */
}
