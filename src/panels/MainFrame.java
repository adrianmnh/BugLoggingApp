package panels;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private int currentUserID;

    public File connectionFile;
    private JPanel CURRENT_panel;

    private ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("assets/rune.png"));

    public LoginPanel login_panel = new LoginPanel(this);
    public MainAppPanel mainApp_panel = new MainAppPanel(this);


    public int getCurrentUserID(){
        return this.currentUserID;
    }
    public void setCurrentUserID(int id){
        this.currentUserID = id;
    }
    public boolean parentBool = false;

    public void setBut(){
        parentBool = parentBool ? true : false;
    }

    public JPanel getMainAppPanel(){
        return this.mainApp_panel;
    }

    public void setSizeTo(int a, int b){
        this.setSize(1265-a, 740-b);
    }

    public File getConnectionFile(){
        return this.connectionFile;
    }

    public void setConnectionFile(File f){
        this.connectionFile = f;
    }


    public void setPanelTo(JPanel jp){
        this.CURRENT_panel = jp;
        this.setContentPane(CURRENT_panel);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void changePanel_MainApp() {
        System.out.println("Entered app UI...");
        this.setVisible(true);
        this.CURRENT_panel = mainApp_panel.getMain();
        this.setContentPane(CURRENT_panel);
        this.pack();
        this.setLocationRelativeTo(null);
//        this.setLocation(this.getX()-450, this.getY()-100);
    }
    public void changePanel_BackToMainApp() {
        System.out.println("BackT to main UI...");
        this.setVisible(true);
        this.CURRENT_panel = mainApp_panel.getMain();
        this.setContentPane(CURRENT_panel);

    }

    public void repackAfterLogin(){
        this.pack();
    }

    public void repack(){
        this.pack();
    }

    public MainFrame() throws IOException, InterruptedException {
        super("Main Application");
        this.setAlwaysOnTop(true);
//        this.setSize(100, 100);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.framepanel = engrave_panel.getMain();
        setPanelTo(login_panel.getMain());

    }


}
