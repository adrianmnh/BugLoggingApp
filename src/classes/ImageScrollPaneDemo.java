package classes;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageScrollPaneDemo extends JFrame {
    public ImageScrollPaneDemo() {
        setTitle("Image Scroll Pane Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(100, 500));

        // Create a panel to hold the images
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add images to the panel
        File[] imageFiles = new File("src/assets/monsters").listFiles(); // Replace "image_folder_path" with your folder path
        for (File file : imageFiles) {
            try {
                Image image = ImageIO.read(file);
                JLabel label = new JLabel(new ImageIcon(image));
                panel.add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Create a scroll pane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the frame
        getContentPane().add(scrollPane);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageScrollPaneDemo demo = new ImageScrollPaneDemo();
            demo.setVisible(true);
        });
    }
}
