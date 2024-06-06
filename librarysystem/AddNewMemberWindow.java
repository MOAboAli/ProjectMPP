package librarysystem;

import business.AddNewMemberException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddNewMemberWindow extends JFrame {


    private boolean isInitialized = false;

    SystemController systemController = new SystemController();


    private JTextField streetField, cityField, stateField,
            zipField, memberIDField, fnameField, lnameField, telField;
    private JButton addButton;
    private ArrayList<User> users;

    public AddNewMemberWindow() {
        // Initialize the user list
        users = new ArrayList<>();

        // Set up the frame
        setTitle("User Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        // Add name field
        panel.add(new JLabel("Street:"));
        streetField = new JTextField();
        panel.add(streetField);

        // Add age field
        panel.add(new JLabel("city:"));
        cityField = new JTextField();
        panel.add(cityField);

        // Add email field
        panel.add(new JLabel("state:"));
        stateField = new JTextField();
        panel.add(stateField);

        panel.add(new JLabel("zipField:"));
        zipField = new JTextField();
        panel.add(zipField);

        panel.add(new JLabel("memberID:"));
        memberIDField = new JTextField();
        panel.add(memberIDField);

        panel.add(new JLabel("First name:"));
        fnameField = new JTextField();
        panel.add(fnameField);

        panel.add(new JLabel("Last name:"));
        lnameField = new JTextField();
        panel.add(lnameField);

        panel.add(new JLabel("tel:"));
        telField = new JTextField();
        panel.add(telField);

        // Add button
        addButton = new JButton("Add User");
        panel.add(addButton);

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        // Add panel to frame
        add(panel);

        // Set frame visibility
        setVisible(true);
    }

    private void addUser() {
        // Get user data from fields

        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipField.getText();
        String memberId = memberIDField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String tel = telField.getText();


        try {
            systemController.addNewMember(street, city, state, zip, memberId, fname, lname, tel);
            JOptionPane.showMessageDialog(this, "User added!");
            streetField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");
            memberIDField.setText("");
            fnameField.setText("");
            lnameField.setText("");
            telField.setText("");
        } catch (AddNewMemberException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


        // Show confirmation

        // Clear fields

    }

    public static void main(String[] args) {
        // Run the form
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddNewMemberWindow();
            }
        });
    }

    // Inner class to represent a user
    class User {
        private String name;
        private int age;
        private String email;

        public User(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    '}';
        }
    }


}
