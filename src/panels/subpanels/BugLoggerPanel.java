package panels.subpanels;

import panels.MyPanel;

import javax.swing.*;
import java.io.IOException;

public class BugLoggerPanel extends JPanel {
//    private MainFrame PF;
    public JPanel mainPanel;

    private MyPanel parentPanel;
    private JPanel left_panel;
    private JPanel right_panel;
    private JCheckBox checkBox1;
    private JComboBox projectSelector_comboBox;
    private JPanel top_panel;
    private JScrollPane leftScrollPanel;
    private JPanel leftPanelInner1;
    private JPanel left1;
    private JPanel left2;

    private void $$$setupUI$$$() throws IOException {

        createUIComponents();

//        radioButton3 = new JRadioButton();
//        radioButton3.setText("RadioButton");
    }

    public void initialSetup(){

//        PF.setContentPane(right_panel);

    }


    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        left1 = new JPanel();
        left2 = new JPanel();
//        leftScrollPanel = new JScrollPane();
        leftScrollPanel = new ScrollPanel(parentPanel, left1, left2);
//        right_panel = new LoginPanel(PF);
    }

    public BugLoggerPanel(MyPanel parentPanel){

        this.parentPanel = parentPanel;

//        super();
//        super(f);
//        setMainPanel(mainPanel);
//        super();
//        setParent(f);
//        initialSetup();
    }
}
