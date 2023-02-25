package classes;

import java.io.File;
import java.sql.*;

public class MonsterDB extends DatabaseMainDriver implements DatabaseCommands{
//    private Connection con;
//    private Statement query;
//    private ResultSet result;

    public MonsterDB(File f){
        super(f);
        setDbTable("user_monsters");
    }

    public void changeColumnName(String from, String to, int i){

        if( i != 0 ) {
            try {
                System.out.println("Changing Column data of \"" + getDbTable() + "\":");

                String sql = "ALTER TABLE `" + getDbTable() + "` CHANGE `" + from + "` `" + to + "` INT(" + i + ") NOT NULL;";

                getStatement().execute(sql);

                System.out.println(getStatement().getUpdateCount());


            } catch (Exception ex) {
                System.out.println("Error found :" + ex);
            }
        }
    }
    @Override
    public void changeColumnNames() {
//        changeColumnName("atk", "ATK", 4);
//        changeColumnName("def", "DEF", 4);
//        changeColumnName("spd", "SPD", 3);
//        changeColumnName("crate", "CRte", 3);
//        changeColumnName("cdmg", "CDmg", 3);
//        changeColumnName("res", "RES", 3);
//        changeColumnName("acc", "ACC", 3);
    }
    @Override
    public void addData() {
        addToMonsterDatabase();
    }

    public void removeMonsterFromDatabase(){

        try{
            getStatement().execute("DELETE FROM monsters WHERE Monster = 'NAME HERE' ");
            System.out.println("Monsters deleted: " + getStatement().getUpdateCount());
        } catch(SQLException e){
            System.out.println("Error executing query: " + e);
        }

    }
    private void addToMonsterDatabase(){
        /*
        Insert data to "monsters" table, no return value.
         */

        try{

            StringBuilder build = new StringBuilder();
            String mon = null;


//            getStatement().execute("INSERT INTO `user_monsters` VALUE (null, 'Monster', '0', '0', '0', '0', '0', '0', '0', '0')");

              //Artamiel
//              mon = "(null, null, 'Artamiel', '11535', '604', '769', '95', '15', '50', '40', '0', '0')";
            //Barbara
//              mon = "(null, null, 'Barbara', '9720', '845', '648', '108', '15', '50', '15', '25', '0')";
            //Bastet
//              mon = "(null, null, 'Bastet', '11850', '637', '714', '99', '15', '50', '40', '0', '0')";
            //Bellenus
//              mon = "(null, null, 'Bellenus', '10215', '703', '758', '99', '15', '50', '15', '0', '0')";
            //Chiwu
//              mon = "(null, null, 'Chiwu', '12180', '780', '549', '103', '15', '50', '15', '40', '0')";
            //Ethna
//                mon = "(null, null, 'Ethna', '10380', '845', '604', '119', '30', '50', '15', '0', '0' )," +
////            //Laika
//                    "(null, null, 'Laika', '11040', '834', '571', '100', '15', '50', '15', '0', '0')," +
////            //Monkey
//                    "(null, null, 'Monkey', '12180', '692', '637', '118', '15', '50', '15', '0', '0')," +
////            //Oberon
//                    "(null, null, 'Oberon', '10050', '790', '681', '92', '15', '50', '40', '0', '0')," +
////            //Perna
//                    "(null, null, 'Perna', '12345', '878', '439', '109', '15', '50', '15', '0', '0')," +
////            //DarkRyu
//                    "(null, null, 'Ryu', '10875', '823', '593', '103', '15', '50', '40', '15', '0')," +
////            //Seara
//                    "(null, null, 'Seara', '10875', '801', '615', '100', '15', '50', '15', '25', '0')," +
////            //Susano
//                    "(null, null, 'Susano', '8070', '911', '527', '107', '15', '50', '15', '25', '0')," +
////            //Sylvia
//                    "(null, null, 'Sylvia', '11040', '692', '714', '104', '15', '50', '15', '0', '0')";

//            //Theomars
//            mon = "(null, null, 'Theomars', '10875', '823', '593', '100', '30', '50', '15', '0', '0')";

////            //Tiana
//            mon = "(null,null, 'Tiana', '11850', '725', '626', '96', '15', '50', '40', '0', '0')";



            build.append("INSERT INTO `user_monsters` VALUES " + mon );
            getStatement().execute(build.toString());



            System.out.println("Monsters Added: " + getStatement().getUpdateCount());

        }catch(Exception ex){
            System.out.println("Error is found :"+ex);

        }

    }
    /*
        *** Adding individual monsters to userid
     */
    public void createUserMonsterDatabase(){
        try{
            String sqlQuery = "CREATE TABLE sql5411932.user_monsters ( userid INT(4) NOT NULL , monsterid INT(2) NOT NULL, name VARCHAR(30) NOT NULL," +
                    "HP INT(5) NOT NULL, ATK INT(5) NOT NULL, DEF INT(5) NOT NULL, SPD INT(5) NOT NULL, "+
                    "CR INT(5) NOT NULL, CD INT(5) NOT NULL, RES INT(5) NOT NULL, ACC INT(5) NOT NULL, engraved BOOLEAN NOT NULL) ENGINE = InnoDB;";

            getStatement().execute(sqlQuery);

        }catch (SQLException e){
            System.out.println("Error found: " + e);
        }
    }

    public static void main(String[] args) {
//        MonsterDB connct = new MonsterDB();
//        connct.addToMonsterDatabase();
//        //connct.removeMonsterFromDatabase();
//        //connct.getAllObjectData();
//        //connct.printAllTableData();
//        //connct.changeColumnNames();
////        connct.createUserMonsterDatabase();
//        connct.closeConnection();

    }


}