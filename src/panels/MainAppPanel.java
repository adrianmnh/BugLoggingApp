package panels;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainAppPanel extends JPanel implements MyPanels {

    public MainFrame parentFrame;

    private JPanel mainPanel;
    private JPanel mainLeftPanel;
    private JScrollPane leftScrollPanel;
    private JPanel leftPanelInner1;
    private JPanel left1;
    private JPanel left2;
    private JPanel mainTopPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton openFile_button;


    private int currentRune = 0;



    public JPanel getMain() {
        return mainPanel;
    }



    private void setParent(MainFrame f) {
        parentFrame = f;
    }

    public MainFrame getParentFrame(){
        return this.parentFrame;
    }

    private void startSetup(){

        getParentFrame().pack();

        JPanel imagePanel = new JPanel();



    }

    public static final int ICON_HEIGHT = 85;

    private void createUIComponents() {
        // TODO: place custom component creation code here

        left1 = new JPanel();
        left2 = new JPanel();
        leftScrollPanel = new leftScrollPanel(left1, left2);



    }


    class ImageGridForm extends JPanel {

        public ImageGridForm() {
            setLayout(new GridLayout(3, 3)); // 3 rows, 3 columns

            for (int i = 1; i <= 9; i++) {
                JLabel label = new JLabel(new ImageIcon("src/assets/ok.png"));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Handle mouse click
                        System.out.println(getLocation());
                    }
                });
                add(label);
            }
        }
    }

    public void printImageData(File imageFile){
        try{
            BufferedImage image = ImageIO.read(imageFile);

            // Print the image header information
            int width = image.getWidth();
            int height = image.getHeight();
            int imageType = image.getType();
            System.out.println("Image width: " + width);
            System.out.println("Image height: " + height);
            System.out.println("Image type: " + imageType);

            // Convert the image to binary format
            StringBuilder binaryStringBuilder = new StringBuilder();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    if (gray > 127) {
                        binaryStringBuilder.append("1 ");
                    } else {
                        binaryStringBuilder.append("  ");
                    }
                }
                binaryStringBuilder.append("\n");
            }

            // Print the binary image data
            System.out.println(binaryStringBuilder.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public MainAppPanel(MainFrame f){
        super();
        System.out.println("Starting Main App panel...");
        setParent(f);

        startSetup();

//        this.parentFrame.setVisible(true);



        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                Image image = Toolkit.getDefaultToolkit().getImage("src/assets/bnw.jpg");
                try {

                    File imageFile = new File("src/assets/bnw.jpg");
                    BufferedImage image = ImageIO.read(imageFile);

                    // Print the image header information
                    int width = image.getWidth();
                    int height = image.getHeight();
                    int imageType = image.getType();
                    System.out.println("Image width: " + width);
                    System.out.println("Image height: " + height);
                    System.out.println("Image type: " + imageType);

                    // Convert the image to binary format
                    StringBuilder binaryStringBuilder = new StringBuilder();
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            int rgb = image.getRGB(x, y);
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;
                            int gray = (red + green + blue) / 3;
                            binaryStringBuilder.append(" "+ gray);

//                            if (gray > 127) {
//                                binaryStringBuilder.append("1 ");
//                            } else {
//                                binaryStringBuilder.append("  ");
//                            }
                        }
                        binaryStringBuilder.append("\n");
                    }

                    // Print the binary image data
                    System.out.println(binaryStringBuilder.toString());
                } catch (Exception ex) {
                }
            }
        });
        openFile_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] imageExtensions = {"jpg", "png"};


//                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                String projectDir = System.getProperty("user.dir");
                String assetsDir = "/src/assets/";
                JFileChooser fileChooser = new JFileChooser(projectDir + assetsDir);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Images Only", imageExtensions));
                int result = fileChooser.showOpenDialog(parentFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Do something with the selected file
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    printImageData(selectedFile);
                }
            }
        });
    }


    private class ImagePanel extends JPanel {
        private ImageIcon icon;

        public ImagePanel(ImageIcon icon) {
            this.icon = icon;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle mouse click
                }
            });
        }
    }

    private class leftScrollPanel extends JScrollPane{
        public leftScrollPanel(JPanel left1, JPanel left2){
//            leftScrollPanel = new JScrollPane();
////        leftScrollPanel.setMaximumSize(new Dimension(50,50));
//
//            File[] imageFiles = new File("src/assets/monsters").listFiles();
//            int counter = 0;
//            for (int i = 0; i < 5; i++) {
//                for (File f: imageFiles){
//                    try {
//                        System.out.println(f);
//                        Image img = ImageIO.read(f);
//                        JLabel l = new JLabel(new ImageIcon(img));
//                        l.addMouseListener(new MouseAdapter() {
//                            @Override
//                            public void mouseClicked(MouseEvent e) {
//                                // Handle mouse click
//                                System.out.println(e.getX() + " " + e.getY());
//
//                            }
//                        });
//                        left1.add(l);
////                        left1.add(new JLabel(new ImageIcon(img)));
//                        JTextArea j = new JTextArea("hellohellohellohellohello\nhellohellohellohellohello\nhellohellohellohellohello\n");
//                        j.setFont(new Font(null, Font.PLAIN, 10));
//                        j.setPreferredSize(new Dimension(180, ICON_HEIGHT-5));
//                        left2.add(j);
//                        counter++;
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//
//                    }
//
//                }
//
//            }
//            left1.setPreferredSize(new Dimension(-1, counter*ICON_HEIGHT));


        }
    }



}














