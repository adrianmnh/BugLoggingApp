package panels;

import classes.DatabaseUserDriver;
import classes.User;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginPanel extends MyPanel {

//    public MainFrame PF;
    private JPanel mainPanel;
    private JPasswordField user_password;
    private JFormattedTextField user_login;
    private JButton pressEnter_Button;
    private JPanel left_panel;
    private JLabel left_panel_label;
    public JButton moveTo_MainApplicationPortal_Button;
    private JLabel ulabel;
    private JLabel plabel;
    private JLabel welcomeToAppLabel;
    private JTextField newEmail;
    private JPanel createNewAccount_panel;
    private JPanel login_panel;
    private JButton moveto_CreateNewAccount_Button;
    private JTextField newName;
    private JPasswordField newPassword;
    private JButton resetButton;
    private JButton cancelButton;
    private JButton submitButton;
    private JButton continueAsGuest_Button;
    private JPanel connectionStringPanel;
    private JButton searchConnectionString_button;
    private JPanel right_panel;
    private ImageIcon iconYes = new ImageIcon(getClass().getClassLoader().getResource("assets/ok.png"));
    private ImageIcon iconNo = new ImageIcon(getClass().getClassLoader().getResource("assets/no2.png"));
    private ImageIcon load = new ImageIcon(getClass().getClassLoader().getResource("assets/load.png"));

    private String login;
    private char[] password;
//    private User user;
    public boolean tf = false;

    public int counter = 0;

//    public void setParent(MainFrame p){
//        this.PF = p;
//    }

//    public JPanel getMain(){
//        return mainPanel;
//    }
    public JButton getRuneButton(){
        return moveTo_MainApplicationPortal_Button;
    }

    public ActionListener[] getButtonListener(){
        return moveTo_MainApplicationPortal_Button.getActionListeners();
    }

    private void pressEscapeToExit(){
        if(counter > 1) {
            System.out.println("exiting application");
            System.exit(1);
        }
        else if(counter == 0){
            counter++;
            System.out.println("EscapePressed x1");
        }
        else {
            JOptionPane.showMessageDialog(mainPanel, "Press ESC exit", "Exit", JOptionPane.WARNING_MESSAGE);
            counter++; System.out.println("EscapePressed x2");
        }

    }
    private void setLoginPanelLabelsVisibleOff(){
        moveTo_MainApplicationPortal_Button.setVisible(true);
        user_password.setVisible(false);
        user_login.setVisible(false);
        ulabel.setVisible(false);
        plabel.setVisible(false);
        pressEnter_Button.setVisible(false);
        continueAsGuest_Button.setVisible(false);
        moveto_CreateNewAccount_Button.setVisible(false);

        connectionStringPanel.setVisible(false);
        searchConnectionString_button.setVisible(false);

//        mainPanel.setVisible(false);
        welcomeToAppLabel.setText("Welcome " + user_login.getText() + "!");
        mainPanel.setVisible(true);
        PF.repackAfterLogin();
    }

    public void initialSetup(){
//        PF.pack();

        System.out.println("Entering logging panel...");
        left_panel_label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("assets/yes2.png")));
        this.moveTo_MainApplicationPortal_Button.setVisible(false);
        createNewAccount_panel.setVisible(false);
        testScript();

    }

    private void testScript(){
        user_login.setText("adrian");
        user_password.setText("password");
    }

    public LoginPanel(MainFrame f){
        super(f);
        setMainPanel(mainPanel);
//        setParent(frame);
        initialSetup();

        //this is added to the username and password fields to execute "enterButton" by pressing Enter
        //1KeyAdapter

        KeyAdapter loginPanelPressEnter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("EnterPressed");
                    pressEnter_Button.doClick();
                    counter = 0;
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    pressEscapeToExit();
                }
            }
        };

        KeyAdapter newUserPanelPressEnter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submitButton.doClick();
                }
            }
        };

        user_password.addKeyListener(loginPanelPressEnter);
        user_login.addKeyListener(loginPanelPressEnter);


        pressEnter_Button.addActionListener(e -> {

            DatabaseUserDriver connct = new DatabaseUserDriver(PF.connectionFile);


            if (connct.isConnected()) {
                connct.printAllTableData();

                PF.setCurrentUserID(1); // Unused
                login = user_login.getText();
                password = user_password.getPassword();
                System.out.println("\n -- Credentials --\n");
                System.out.println(login);
                login = login.replaceAll("[`~!@#$%^&*()-=\'\"]", "");

                User user = new User(login, password, "");
                System.out.println(login);
                System.out.println(password);
                System.out.println("\n -- END --\n");
                ArrayList<Integer> response;

                    response = connct.checkLoginDetails(user);

                PF.setCurrentUserID(response.get(1));
                System.out.println("\n -- Response Codes: --\n");
                System.out.println(response);

                boolean match1 = (response.get(0) == 200 ) ? true : false;
                if(match1) System.out.println("id exists");
                boolean match2 = (response.get(2) == 200 ) ? true : false;
                if(match2) System.out.println("correct pass");
                if (match2 && match1) {
                    JOptionPane.showMessageDialog(PF, "Correct!", "login", JOptionPane.ERROR_MESSAGE, iconYes);
                    System.out.println("Login successful...");
                    setLoginPanelLabelsVisibleOff();
                } else {
                    JOptionPane.showMessageDialog(PF, "Incorrect username or password.   ", "login", JOptionPane.ERROR_MESSAGE, iconNo);
                }

            } else {
                JOptionPane.showMessageDialog(PF, "Connection could not be established.    ", "Could not connect", JOptionPane.ERROR_MESSAGE, iconNo);

            }

            connct.closeConnection();


            //System.out.print("username: " + Arrays.toString(login.toCharArray()).replaceAll(",", ""));
            //System.out.println("\tpassword: " + Arrays.toString(password).replaceAll(",",""));
            ////UNSECURE,  THIS requires variables above to print to console and process following statement
            //boolean match = (usertemp.compareTo(login) == 0 && Arrays.equals(passtemp, password)) ? true : false;
            //String usertemp = "tabia";
            //char[] passtemp = "pas5word".toCharArray();
            ////SECURE, no print statements
            //boolean match = (usertemp.compareTo(user_login.getText()) == 0 && Arrays.equals(passtemp, user_password.getPassword())) ? true : false;

        });






        moveTo_MainApplicationPortal_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("moving to 'Create New Rune Panel'...");
                System.out.println("********************");
//                System.out.println("currnt userid: " + PF.getCurrentUserID());
                System.out.println("********************");
//                parentFrame.setPanelTo(parentFrame.mainApp_panel.getMain());

//                PF.changePanel_MainApp();
                PF.changePanel_SummonersWarApp();
            }
        });


        moveTo_MainApplicationPortal_Button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    moveTo_MainApplicationPortal_Button.doClick();
                }
            }
        });

        moveto_CreateNewAccount_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Object o = JOptionPane.showInputDialog(PF, "Please enter invitation code", "New Account",1, load, null, "");
//                if(o!=null){
//                    if(o.toString().equals("CS370") || o.toString().equals("")){
                        login_panel.setVisible(false);
                        searchConnectionString_button.setVisible(false);

                        createNewAccount_panel.setVisible(true);
                        PF.pack();




                        newName.setText("tabia");
                        newPassword.setText("password");
                        newEmail.setText("tabia@email");

//                    }
//                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newName.setText("");
                newPassword.setText("");
                newEmail.setText("");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_panel.setVisible(true);
                createNewAccount_panel.setVisible(false);
                PF.pack();

            }
        });

        ActionListener createNewUser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Box box = Box.createHorizontalBox();
                JPasswordField reenterPass = new JPasswordField(16);
                box.add(new JLabel("password:  "));
                box.add(reenterPass);

                String newUsername = newName.getText();
                newUsername = newUsername.replace("\'", "");
                char[] newPass = newPassword.getPassword();
                String newEmail = LoginPanel.this.newEmail.getText();
                DatabaseUserDriver newacc = new DatabaseUserDriver(PF.getConnectionFile());

                if(!newacc.isConnected()){
                    JOptionPane.showMessageDialog(PF, "Connection could not be established.    ", "Could not connect", JOptionPane.ERROR_MESSAGE, iconNo);

                } else {
                    JLabel jl = new JLabel();
                    box.add(jl);

                    // option 2 : ok cancel
                    // option 0 : yes no

                    User newUser = new User(newUsername, newPass, newEmail);

//                    if (newUsername.length() >= 5 && newPass.length >= 5 && newEmail.length() > 0) {
                    if (newUser.testUserInput()) {
                        int reEnterPassword = JOptionPane.showOptionDialog(PF, box, "The title", 2, JOptionPane.PLAIN_MESSAGE, load, null, "");

//                int reEnterPassword = JOptionPane.showOptionDialog(parentFrame, box, "The title", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, load, new String[]{"Ok", "Cancel"}, "");
//                int reEnterPassword = JOptionPane.showConfirmDialog(parentFrame, box, "Please re-enter password", 2, 2, load);

                        System.out.println("pass      - " + newPass);
                        System.out.println("reentered - " + reenterPass.getPassword());
                        ArrayList response = null;
                        if (reEnterPassword == 0) {
                            char[] input = reenterPass.getPassword();
                            if (Arrays.equals(input, newPass)) {
                                System.out.println("Passwords match");
//                        if( !userName.equals("") && userName.length()>5 && pass.length()>5 && !pass.equals("") && email.length()>0 && !email.equals("7")){
//                                User newUser = new User(newUsername, newPass, newEmail);
                                try {
                                    String query = newacc.addNewUserQuery(newUser);
                                    response = newacc.templateExecuteUpdate_ReturnRowsUpdated(newUser, query);
                                    System.out.println(response);
                                    int resCode = (int)response.get(0);
                                    if(resCode==200){
                                        JOptionPane.showMessageDialog(PF, "Account created.      ", "Success", JOptionPane.INFORMATION_MESSAGE, iconYes);
                                        createNewAccount_panel.setVisible(false);
                                        login_panel.setVisible(true);
                                        PF.pack();
                                    } else if(resCode == 2627){
                                        JOptionPane.showMessageDialog(PF, "Username or Email cannot be used.      ", "Failed", JOptionPane.ERROR_MESSAGE, iconNo);
                                    } else if(resCode == 2628){
                                        JOptionPane.showMessageDialog(PF, "Input is too long", "Failed", JOptionPane.ERROR_MESSAGE, iconNo);
                                    }else {
                                        JOptionPane.showMessageDialog(PF, "Unknown error. Something went wrong!      ", "Failed", JOptionPane.ERROR_MESSAGE, iconNo);
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Some Error thrown");
                                    System.out.println(response);
                                }
                            }
                            else {
                                System.out.println("Passwords DO NOT match");
                                JOptionPane.showMessageDialog(PF, "Passwords don't match", "Please re-enter password", JOptionPane.ERROR_MESSAGE, iconNo);
                            }
                        }


                    } else {
                        System.out.println("gETS HERE");
                        if (newUsername.length() < 5 || newUsername.length() > 16)
                            JOptionPane.showMessageDialog(PF, "Username must be between 5 and 16 characters      ", "Error", 2, iconNo);
                        else if (newPass.length < 5 || newPass.length > 16)
                            JOptionPane.showMessageDialog(PF, "Password is too short or long      ", "Error", 2, iconNo);
//                        else if (newEmail.length() < 12)
//                            JOptionPane.showMessageDialog(parentFrame, "Email must be 12 characters or longer", "Error", 2, iconNo);
//
//                }


                    }
                    System.out.println("Closing Connection");
                    newacc.closeConnection();
                }

            }
        };

        submitButton.addActionListener(createNewUser);



        newEmail.addKeyListener(newUserPanelPressEnter);

        newPassword.addKeyListener(newUserPanelPressEnter);

        newName.addKeyListener(newUserPanelPressEnter);

        submitButton.addKeyListener(newUserPanelPressEnter);

        continueAsGuest_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setLoginPanelLabelsVisibleOff();
            }
        });
        searchConnectionString_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println( System.getProperty("user.dir"));
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
//                fileChooser.setFileFilter(new FileNameExtensionFilter(null ));
                int result = fileChooser.showOpenDialog(PF);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Do something with the selected file
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    PF.setConnectionFile(selectedFile);
                }

            }
        });
    }


}
