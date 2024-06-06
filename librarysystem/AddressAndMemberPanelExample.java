package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AddressAndMemberPanelExample extends JFrame {

    public AddressAndMemberPanelExample() {
        setTitle("Address and Member Data Panel Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BoxLayout to hold the two sections
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Address Panel
        JPanel addressPanel = new JPanel(new GridLayout(4,2,10,10));
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address Data"));

        // Address fields
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(25);

        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField(25);

        JLabel stateLabel = new JLabel("State:");
        JTextField stateField = new JTextField(25);

        JLabel zipLabel = new JLabel("ZIP Code:");
        JTextField zipField = new JTextField(25);

        addressPanel.add(addressLabel);
        addressPanel.add(addressField);
        addressPanel.add(cityLabel);
        addressPanel.add(cityField);
        addressPanel.add(stateLabel);
        addressPanel.add(stateField);
        addressPanel.add(zipLabel);
        addressPanel.add(zipField);

        // Member Panel
        JPanel memberPanel = new JPanel(new GridLayout(4,2, 10, 10));
        memberPanel.setBorder(BorderFactory.createTitledBorder("Member Data"));

        // Member fields
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(25);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(25);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(25);

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(25);

        //Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JButton addMemberButton = new JButton("Add New Member");
        buttonPanel.add(addMemberButton, BorderLayout.CENTER);

        memberPanel.add(firstNameLabel);
        memberPanel.add(firstNameField);
        memberPanel.add(lastNameLabel);
        memberPanel.add(lastNameField);
        memberPanel.add(emailLabel);
        memberPanel.add(emailField);
        memberPanel.add(phoneLabel);
        memberPanel.add(phoneField);

        // Adding both panels to the main panel
        mainPanel.add(addressPanel);
        mainPanel.add(memberPanel);
        mainPanel.add(addMemberButton);

        // Adding the main panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddressAndMemberPanelExample example = new AddressAndMemberPanelExample();
            example.setVisible(true);
        });
    }
}
