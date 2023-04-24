package classes;

import panels.MainFrame;
import runes.Rune;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import panels.MainFrame;

public abstract class DatabaseMainDriver {

    private Connection connection;
    public ResultSet rs;
    private Statement sqlStatement;
    private String dbSchema;
    private String dbTable;
    private String dbfqtn; // db fully qualified table name

    public String selectAll;

    public File connectionFile = null;

    private MainFrame mainFrame;

    private int connectionStatus = -1;
    /*
     * Dont forget to call Close Connection before exiting program
     */

    public DatabaseMainDriver(File f){
        this.connectionFile = f; initConnection();
    }

//    private void setOS(){
//        System.out.print("Setting OS to: ");
//        String os = System.getProperty("os.name").toUpperCase();
//        if(os.contains("LINUX")){
//            return "linux";
//        } else if(os.contains("WINDOWS")){
//            this.OS = "windows";
//        } else this.OS = "mac";
//        System.out.println(os);
//    }
//
//    private void setEXEType(){
//        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        if (jarPath.endsWith(".jar")) {
//            System.out.println("RUNNING FROM JAR");
//            this.EXEType = "jar";
//        } else {
//            System.out.println("RUNNING FORM EXEcute class");
//            this.EXEType = "class";
//        }
//    }

    public String getRootDir(){
        Path root = null;
        String jarPath = null;

        try {
//            jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(System.getProperty("os.name").toLowerCase().contains( "windows")){
                jarPath = jarPath.replace("/", "\\");
                jarPath = jarPath.substring(1, jarPath.length());
            }
            root = Paths.get(jarPath).getParent();
            jarPath = root.toString();
            System.out.println("Using this path: " + jarPath);


        } catch (URISyntaxException ex) {
            System.out.println("Path DNE");
        }
        return jarPath;
//        return root;
    }
    private BufferedReader getBufferedReaderFromJarRoot() {
        BufferedReader bf = null;
//        try{
            System.out.println("\n\nFrom JAR ROOT::::::  2 :::::::::::");

//            String jarDir = System.getProperty("user.dir");
            String jarDir = getRootDir();
        System.out.println(jarDir);
//            File myFile = new File( System.getProperty("user.dir"), "connectionString");
            File myFile = new File( jarDir, "connectionString");
            System.out.println(myFile.getPath());



//        if(MainFrame.getRootDir())

        if(myFile == null) {
                System.out.println("not used");
                return null;
            }

            System.out.println("************************\n\t\t\t\tJAR ROOT used\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + jarDir);
            try{
                bf = new BufferedReader(new FileReader(myFile));

            } catch (FileNotFoundException e){
                System.out.println("from jar root ::2:: failed file not found exception");
            }
            return  bf;

//        }
//        catch(FileNotFoundException e2){
//            System.out.println("\n**************************File not found IN JAR\n");
////            e2.printStackTrace();
//            return null;
//        }
//        catch(Exception e3){
//            System.out.println("\n**************************Another Exception\n");
//            e3.printStackTrace();
//            return null;
//        }

    }
    private String processConnectionString(BufferedReader reader){

        try{
            String line;
            String s = "";
            while( (line = reader.readLine() ) != null){
                s += line;
            }
            return s.replaceAll("([\"+\t ])", "");
        } catch (IOException e){
            return "";
        }

    }

    private BufferedReader getBufferedReaderFromSource(){
        // This only works when the connection String file is inside the 'src' folder and is named 'connectionString'
        System.out.print("\n\nFrom CONNECTION SOURCE ::::::  3 :::::::::::");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("connectionString");
        if(inputStream == null ){
            System.out.println("not used"); return null;
        }
        System.out.println("************************\n\t\t\tSOURCE FOLDER connection used\n=====================================" );
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private BufferedReader getConnectionFileFromMainFrame() throws Exception{
        System.out.print("\n\nFrom User Selected File at Login Panel::::::  1 :::::::::::");
        if(this.connectionFile == null){
            System.out.println("not used");return null; }
        System.out.println("\n\nFrom MainFrame::::::\n\n");
        System.out.println("************************\n\t\t\t\t\tUSER SELECTED CONNECTION FILE being used\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        BufferedReader reader = new BufferedReader(new FileReader(this.connectionFile));


        return reader;

    }

    public boolean isConnected() {
        return (this.connectionStatus == 200);
//        return !(this.connection == null);
    }


    public int getConnectionStatus(){
        return this.connectionStatus;
    }

    public int initConnection(){
        System.out.println("\n/***************************************/\n" +
                "Initializing Database Connection" +
                "\n/***************************************/");
        BufferedReader reader = null;

        try{
            if(( reader = getConnectionFileFromMainFrame()) == null ) {
                if ((reader = getBufferedReaderFromJarRoot()) == null) {
                    reader = getBufferedReaderFromSource();
                }

            }
            String connectionString = processConnectionString(reader);

            System.out.println("Database.Connection");
            System.out.println(connectionString);

            connection = DriverManager.getConnection(connectionString);

            System.out.println("\n -- Connection established -- \n --" + connection);
            System.out.println(connection.getClientInfo());

            this.connectionStatus = 200;


            this.sqlStatement = connection.createStatement();
            System.out.println("\n\nStatement created\n\n");

            dbTable = null;
            selectAll = "SELECT * FROM ";
        }catch(Exception ex){
            System.out.println("ERROR NO CONNECTION :");
            this.connectionStatus = -1;

        } finally {
            return connectionStatus;
        }


    }


    public ArrayList<Integer> templateExecuteUpdate_ReturnRowsUpdated(User u, String s) {
        ArrayList<Integer> toReturn = new ArrayList<>(List.of(-1,-1,-1));
        ResultSet rs = null;
        int rowsAffected = -1;
        try{
            rowsAffected = this.getStatement().executeUpdate(s);
            System.out.println(s + "\n" + rowsAffected);
            toReturn.set(1, rowsAffected);
            /// new exception nested
            if(rowsAffected > 0){
                toReturn.set(0,200);
            }
            else if(rowsAffected == 0){
                toReturn.set(0, -200);
                System.out.println("shouldnt ever get here");
                System.out.println("No rows affected");
            }
            else {
                System.out.println("shouldnt ever get here");
                System.out.println("What happens here?");
            }
        }
        catch (SQLException e){
            System.out.println("SQL Exception: \n" + e.getMessage() + "\nsomething");
            System.out.println(e.getSQLState());
            toReturn.set(0, e.getErrorCode());
            System.out.println("Error codes: " + toReturn);
            System.out.println(e.getClass().getClassLoader());
            System.out.println(e.getCause().getLocalizedMessage());
        }
        catch (NullPointerException ex){
            System.out.println(" ---- NO CONNECTION ---- \n" + ex.getMessage());
            toReturn.set(0,-1);
            System.out.println(toReturn);
            System.out.println(ex.getClass().getClassLoader());
            System.out.println(ex.getCause().getLocalizedMessage());
        }
        catch (Exception ex){
            System.out.println(" ---- unknown Exception caught: ----\n " + ex.getMessage());
            System.out.println(ex.getClass());
            toReturn.set(0,-12345);
            System.out.println(toReturn);
            System.out.println(ex.getClass().getClassLoader());
            System.out.println(ex.getCause().getLocalizedMessage());
        }
        finally {
            return toReturn;
        }



    }

    public ArrayList<Object> execUpdate(String query) {
        System.out.println(query);
        int rowsAffected, returnCode;
        ArrayList<Object> returnObject = new ArrayList<>();
        try {
            rowsAffected = this.getStatement().executeUpdate(query);
            returnCode = 200;
        } catch(SQLException e){
            System.out.println("400 BAD REQUEST");
            System.out.println(e);
            rowsAffected = 0;
            returnCode = 400;
        }
        returnObject.add(returnCode);
        returnObject.add(rowsAffected);
        return returnObject;
    }

    public ArrayList<Object> execQuery(String query){
        System.out.println(query);
        int returnCode;
        ResultSet rs;
        ArrayList<Object> returnObject = new ArrayList<>();
        rs = null;
        returnCode = 444;
        if(this.getStatement()!=null){
            try {
                rs = this.getStatement().executeQuery(query);
                returnCode = 200;
            } catch(SQLException e){
                System.out.println("400 BAD REQUEST\n" + e.getLocalizedMessage());
                returnCode = 400;
            }
        }
        returnObject.add(returnCode);
        returnObject.add(rs);
        return returnObject;
    }


    public void closeConnection() {
        System.out.println("\n/------------------------------/\n" +
                "Closing Connection Connection" +
                "\n/------------------------------/");
        if(connection != null) {
            try{
                if(!connection.isClosed()){
                    connection.close();
                    System.out.println("*** Connection Closed ***");
                }
            }catch(SQLException e) {
                System.out.println("Unable to close connection:\n" + e);
            }
        } else {
            System.out.println("No active connection to close");
        }
    }
    public void setDbSchema(String s){this.dbSchema = n(s);}
    public void setDbTable(String s){this.dbTable = n(s);}
    public void setFQTN(){this.dbfqtn = fqtn(getDbSchema(), getDbTable() ); }
    public String getDbSchema(){ return this.dbSchema; }
    public String getDbTable(){ return this.dbTable; }
    public String getFQTN(){ return this.dbfqtn; }

    public Statement getStatement(){return this.sqlStatement; }
    public Statement query(){return this.sqlStatement; }


    public ArrayList<Object> getAllObjectData(){
        try{
            System.out.println("Retrieving all data from \"" + dbTable + "\":");
            String sql = "select * from " + dbTable;
            ResultSet result = sqlStatement.executeQuery(sql);
            System.out.println("Number of columns: " + result.getMetaData().getColumnCount());

            ArrayList<Object> monsterList = new ArrayList<Object>();
            while(result.next()){
                ArrayList<Object> monster = new ArrayList<Object>();
                for(int col=1; col<result.getMetaData().getColumnCount(); col++)
                    monster.add( result.getObject(col) );
                monsterList.add(monster);
            }
            for(Object o : monsterList)
                System.out.println(o);
            return monsterList;

        }catch(Exception ex){
            System.out.println("Error found :"+ex);
        }

        return null;

    }

    public ArrayList<Object> getRowDataObject(int i){

        try{
            System.out.println("Retrieving row " + i + " from  + getDbTable() + :");
            ArrayList<Object> rowObject = new ArrayList<Object>();

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s WHERE monster_id = %d", getDbTable(), i);

            ResultSet result = getStatement().executeQuery(sqlQuery);

            result.next();

            for(int col = 1; col < result.getMetaData().getColumnCount(); col++){
                rowObject.add(result.getObject(col));
            }
            //System.out.println(rowObject);
            return rowObject;

        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        }
        return null;
    }

    public ArrayList<Object> getRowDataObject(String column_name, String value){


        try{
            System.out.printf("Retrieving row where %s is %s from %s\n", column_name, value, getDbTable());
            ArrayList<Object> rowObject = new ArrayList<Object>();

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s WHERE %s = '%s'", getDbTable(), column_name, value);

            ResultSet result = getStatement().executeQuery(sqlQuery);
            System.out.println("Retrieved rows: " + result.getFetchSize());
            result.next();

            for(int col = 1; col < result.getMetaData().getColumnCount(); col++){
                rowObject.add(result.getObject(col));
            }
            System.out.println(rowObject);
            return rowObject;

        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        }
        return null;
    }


    public void printAllTableData() {
        try {
            System.out.println("\nDatabase.java:\n" +
                    "Printing data from online Database " + dbSchema + "." + dbTable + " :");

            String sql = "SELECT * FROM " + fqtn(dbSchema, dbTable) ;

            ResultSet resultSet = query().executeQuery(sql);

            RSTable rs = new RSTable(resultSet);

        } catch (Exception ex) {
            System.out.println("Error found :" + ex);
        }
    }



    public String n(String s){
        return "["+s+"]";
    }

    public String fqtn(String s, String t){
        return s+"."+t;
    }









}

