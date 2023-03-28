package panels.subpanels;

import panels.MainFrame;
import panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScrollPanel extends JScrollPane {

    private JPanel left1, left2;
    private MyPanel parentPanel;
    private MainFrame f;

    final int ICON_HEIGHT = 85;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public ScrollPanel(MyPanel parentPanel, JPanel l1, JPanel l2)  {



        this.left1 = l1;
        this.left2 = l2;
        this.parentPanel = parentPanel;
        this.f = this.parentPanel.getParentFrame();
//            leftScrollPanel = new JScrollPane();

        String projectDir = System.getProperty("user.dir");
        String assetsDir = "/src/assets/monsters/";
        System.out.println("\n\n\n\n\nADDING CONTENT");



        loadAssetsIntoPanels();



// START HERE -----------------------------------------------------------------




//            InputStream is = getClass().getClassLoader().getResourceAsStream(projectDir + assetsDir);
//            System.out.println(is);
//            try (JarFile jarFile = new JarFile(assetsDir)) {
//                Enumeration<JarEntry> entries = jarFile.entries();
//                while (entries.hasMoreElements()) {
//                    JarEntry entry = entries.nextElement();
//                    System.out.println(entry.getName());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



        ///////////THIS WORKS ON INTELLIJ ONLY NOT ON JAR----------------------------------------------------------
//            int counter = 0;
//            for (int i = 0; i < 5; i++) {
//                for(Object f : PF.getParentFrame().localAssetMap.values()){
////                for (File f: imageFiles){
//                    try {
////                        System.out.println(f);
//                        ByteArrayInputStream bis = new ByteArrayInputStream((byte[])f);
//                        BufferedImage img = ImageIO.read(bis);
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
//            PF.getParentFrame().pack();
////////////////--------------------------------------------------------------------------------------------------------


        ///////////// IN CASE IT BREKAS
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

    public void loadAssetsIntoPanels() {
//        System.out.println(this.getParentFrame().localAssetMap);
        int counter = 0;
        for (int i = 0; i < 5; i++) {
//            for (Object f : this.getParentFrame().localAssetMap.values()) {
//            for (Object f : this.getParentFrame().localAssetMap.keySet()) {
            for (String name : this.f.localAssetList) {
                System.out.println("\n" + name);
//                for (File f: imageFiles){

//                    Image img = ImageIO.read(f);
//                    JLabel l = new JLabel(new ImageIcon(img));


//                    ByteArrayInputStream bis = new ByteArrayInputStream((byte[])f);

//                    ImageIcon img = ImageIO.read(bis);
                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(name));
                JLabel l = new JLabel(img);

//                l.setMaximumSize(new Dimension(ICON_HEIGHT, ICON_HEIGHT-10));
//                l.setPreferredSize(new Dimension(ICON_HEIGHT, ICON_HEIGHT-10));
                l.setVerticalAlignment(0);
                System.out.println(l.getVerticalAlignment());

//                    ImageIcon ii = new ImageIcon(img);
//                    l.setIcon(ii);


//                l.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        // Handle mouse click
//                        System.out.println(e.getX() + " " + e.getY());
////                            System.out.println(e.getPoint());
//
//                    }
//                });
                left1.add(l);
//                        left1.add(new JLabel(new ImageIcon(img)));
                JTextArea j = new JTextArea("hellohellohellohellohello\nhellohellohellohellohello\nhellohellohellohellohello\n");
                j.setFont(new Font(null, Font.PLAIN, 10));
                j.setPreferredSize(new Dimension(180, ICON_HEIGHT - 5));
                j.setMinimumSize(new Dimension(180, ICON_HEIGHT - 5));
                j.setMaximumSize(new Dimension(180, ICON_HEIGHT - 5));
                left2.add(j);
                counter++;

            }
            left1.setPreferredSize(new Dimension(-1, counter * ICON_HEIGHT));
//            left1.setPreferredSize(new Dimension(-1, -1));

            this.f.pack();
        }

        System.out.println(counter * ICON_HEIGHT);
        left1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {
                // Handle mouse click
                int pos = e2.getY() / ICON_HEIGHT ;
                int assetPosInList = pos % f.localAssetList.size();
                String message = "Clicked on " + assetPosInList + " -- " + f.localAssetList.get(assetPosInList) + "\n " + e2.getX() + " " + e2.getY();
//                System.out.println(e2.getX() + " " + e2.getY() + "\n");
                System.out.println(assetPosInList);
                System.out.println("Clicked on -- " + f.localAssetList.get(assetPosInList));
                JOptionPane.showMessageDialog(f,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, f.getImg(assetPosInList));
//                            System.out.println(e.getLocationOnScreen());
//                            System.out.println(e.getPoint());
                String imgClicked = "";


            }
        });
    }

}