package panels.subpanels;

import panels.MyPanel;

import javax.swing.*;

public abstract class MySubPanel extends JPanel {
    public MyPanel parentPanel;
    public MySubPanel(MyPanel p){
        parentPanel = p;
    }

    public abstract JPanel getMain();

}
