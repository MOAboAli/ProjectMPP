package librarysystem;

import utils.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginTempScreen extends JFrame {

    public final static int WIDTH = 1024;
    public final static int HEIGHT = 720;

    public LoginTempScreen() {
        setTitle("Login Screen");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());



        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension((WIDTH * 40) / 100, HEIGHT));
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);


        // Login Header
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel loginLabel = new JLabel("Login", JLabel.LEADING);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftPanel.add(loginLabel, gbc);

        // Welcome Message
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel welcomeLabel = new JLabel("Please login to your account", JLabel.LEFT);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(welcomeLabel, gbc);

        // Email field
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JLabel emailLabel = new JLabel("Email");
        leftPanel.add(emailLabel, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JTextField emailField = createCustomTextField("email");
        leftPanel.add(emailField, gbc);

        // Password field
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JLabel passwordLabel = new JLabel("Password");
        leftPanel.add(passwordLabel, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JPasswordField passwordField = new JPasswordField();
        leftPanel.add(passwordField, gbc);


        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BorderLayout());
        boxPanel.setBorder(new LineBorder(Color.GRAY));

        // Create the label
        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create the text field
        JTextField textField = createCustomTextField("@example.com");
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add the label and text field to the panel
        boxPanel.add(titleLabel, BorderLayout.NORTH);
        boxPanel.add(textField, BorderLayout.CENTER);

        leftPanel.add(boxPanel, gbc);

        // Buttons
        gbc.gridy = 8;
        gbc.gridx = 1;
        JButton loginButton = new JButton("Login");
        leftPanel.add(loginButton, gbc);


        add(leftPanel, BorderLayout.WEST);

        // Right panel (image)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel(scaleImage(Utils.assets_dir + "Untitled.png", (WIDTH * 40) / 100, HEIGHT / 2)); // Add your image path
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.CENTER);


    }

    private JTextField createCustomTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setText(placeholder);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    private ImageIcon scaleImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginTempScreen loginScreen = new LoginTempScreen();
            loginScreen.setVisible(true);
        });
    }
}
