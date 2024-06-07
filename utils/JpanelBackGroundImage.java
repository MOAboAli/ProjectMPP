package utils;
import javax.swing.*;
import java.awt.*;

import static utils.Utils.assets_dir;

public class JpanelBackGroundImage extends JPanel {
    private Image backgroundImage;

    // Constructor to load the image
    public JpanelBackGroundImage() {
        // Load the background image
        backgroundImage = new ImageIcon(assets_dir+"/libray.jpg").getImage();

        setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.PAGE_START);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }


}