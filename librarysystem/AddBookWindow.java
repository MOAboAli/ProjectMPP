package librarysystem;

import business.*;
import business.rulesets.RuleSetFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JPanel implements LibWindow  {
    ControllerInterface ci = new SystemController();
    public final static AddBookWindow INSTANCE =new AddBookWindow();

    //public final static int WIDTH = (int) (0.8 * screenSize.width);
    //public final static int HEIGHT = (int) (0.8 * screenSize.height);

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
    private DefaultTableModel authorTableModel;



    public AddBookWindow()
    {
        this.authors = new ArrayList<>();

        //setTitle("Add Book");
        //setSize(800, 600);
        setSize(AdminDashboard.WIDTH * 50 / 100, AdminDashboard.HEIGHT);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);

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


        String[] columnNames = {"First Name", "Last Name", "Telephone", "Bio", "Street", "City", "State", "Zip"};
        authorTableModel = new DefaultTableModel(columnNames, 0);
        JTable authorTable = new JTable(authorTableModel);

        mainPanel = new JPanel(new BorderLayout(5, 5));


        JPanel panel = new JPanel(new GridLayout(18, 2, 5, 5));  // 14 rows, 2 columns, 5px gaps
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Max Checkout Length:"));
        panel.add(maxCheckoutLengthField);
        panel.add(new JLabel("Number of Copies:"));
        panel.add(numberOfCopiesField);

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        //Author form
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

        //panel.add(new JLabel(""));
        //panel.add(new JScrollPane(authorTable));


        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(addButton);


        // Panel for the authors table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(authorTable), BorderLayout.CENTER);

        mainPanel.add(panel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);



        //add(panel);
        //add(tablePanel);

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

    @Override
    public void closeWnidow() {

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

        authorTableModel.addRow(new Object[]{
                firstName, lastName, telephone, bio, street, city, state, zip
        });


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

        try {
            RuleSetFactory.getRuleSet(this).applyRules(AddBookWindow.INSTANCE);

            // add code
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
            authorTableModel.setRowCount(0);  // Clear the table
        }
        catch (Exception ex)
        {

        }

    }


    public JTextField[] getAllFields() {
        return new JTextField[]{


         isbnField,
        titleField,
        // authorFirstNameField,
        //authorLastNameField,
        // authorTelephoneField,
        // authorBioField,
        // addressStreetField,
        // addressCityField,
        // addressStateField,
         //addressZipField;
                maxCheckoutLengthField,
         numberOfCopiesField

        };
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
