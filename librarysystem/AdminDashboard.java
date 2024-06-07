package librarysystem;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class AdminDashboard extends JFrame implements  LibWindow {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public final static int WIDTH = (int) (0.8 * screenSize.width);
    public final static int HEIGHT = (int) (0.8 * screenSize.height);
    private JButton selectedButton;
    JPanel rightPanel;
    JPanel rightContainer;

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
        leftPanel.setLayout(new BorderLayout(10, 10));
        JPanel leftNavPanel = new JPanel();

        leftNavPanel.setLayout(new GridLayout(9, 1));
        leftNavPanel.setPreferredSize(new Dimension(WIDTH * 40 / 100, getHeight()));
        leftNavPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(leftNavPanel, BorderLayout.CENTER);


        // Add admin greeting
        JLabel adminLabel = new JLabel("Hello admin!", JLabel.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 20));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftNavPanel.add(adminLabel);

        // Add navigation buttons
        HashMap<String, JPanel> navItems = new HashMap<>();
        navItems.put("Book Check Out", new CheckOutBooksWindow());
        navItems.put("New Member", new AddNewMemberWindow());
        navItems.put("Add Book", new AddBookWindow());
        navItems.put("Check Member Records", new CheckOutRecordWindow());

        for (HashMap.Entry<String, JPanel> item : navItems.entrySet()) {
            JButton button = createCustomButton(item.getKey(), "", new Font("Roboto Mono", Font.PLAIN, 12));
            button.addActionListener(e -> {
                if (selectedButton != null) {
                    selectedButton.setForeground(Color.BLACK); // Change text color back to normal
                    selectedButton.setBorderPainted(false); // Hide border
                    selectedButton.setOpaque(false); // Make background transparent
                    selectedButton.setBackground(null); // Reset background
                }
                selectedButton = button;
                button.setForeground(Color.WHITE); // Change text color on hover
                button.setOpaque(true); // Make background opaque
                button.setBackground(Color.GRAY);
                if (item.getValue() != null)
                    updateRightPanel(item.getValue());
            });
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftNavPanel.add(button);
        }

        // Add settings icon
        JButton settingsLabel = createCustomButton("Logout", Utils.assets_dir + "logout.png", new Font("Roboto Mono", Font.PLAIN, 12));
        settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(settingsLabel, BorderLayout.SOUTH);

        // Right panel for user form
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);


        // Add right panel and button panel into a container
        rightContainer = new JPanel(new BorderLayout());
        rightContainer.add(new JPanel(), BorderLayout.BEFORE_FIRST_LINE);
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightContainer);
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

    private void updateRightPanel(JPanel newPanel) {
        rightContainer.removeAll(); // Remove current panel
        rightContainer.add(newPanel, BorderLayout.CENTER); // Add new panel
        rightContainer.revalidate(); // Revalidate the container
        rightContainer.repaint(); // Repaint the container
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
