
package panels;
import classes.DatabaseMainDriver;

import javax.swing.*;
import java.io.File;

public class MyPanels extends JPanel {

     public MainFrame PF;

     public JPanel mainPanel;

     public void setParent(MainFrame f) {
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


     public MyPanels(MainFrame PF){
          super();
          setParent(PF);
//          setMainPanel(mainPanel);

     }



}
