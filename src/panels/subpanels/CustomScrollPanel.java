package panels.subpanels;

import classes.Helper;
import panels.MainFrame;
import panels.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomScrollPanel extends JScrollPane{

    private JPanel monsterPanel, left2;
    private MyPanel parentPanel;

    private Component outerComponent;
    private MainFrame f;

    final int REPEAT = 1;
    int ICON_DIMENSION = 73;
    final int HEIGHT = 2;

    int COLUMN_SIZE;
    int COLUMNS;

    //        public leftScrollPanel(MainFrame PF, JPanel left1, JPanel left2) throws IOException {
    public CustomScrollPanel(MyPanel parentPanel, JPanel l1, Component component)  {


        this.monsterPanel = l1;
//        this.left2 = l2;
        this.parentPanel = parentPanel;
        this.outerComponent = component;
        this.f = this.parentPanel.getParentFrame();

        this.getHorizontalScrollBar().setUnitIncrement(16);

        loadAssetsIntoPanels();


    }

    public void loadAssetsIntoPanels() {
        int size = f.localAssetList.size();
        for (int i = 0; i < REPEAT; i++) {

            for (String name : this.f.localAssetList) {

                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(name));
                img = new ImageIcon(img.getImage().getScaledInstance(ICON_DIMENSION,ICON_DIMENSION, Image.SCALE_SMOOTH));
                JLabel l = new JLabel(img);


                l.setVerticalAlignment(0);
                l.setHorizontalAlignment(0);
                monsterPanel.add(l);

            }
        }
        COLUMNS = size * REPEAT / 2;
        if(size%2==1)   this.COLUMNS += 1;

        COLUMN_SIZE = ICON_DIMENSION * COLUMNS;


        Helper.resize(monsterPanel, COLUMN_SIZE, ICON_DIMENSION * HEIGHT);

        System.out.println("Col size: " + COLUMN_SIZE + " " + ICON_DIMENSION * HEIGHT);
//        System.out.println(monsterPanel.());
        System.out.println(monsterPanel.getInsets());

        monsterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        this.f.pack();

//        System.out.println(counter * ICON_HEIGHT);
        System.out.println(  f.getSize()  );
        System.out.println(f.getInsets());

        try {
            Thread.sleep(100);
            Helper.resize(this, COLUMN_SIZE * ICON_DIMENSION + 10, ICON_DIMENSION * HEIGHT + 10);
//            Helper.resize(outerComponent, COLUMN_SIZE * ICON_DIMENSION + 10, ICON_DIMENSION * HEIGHT + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }




        monsterPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e2) {
                // Handle mouse click
                System.out.println(size);
                int size = f.localAssetList.size();
                int row = e2.getY()/ICON_DIMENSION;
                int col = e2.getX()/ICON_DIMENSION;
                int pos = row * COLUMNS + col ;
                int assetPosInList = pos % size;
                System.out.println(row + " " + col + "\n" + assetPosInList);
                String message = "Clicked on " + assetPosInList + "\n -- "
                        + f.localAssetList.get(assetPosInList) + "\n --"
                        + e2.getX() + " " + e2.getY()
                        + row + " " + col;
                System.out.println(row + " " + col + "\n");
                System.out.println(assetPosInList);
                System.out.println("Clicked on -- " + f.localAssetList.get(assetPosInList));
                JOptionPane.showMessageDialog(f,message, "Watcher", JOptionPane.INFORMATION_MESSAGE, f.getImg(assetPosInList));



            }
        });

    }


}
