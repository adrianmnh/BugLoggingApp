package panels.subpanels;

import panels.MyPanel;

import javax.swing.*;

public class LongestSubstringPanel extends MySubPanel {
    public JPanel mainPanel;
    private JTextArea string1;
    private JTextArea string2;
    private JLabel lcsLabel;
    private JLabel lengthLabel;
    private JLabel startLabel;
    private JLabel endLabel;


    public LongestSubstringPanel(MyPanel p) {
        super(p);

        string1.setText("subexamexampleexamexamle");
//        string1.setText("");
        string2.setText("example");

//        System.out.println(lcs(string1.getText(), string2.getText()));


    }

    public String lcs(String a, String b){
        int[][] dp = new int[a.length()+1][b.length()+1];
        int maxLength = 0;
        int idx=0;
        for (int i = 0; i < a.length()+1; i++) {
            for (int j = 0; j < b.length()+1; j++) {
                if(i == 0 || 0 == j) dp[i][j] = 0;
                else {
                    System.out.println(a.substring(i-1, i));
                    System.out.println(b.substring(j-1, j));

                    if(a.substring(i-1, i).equals(b.substring(j-1, j))){
                        dp[i][j] = dp[i-1][j-1]+1;
                        if(dp[i][j]>maxLength) {
                            maxLength = dp[i][j];
                            idx= i;
                        }
                    }
                }
            }
        }


        for (int i = 0; i < a.length()+1; i++) {
            for (int j = 0; j < b.length()+1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("length: " + maxLength);
        System.out.println(idx);

        return a.substring(idx-maxLength, idx);
    }










    @Override
    public JPanel getMain() {
        return mainPanel;
    }
}
