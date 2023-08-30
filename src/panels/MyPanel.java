
package panels;

import javax.swing.*;

public class MyPanel extends JPanel {

     public MainFrame PF;

     public JPanel mainPanel;

     public void setParent(MainFrame f) {
          System.out.println("gets used here");
          this.PF = f;
     }


     public JPanel getMain() {
          return mainPanel;
     }

     public void setMainPanel(JPanel m) {
          this.mainPanel = m;
     }

     public MainFrame getParentFrame(){
          return this.PF;
     }

     //     private DatabaseMainDriver initiateConnection(){
//          System.out.println("hello");
//          return null;
//     }
//     private DatabaseMainDriver initiateConnection(File f){
//
//     }


     public MyPanel(MainFrame PF){
          super();
          setParent(PF);
//          setMainPanel(mainPanel);

     }



}
