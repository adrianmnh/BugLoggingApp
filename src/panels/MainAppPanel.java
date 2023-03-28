package panels;



import classes.DatabaseUserDriver;
import classes.RSTable;
import panels.subpanels.BugLoggerPanel;
import panels.subpanels.ScrollPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static classes.Helper.*;
import static classes.Helper.processResultSet;

public class MainAppPanel extends MyPanel {

//    public MainFrame PF;

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
    private JPanel testPanel;
    private JPanel mainRightPanel;


    private int currentRune = 0;



//    public JPanel getMain() {
//        return mainPanel;
//    }



//    public void setParent(MainFrame f) {
//        PF = f;
//    }

//    public MainFrame getParentFrame(){
//        return this.PF;
//    }

    private void initialSetup(){


        JPanel imagePanel = new JPanel();

//        loadAssetsIntoPanels();

        getParentFrame().pack();




    }

    public static final int ICON_HEIGHT = 85;

//    private void $$$setupUI$$$() {
//
//        createUIComponents();
//
//        System.out.println("Executes first ");
//
//
////        radioButton3 = new JRadioButton();
////        radioButton3.setText("RadioButton");
//    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        System.out.println("Creating Main App Panel Components &&&&&&&&&&&&&&&");
        left1 = new JPanel();
        left2 = new JPanel();

        try{

//            leftScrollPanel = new leftScrollPanel(PF,  left1, left2);
            leftScrollPanel = new ScrollPanel(this,  left1, left2);
        } catch (Exception e){
            System.out.println("SOMETHING WENT WRONG");
        }


//        testPanel = new LoginPanel(this.PF);
        BugLoggerPanel bg = new BugLoggerPanel(this);

        testPanel = bg.mainPanel;

    }

//    public void loadAssetsIntoPanels() {
////        System.out.println(this.getParentFrame().localAssetMap);
//        int counter = 0;
//        for (int i = 0; i < 5; i++) {
////            for (Object f : this.getParentFrame().localAssetMap.values()) {
////            for (Object f : this.getParentFrame().localAssetMap.keySet()) {
//            for (String name : this.getParentFrame().localAssetList) {
//                System.out.println("\n" + name);
////                for (File f: imageFiles){
//
////                    Image img = ImageIO.read(f);
////                    JLabel l = new JLabel(new ImageIcon(img));
//
//
////                    ByteArrayInputStream bis = new ByteArrayInputStream((byte[])f);
//
////                    ImageIcon img = ImageIO.read(bis);
//                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(name));
//                JLabel l = new JLabel(img);
//
//
////                    ImageIcon ii = new ImageIcon(img);
////                    l.setIcon(ii);
//
//
////                l.addMouseListener(new MouseAdapter() {
////                    @Override
////                    public void mouseClicked(MouseEvent e) {
////                        // Handle mouse click
////                        System.out.println(e.getX() + " " + e.getY());
//////                            System.out.println(e.getPoint());
////
////                    }
////                });
//                left1.add(l);
////                        left1.add(new JLabel(new ImageIcon(img)));
//                JTextArea j = new JTextArea("hellohellohellohellohello\nhellohellohellohellohello\nhellohellohellohellohello\n");
//                j.setFont(new Font(null, Font.PLAIN, 10));
//                j.setPreferredSize(new Dimension(180, ICON_HEIGHT - 5));
//                j.setMinimumSize(new Dimension(180, ICON_HEIGHT - 5));
//                j.setMaximumSize(new Dimension(180, ICON_HEIGHT - 5));
//                left2.add(j);
//                counter++;
//
//            }
//            left1.setPreferredSize(new Dimension(-1, counter * ICON_HEIGHT));
////            left1.setPreferredSize(new Dimension(-1, -1));
//
//            this.getParentFrame().pack();
//        }
//
//        System.out.println(counter * ICON_HEIGHT);
//        left1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e2) {
//                // Handle mouse click
//                System.out.println(e2.getX() + " " + e2.getY() + "\n");
////                            System.out.println(e.getLocationOnScreen());
////                            System.out.println(e.getPoint());
//                String imgClicked = "";
//                int pos = e2.getY() / ICON_HEIGHT ;
//                int assetPosInList = pos % getParentFrame().localAssetList.size();
//                System.out.println(assetPosInList);
//                System.out.println("Clicked on -- " + getParentFrame().localAssetList.get(assetPosInList));
//                JOptionPane.showMessageDialog(getParentFrame(),"Clicked on -- " + getParentFrame().localAssetList.get(assetPosInList) , "Watcher", JOptionPane.INFORMATION_MESSAGE, getParentFrame().getImg(assetPosInList));
//
//
//            }
//        });
//    }


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
    public void readImage(File stream){
        try {
            BufferedImage image = ImageIO.read(stream);
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

        } catch (IOException ex) {
            System.out.println("WRONG IMAGE REAED 1");
//            throw new RuntimeException(ex);
        }

    }
    public void readImage(InputStream stream){
        try {
            BufferedImage image = ImageIO.read(stream);
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

        } catch (IOException ex) {
            System.out.println("WRONG IMGE READ2");
//            throw new RuntimeException(ex);
        }
    }
    public MainAppPanel(MainFrame f){
        super(f);
        setMainPanel(mainPanel);

        System.out.println("Starting Main App panel...");
//        setParent(f);

        initialSetup();

//        this.parentFrame.setVisible(true);



        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                Image image = Toolkit.getDefaultToolkit().getImage("src/assets/bnw.jpg");
//                try {

                    System.out.println("Opening Image");
//                InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/monsters/Theomars.jpg");
//                readImage(stream);


                    DatabaseUserDriver db = new DatabaseUserDriver(f.connectionFile);

                    db.setDbSchema("Users");
                    db.setDbTable("Account");

                    ArrayList<Object> ar = db.execQuery("SELECT * FROM BugLogger.Project");
    //                db.printAllTableData();
    //

                    ResultSet rs = (ResultSet)(ar.get(1));


                RSTable tbl = new RSTable(rs);

                System.out.println(tbl.header);

                    System.out.println(tbl);


//                    byte[] imageData = rs.getBytes("img1");
//                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
//
//                    ImageIO.write(img, "jpg", new File("TESTTESTTEST.jpg"));
//
//                    ImageIcon icon = new ImageIcon(img);
//
//                JOptionPane.showMessageDialog(f, "test", "message", JOptionPane.INFORMATION_MESSAGE, icon);
//                    while(rs.next()) {
//                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                            if (rs.getObject(i) != null)
//                                System.out.println(rs.getObject(i) + " ");
//                        }
//                    }




//                    System.out.println(rs.next());
//                    rs.next();
//                    rs.next();
//
//
//


                db.closeConnection();








            }
        });



        openFile_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] imageExtensions = {"jpg", "png"};


                System.out.println("Tries to open file");

//                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                String hardcodedDir = "F:\\JavaApp\\distro";
                String projectDir = System.getProperty("user.dir");
//                System.out.println(getParentFrame().getJarLocation());
                System.out.println(getParentFrame().getJarLocation());
                if(getParentFrame().getEXEType() == "jar"){
                    projectDir = getParentFrame().getJarLocation().toString();
                }
                else {
                    projectDir = hardcodedDir;
                }


                JFileChooser fileChooser = new JFileChooser(projectDir);


                fileChooser.setFileFilter(new FileNameExtensionFilter("Images Only", imageExtensions));
                int result = fileChooser.showOpenDialog(getParentFrame());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Do something with the selected file
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    printImageData(selectedFile);
                }

                JOptionPane.showMessageDialog(f, projectDir, f.getEXEType(), JOptionPane.INFORMATION_MESSAGE, f.getImg(1));


                for (String s : f.localAssetList)
                    System.out.println(s);


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

//    public static class leftScrollPanel extends JScrollPane{
////        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
//        public leftScrollPanel(MyPanels PF, JPanel left1, JPanel left2)  {
////            leftScrollPanel = new JScrollPane();
////        leftScrollPanel.setMaximumSize(new Dimension(50,50));
//
//            String projectDir = System.getProperty("user.dir");
//            String assetsDir = "/src/assets/monsters/";
//            System.out.println("\n\n\n\n\nADDING CONTENT");
//
////            System.out.println(PF.getParentFrame().localAssetList);
//
//
//
//// START HERE -----------------------------------------------------------------
//
//
//
//
////            InputStream is = getClass().getClassLoader().getResourceAsStream(projectDir + assetsDir);
////            System.out.println(is);
////            try (JarFile jarFile = new JarFile(assetsDir)) {
////                Enumeration<JarEntry> entries = jarFile.entries();
////                while (entries.hasMoreElements()) {
////                    JarEntry entry = entries.nextElement();
////                    System.out.println(entry.getName());
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//
//
//            ///////////THIS WORKS ON INTELLIJ ONLY NOT ON JAR----------------------------------------------------------
////            int counter = 0;
////            for (int i = 0; i < 5; i++) {
////                for(Object f : PF.getParentFrame().localAssetMap.values()){
//////                for (File f: imageFiles){
////                    try {
//////                        System.out.println(f);
////                        ByteArrayInputStream bis = new ByteArrayInputStream((byte[])f);
////                        BufferedImage img = ImageIO.read(bis);
////                        JLabel l = new JLabel(new ImageIcon(img));
////                        l.addMouseListener(new MouseAdapter() {
////                            @Override
////                            public void mouseClicked(MouseEvent e) {
////                                // Handle mouse click
////                                System.out.println(e.getX() + " " + e.getY());
////
////                            }
////                        });
////                        left1.add(l);
//////                        left1.add(new JLabel(new ImageIcon(img)));
////                        JTextArea j = new JTextArea("hellohellohellohellohello\nhellohellohellohellohello\nhellohellohellohellohello\n");
////                        j.setFont(new Font(null, Font.PLAIN, 10));
////                        j.setPreferredSize(new Dimension(180, ICON_HEIGHT-5));
////                        left2.add(j);
////                        counter++;
////                    } catch (IOException e) {
////                        throw new RuntimeException(e);
////
////                    }
////
////                }
////
////            }
////            left1.setPreferredSize(new Dimension(-1, counter*ICON_HEIGHT));
////            PF.getParentFrame().pack();
//////////////////--------------------------------------------------------------------------------------------------------
//
//
//            ///////////// IN CASE IT BREKAS
////            File[] imageFiles = new File("src/assets/monsters").listFiles();
////            int counter = 0;
////            for (int i = 0; i < 5; i++) {
////                for (File f: imageFiles){
////                    try {
////                        System.out.println(f);
////                        Image img = ImageIO.read(f);
////                        JLabel l = new JLabel(new ImageIcon(img));
////                        l.addMouseListener(new MouseAdapter() {
////                            @Override
////                            public void mouseClicked(MouseEvent e) {
////                                // Handle mouse click
////                                System.out.println(e.getX() + " " + e.getY());
////
////                            }
////                        });
////                        left1.add(l);
//////                        left1.add(new JLabel(new ImageIcon(img)));
////                        JTextArea j = new JTextArea("hellohellohellohellohello\nhellohellohellohellohello\nhellohellohellohellohello\n");
////                        j.setFont(new Font(null, Font.PLAIN, 10));
////                        j.setPreferredSize(new Dimension(180, ICON_HEIGHT-5));
////                        left2.add(j);
////                        counter++;
////                    } catch (IOException e) {
////                        throw new RuntimeException(e);
////
////                    }
////
////                }
////
////            }
////            left1.setPreferredSize(new Dimension(-1, counter*ICON_HEIGHT));
//
//
//        }
//
//    }




}














