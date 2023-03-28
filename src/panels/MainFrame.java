package panels;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class MainFrame extends JFrame {

    private int currentUserID;

    public File connectionFile;
    private JPanel CURRENT_panel;

    private ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/rune.png")));

//    public LoginPanel login_panel = new LoginPanel(this);
//    public MainAppPanel mainApp_panel = new MainAppPanel(this);
//    public BugLoggerPanel bg = new BugLoggerPanel();
    public LoginPanel login_panel;
    public MainAppPanel mainApp_panel;
//    public BugLoggerPanel bg;

    public ImageIcon loadImageIcon(String name){
        return new ImageIcon(getClass().getClassLoader().getResource(name));
    }



    public int getCurrentUserID(){
        return this.currentUserID;
    }
    public void setCurrentUserID(int id){
        this.currentUserID = id;
    }
    public boolean parentBool = false;

//    public void setBut(){
//        parentBool = parentBool ? true : false;
//    }

    public JPanel getMainAppPanel(){
        return this.mainApp_panel;
    }

    public void setSizeTo(int a, int b){
        this.setSize(1265-a, 740-b);
    }

    public File getConnectionFile(){
        return this.connectionFile;
    }

    public void setConnectionFile(File connFile){
        this.connectionFile = connFile;
    }

//    public Map localAssetMap;
    public ArrayList<String> localAssetList;

    private String OS;

    private String EXEType;

    private String rootDir;


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

    private void initializePanels(){
        login_panel = new LoginPanel(this);
        mainApp_panel = new MainAppPanel(this);

//        mainApp_panel.loadAssetsIntoPanels();

//        bg = new BugLoggerPanel();
    }

//    private void $$$setupUI$$$() {
//
//        createUIComponents();
//
//        System.out.println("Executes first ");
//
//
//
//
////        radioButton3 = new JRadioButton();
////        radioButton3.setText("RadioButton");
//    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//
//        System.out.println("Executes first or NOT");
//
//
//
//
//    }

    public ImageIcon getImg(String s){
        return this.loadImageIcon(s);
    }
    public ImageIcon getImg(int i){
        return this.loadImageIcon(this.localAssetList.get(i));
    }


    public MainFrame() throws IOException, InterruptedException {
        super("Main Application");
        setEXEType();
        setOS();
//        localAssetMap = loadLocalAssetsInJAR("assets/monsters");
        localAssetList = loadLocalAssetsInJAR("assets/monsters");

        this.setAlwaysOnTop(true);
//        this.setSize(100, 100);
        this.setIconImage(logo.getImage());
//        Image img = new ImageIcon(getClass().getClassLoader().getResource(localAssetList.get(3))).getImage();
//        this.setIconImage(img);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);



        initializePanels();

//        setPanelTo(mainApp_panel.getMain());
        setPanelTo(login_panel.getMain());
//        mainApp_panel = new MainAppPanel(this);
//        setPanelTo(bg.getMain());

    }

    private void setOS(){
        System.out.print("Setting OS to: ");
        String os = System.getProperty("os.name").toUpperCase();
        if(os.contains("LINUX")){
            this.OS = "linux";
        } else if(os.contains("WINDOWS")){
            this.OS = "windows";
        } else this.OS = "mac";
        System.out.println(os);
    }

    public String getEXEType(){
        return this.EXEType;
    }

    private void setEXEType(){
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (jarPath.endsWith(".jar")) {
            System.out.println("RUNNING FROM JAR");
            this.EXEType = "jar";
        } else {
            System.out.println("RUNNING FORM EXEcute class");
            this.EXEType = "class";
        }
    }

//    public String getRootDir(){
    public Path getJarLocation(){
        Path root = null;
        String jarPath = null;


        try {
            jarPath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(this.OS == "windows"){
                jarPath = jarPath.replace("/", "\\");
                jarPath = jarPath.substring(1, jarPath.length());
            }
            root = Paths.get(jarPath);


        } catch (URISyntaxException ex) {
            System.out.println("CLASS PATH exception");
        }
//        return jarPath;
        return root;
    }

    public Path getRootDir(){
        return getJarLocation().getParent();
    }

    public ArrayList<String> loadLocalAssetsInJAR(String s){

        ArrayList<String> assetList= new ArrayList<>();
        String message = null;



        if(this.EXEType == "jar"){
            try {
    //            System.out.println("*****************1\n  -- " + jarPath + " -- \n*****************\n");
    //            String jarPath = getRootDir();
    //            Path root = Paths.get(jarPath);
                Path root = getJarLocation();
                System.out.println("Reading From :: "+ root);
                var fileIn = Files.newInputStream(root);
                ZipInputStream zip = new ZipInputStream(fileIn);
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                    String content = new String(zip.readAllBytes(), StandardCharsets.UTF_8);
//                    byte[] data = content.getBytes();
                    if(entry.getName().startsWith("assets/monsters/") && entry.getName().endsWith(".jpg")){
//                        map.put(entry.getName(), content.getBytes());
//                        map.put(entry.getName(), data);
                        assetList.add(entry.getName());
//                    System.out.println(entry.getName() + ": " + data.length + " bytes");

                    }
                }
            message = "Assets read from "+root;
//            JOptionPane.showMessageDialog(this, "Correct!", "login", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(assetList.get(5)));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {

            File folder = new File(getClass().getClassLoader().getResource("assets/monsters/").getFile());
//                File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg"));
            System.out.println("Reading From :: "+ folder.getPath());
            File[] files = folder.listFiles();
            for (File file : files) {
                try (InputStream is = new FileInputStream(file)) {
//                    byte[] data = is.readAllBytes();
//                    String a = data.toString();
//                    System.out.println(file.getName() + ": " + data.length + " bytes");
//                    map.put("assets/monsters/"+file.getName(), data);
                    assetList.add("assets/monsters/"+file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File[] imageFiles = new File("src/assets/monsters").listFiles();
            message = "Assets read from "+folder.getPath();
        }
        JOptionPane.showMessageDialog(this, message, this.EXEType, JOptionPane.INFORMATION_MESSAGE, loadImageIcon(assetList.get(0)));

        return assetList;
    }


}
