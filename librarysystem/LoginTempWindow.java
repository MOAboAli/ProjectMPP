package librarysystem;

import javax.swing.*;
import java.awt.*;

public class LoginTempWindow extends JFrame implements LibWindow {


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


    public LoginTempWindow() {
        // Set up the frame
        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Left panel for the login form
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Logo
        JLabel logo = new JLabel("Welcome to Library System");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setIcon(new ImageIcon(LibrarySystem.ResourcesPath + "miu-logo-horizontal.png"));  // replace with your logo path
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(logo);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, Please login in to your account");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(welcomeLabel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Email field
        JLabel emailLabel = new JLabel("Username");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(emailLabel);

        JTextField emailField = new JTextField("101");
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        leftPanel.add(emailField);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField("xyz");
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        leftPanel.add(passwordField);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Forgot Password link
        //JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        //forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        ///forgotPasswordLabel.setForeground(Color.BLUE);
        //forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //forgotPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //leftPanel.add(forgotPasswordLabel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        //JButton signUpButton = new JButton("Sign Up");
        //buttonPanel.add(signUpButton);

        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        leftPanel.add(buttonPanel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Social login
        //JPanel socialPanel = new JPanel();
        //socialPanel.setBackground(Color.WHITE);
        //socialPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        //socialPanel.add(new JLabel("or login with"));

        //JButton facebookButton = new JButton("Facebook");
        //socialPanel.add(facebookButton);

        //JButton gmailButton = new JButton("Gmail");
        //socialPanel.add(gmailButton);

        //JButton linkedinButton = new JButton("Linkedin");
        //socialPanel.add(linkedinButton);

        //leftPanel.add(socialPanel);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Right panel for the image
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for stretching
        rightPanel.setBackground(new Color(38, 109, 211));  // Set background color



        // Add your image to the right panel
        JLabel imageLabel = new JLabel(new ImageIcon(LibrarySystem.ResourcesPath + "assets/LoginBG.png"));  // replace with your image path
        //rightPanel.add(imageLabel, BorderLayout.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Center horizontally
        imageLabel.setVerticalAlignment(JLabel.CENTER); // Center vertically


        // Configure GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Stretch horizontally
        gbc.weighty = 1.0; // Stretch vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically

        rightPanel.add(imageLabel, gbc); // Add the label to the panel with constraints

        //mainPanel.add(rightPanel, gbc);//BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.CENTER); // Add the right panel to the main panel



        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginTempWindow().setVisible(true);
        });
    }

}
