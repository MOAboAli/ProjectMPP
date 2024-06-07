package librarysystem;

import Exception.AddNewMemberException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class AddNewMemberWindow extends JPanel {
    SystemController systemController = new SystemController();

    private JTextField streetField, cityField, stateField,
            zipField, memberIDField, fnameField, lnameField, telField;
    private JButton addButton;

    public AddNewMemberWindow() {
        setSize(AdminDashboard.WIDTH * 50 / 100, AdminDashboard.HEIGHT);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(getAddressSection());
        mainPanel.add(getMemberSection());
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(getSize().width, (int) (AdminDashboard.HEIGHT * 0.5)));
        mainPanel.add(emptyPanel);
        add(mainPanel, BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);
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

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        addButton = new JButton("Add New Member");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> addUser());
        return buttonPanel;
    }

    private JPanel getAddressSection() {
        JPanel addressPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address Data"));

        // Address fields
        JLabel addressLabel = new JLabel("Street:");
        streetField = new JTextField(25);

        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(25);

        JLabel stateLabel = new JLabel("State:");
        stateField = new JTextField(25);

        JLabel zipLabel = new JLabel("ZIP Code:");
        zipField = new JTextField(25);

        addressPanel.add(addressLabel);
        addressPanel.add(streetField);
        addressPanel.add(cityLabel);
        addressPanel.add(cityField);
        addressPanel.add(stateLabel);
        addressPanel.add(stateField);
        addressPanel.add(zipLabel);
        addressPanel.add(zipField);

        return addressPanel;
    }

    private JPanel getMemberSection() {
        JPanel memberPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        memberPanel.setBorder(BorderFactory.createTitledBorder("Member Data"));

        // Member fields
        JLabel firstNameLabel = new JLabel("First Name:");
        fnameField = new JTextField(25);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lnameField = new JTextField(25);

        JLabel emailLabel = new JLabel("MemberID:");
        memberIDField = new JTextField(25);

        JLabel phoneLabel = new JLabel("Tel:");
        telField = new JTextField(25);

        memberPanel.add(firstNameLabel);
        memberPanel.add(fnameField);
        memberPanel.add(lastNameLabel);
        memberPanel.add(lnameField);
        memberPanel.add(emailLabel);
        memberPanel.add(memberIDField);
        memberPanel.add(phoneLabel);
        memberPanel.add(telField);

        return memberPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddNewMemberWindow example = new AddNewMemberWindow();
            example.setVisible(true);
        });
    }
}
