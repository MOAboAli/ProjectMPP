package librarysystem;

import java.awt.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.User;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame implements LibWindow {
	public static  LoginWindow INSTANCE = new LoginWindow();
	ControllerInterface ci = new SystemController();

	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	private JPanel container;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;

	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	@Override
	public void closeWnidow() {
		INSTANCE.dispose();
	}

	private JTextField messageBar = new JTextField();

	public void clear() {
		messageBar.setText("");
	}

	/* This class is a singleton */
	private LoginWindow() {}

	public static void initialize() {
		INSTANCE = new LoginWindow();
	}

	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// Create left panel for login form
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();

		leftPanel.add(upperHalf, BorderLayout.NORTH);
		leftPanel.add(middleHalf, BorderLayout.CENTER);
		//leftPanel.add(lowerHalf, BorderLayout.SOUTH);

		// Create right panel for image
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setBackground(new Color(38, 109, 211));  // Set background color

		JLabel imageLabel = new JLabel(new ImageIcon(LibrarySystem.ResourcesPath + "assets/LoginBG.png"));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);

		// Adjust image size to half the right panel
		imageLabel.setPreferredSize(new Dimension(400, 300));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		rightPanel.add(imageLabel, gbc);

		// Use JSplitPane to split left and right panels
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setDividerLocation(400); // Initial divider location

		mainPanel.add(splitPane, BorderLayout.CENTER);
		getContentPane().add(mainPanel);

		setSize(800, 600);
		setLocationRelativeTo(null); // Center the window on the screen
		setTitle("Login");

		isInitialized(true);
		pack();
	}

	private void defineUpperHalf() {
		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.add(topPanel, BorderLayout.NORTH);
		upperHalf.add(middlePanel, BorderLayout.CENTER);
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.HORIZONTAL);
		middleHalf.add(s, BorderLayout.SOUTH);
	}

	private void defineLowerHalf() {
		//lowerHalf = new JPanel();
		//lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

		//JButton backButton = new JButton("<= Back to Main");
		//addBackButtonListener(backButton);
		//lowerHalf.add(backButton);
	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		JPanel intPanel = new JPanel(new BorderLayout());
		intPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(intPanel);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		loginButton = new JButton("Login");
		addLoginButtonListener(loginButton);
		lowerPanel.add(loginButton);
	}

	private void defineLeftTextPanel() {
		leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BoxLayout(leftTextPanel, BoxLayout.Y_AXIS));

		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel("Username");
		label.setFont(Util.makeSmallFont(label.getFont()));
		username = new JTextField(15);
		usernamePanel.add(label);
		usernamePanel.add(username);

		leftTextPanel.add(usernamePanel);
	}

	private void defineRightTextPanel() {
		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BoxLayout(rightTextPanel, BoxLayout.Y_AXIS));

		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel("Password");
		label.setFont(Util.makeSmallFont(label.getFont()));
		password = new JPasswordField(15);
		passwordPanel.add(label);
		passwordPanel.add(password);

		rightTextPanel.add(passwordPanel);
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

    private void addLoginButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            try {
                User user = ci.login(username.getText(), password.getText());
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setTitle("Library Application");
                adminDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adminDashboard.setUser(user);
                adminDashboard.init();
                centerFrameOnDesktop(adminDashboard);
                adminDashboard.setVisible(true);

            } catch (LoginException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}

    public void closeWindow() {
        INSTANCE.dispose();
    }
}