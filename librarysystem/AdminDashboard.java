package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboard extends JFrame implements  LibWindow {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    public final static AdminDashboard INSTANCE =new AdminDashboard();


    public AdminDashboard() {
        // Frame settings
        setTitle("Admin Dashboard");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout());

        // Left panel for navigation
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(9, 1));
        leftPanel.setPreferredSize(new Dimension(WIDTH * 40 / 100, getHeight()));
        leftPanel.setBackground(Color.LIGHT_GRAY);

        // Add admin greeting
        JLabel adminLabel = new JLabel("Hello admin!", JLabel.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 20));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(adminLabel);

        // Add navigation buttons
        String[] navItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        for (String item : navItems) {
            JButton button = createCustomButton(item, "", new Font("Roboto Mono", Font.PLAIN, 12), e -> {
                System.out.println(item + " clicked");
            });
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.add(button);
        }

        // Add settings icon
        JButton settingsLabel = createCustomButton("Logout", "C:\\Users\\salah\\OneDrive\\Desktop\\logout.png", new Font("Roboto Mono", Font.PLAIN, 12), e -> {
            System.out.println("Logout clicked");
        });
        settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(settingsLabel);

        // Right panel for user form
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setLayout(new GridLayout(7, 2, 10, 10));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add form fields
        String[] labels = {"Tag ID", "Neptun", "Név", "Könyvek száma", "Email", "Összes díj"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            rightPanel.add(new JLabel(labels[i]));
            textFields[i] = new JTextField();
            rightPanel.add(textFields[i]);
        }

        // Add action buttons
        JButton deleteButton = new JButton("Tag törlése");


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(deleteButton);


        // Add right panel and button panel into a container
        JPanel rightContainer = new JPanel(new BorderLayout());
        rightContainer.add(new AddNewMemberWindow(), BorderLayout.CENTER);
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightContainer);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(200); // Set initial divider location
        splitPane.setEnabled(false);

        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createCustomButton(String text, String iconPath, Font font, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(font); // Set custom font
        button.setContentAreaFilled(false); // Remove button background
        button.setBorderPainted(false); // Remove button border
        button.setFocusPainted(false); // Remove focus painted border
        button.setOpaque(false);
        ImageIcon originalIcon = new ImageIcon(iconPath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        button.setIcon(scaledIcon);
        button.setIconTextGap(8);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set cursor to hand
        button.addActionListener(listener);

        // Mouse listener for hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE); // Change text color on hover
                button.setOpaque(true); // Make background opaque
                button.setBackground(new Color(58, 145, 232)); // Light lavender background on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK); // Change text color back to normal
                button.setBorderPainted(false); // Hide border
                button.setOpaque(false); // Make background transparent
                button.setBackground(null); // Reset background
            }
        });// Make button background transparent
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDashboard();
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
}
