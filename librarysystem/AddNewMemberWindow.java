package librarysystem;

import Exception.AddNewMemberException;
import business.SystemController;
import business.rulesets.RuleException;
import business.rulesets.RuleSetFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddNewMemberWindow extends JPanel {
    SystemController systemController = new SystemController();

    private JTextField streetField, cityField, stateField,
            zipField, memberIDField, fnameField, lnameField, telField;
    private JButton addButton;
    private DefaultTableModel tableModel;
    private JTable table;


    public JTextField[] getAllFields() {
        return new JTextField[]{stateField, cityField, stateField, zipField, memberIDField, fnameField, lnameField, telField};
    }

    public JTextField getMemberIDField() {
        return memberIDField;
    }

    public AddNewMemberWindow() {
        setSize(AdminDashboard.WIDTH * 50 / 100, AdminDashboard.HEIGHT);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(getAddressSection());
        mainPanel.add(getMemberSection());
        mainPanel.add(Setthetable());
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(getSize().width, (int) (AdminDashboard.HEIGHT * 0.5)));
//        mainPanel.add(emptyPanel);
        add(mainPanel, BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);


    }

    private void addNewMember() {
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
        buttonPanel.add(addButton, BorderLayout.CENTER);
        addButton.addActionListener(e -> {
            try {
                RuleSetFactory.getRuleSet(this).applyRules(AddNewMemberWindow.this);
                addNewMember();
                updateTable();
            } catch (RuleException exception) {
                JOptionPane.showMessageDialog(AddNewMemberWindow.this, exception.getMessage());
            }
        });
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

    private JScrollPane Setthetable(){
        String[] columnNames = {"Member ID", "First name", "Last name", "Address","Telephone"};
        //tableModel = new DefaultTableModel(columnNames, 0);
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        systemController.getAllMember().forEach(item -> {
            tableModel.addRow(new Object[]{
                    item.getMemberId(),item.getFirstName(),item.getLastName(),item.getAddress(),item.getTelephone()
            });
        });
        return  scrollPane;
    }

    private void updateTable(){
        tableModel.setRowCount(0);
        systemController.getAllMember().forEach(item -> {
            tableModel.addRow(new Object[]{
                    item.getMemberId(),item.getFirstName(),item.getLastName(),item.getAddress(),item.getTelephone()
            });
        });
    }
}
