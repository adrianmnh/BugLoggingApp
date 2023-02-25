package runes;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        String inputFileName = "src/runes/r.txt";
        Scanner in = new Scanner(new File(inputFileName));

        //RuneCollection collection = new RuneCollection();

        System.out.println("Starting\n");
        ArrayList<Rune> mine = new ArrayList<Rune>();

        int counter = 0;
        while (in.hasNextLine()){
            String s = in.nextLine();
            Rune r = new Rune(s);
            mine.add(new Rune(s));
        }
//
//        for(Rune r : mine) {
//            System.out.println(r);
//            //System.out.println(r.toStringDB());
//        }
//        if(mine.get(1).runeExists(mine.get(3)))
//            System.out.println("Runes are the same");
//        else System.out.println("Runes are different");

//            connection.addRuneToTable("user_runes", 2, r);
//            connection.printAllTableData();
//            //connection.addRuneToUser(10,r);
////
////            connection.printAllTableData();
//            connection.closeConnection();

//        System.out.println("\n\nTesting on database..");
//
//        RuneDB connection = new RuneDB();
//        connection.execQuery("delete from user_runes where userid = 10");
//        connection.execQuery("ALTER TABLE user_runes AUTO_INCREMENT = 100;");
//        for(Rune r : mine)connection.addRuneToUser(10, r);
////        //        //System.out.println(connection.userHasRune(10, mine.get(1)));
////        connection.printAllTableData();
////        ArrayList<Rune> test = connection.getUserRunes(10);
//////
//        //connection.userHasRune(10, mine.get(1));
////        connection.addRuneToUser(10, mine.get(2));
//        connection.printAllTableData();
//        connection.closeConnection();





    }
}
