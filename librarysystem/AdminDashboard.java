package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    private JButton selectedButton;

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
            JButton button = createCustomButton(item, "", new Font("Roboto Mono", Font.PLAIN, 12));
            button.addActionListener(e -> {
                if (selectedButton!=null){
                    selectedButton.setForeground(Color.BLACK); // Change text color back to normal
                    selectedButton.setBorderPainted(false); // Hide border
                    selectedButton.setOpaque(false); // Make background transparent
                    selectedButton.setBackground(null); // Reset background
                }
                System.out.println(item + " clicked");
                selectedButton = button;
                button.setForeground(Color.WHITE); // Change text color on hover
                button.setOpaque(true); // Make background opaque
                button.setBackground(Color.GRAY);
            });
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.add(button);
        }

        // Add settings icon
        JButton settingsLabel = createCustomButton("Logout", "C:\\Users\\salah\\OneDrive\\Desktop\\logout.png", new Font("Roboto Mono", Font.PLAIN, 12));
        settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(settingsLabel);

        // Right panel for user form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rightPanel.add(new AddNewMemberWindow());


        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(200); // Set initial divider location
        splitPane.setEnabled(false);

        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createCustomButton(String text, String iconPath, Font font) {
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

        // Mouse listener for hover effect
//        button.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                button.setForeground(Color.WHITE); // Change text color on hover
//                button.setOpaque(true); // Make background opaque
//                button.setBackground(new Color(84, 84, 84)); // Light lavender background on hover
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                button.setForeground(Color.BLACK); // Change text color back to normal
//                button.setBorderPainted(false); // Hide border
//                button.setOpaque(false); // Make background transparent
//                button.setBackground(null); // Reset background
//            }
//        });// Make button background transparent
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
}
