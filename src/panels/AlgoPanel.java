package panels;

import panels.subpanels.LongestSubstringPanel;
import panels.subpanels.MySubPanel;

import javax.swing.*;
import java.util.List;

public class AlgoPanel extends MyPanel{
    public JPanel mainPanel;
    private JComboBox algoComboBox;
    private JPanel mainTopPanel;
    private JPanel mainBottomPanel;

    public AlgoPanel(MainFrame PF) {
        super(PF);
        setMainPanel(mainPanel);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MySubPanel sp = new LongestSubstringPanel(this);
        algoComboBox = new MyComboBox(null);
        mainBottomPanel = sp.getMain();

    }



    class MyComboBox extends JComboBox{
        public MyComboBox(List<Object> l){
            super();

            this.addItem("Longest Substring");
            this.addItem("hello2");
            this.addItem("hello3");
        }




    }
}
