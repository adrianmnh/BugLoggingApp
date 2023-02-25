package classes;

//import classes.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDriver extends DatabaseMainDriver {
//    private Connection con;
//    private Statement st;

    public DatabaseUserDriver(File f) {
        super(f);
        setDbSchema("Users");
        setDbTable("Account");
        setFQTN();
    }
//    public void getData(){
//        try{
//            System.out.println("get Data tries");
//
//            String sql = selectAll + getFQTN()+";";
//            System.out.println(sql);
//            rs = query().executeQuery(sql);
//            System.out.println("Data from online Database :");
//            while(rs.next()){
//                String name = rs.getString("username");
//                String area = rs.getString("password");
//                String pin = rs.getString("email");
//                System.out.println("username:"+name+"\n"+"password :"+area+"\n"+"email:"+pin+"\n");
//
//            }
//
//        }catch(Exception ex){
//            System.out.println("Error is found :"+ex);
//        }
//    }





    public String addNewUserQuery(User u) throws Exception {
        String query = String.format("INSERT INTO %s (UserName, Password, Email, unsafeKeep) VALUES (%s, %s, %s, %s);",
                getFQTN(), u.getUsername(true), u.getHash(), u.getEmail(true), u.getPhrase(false));
//        String query = String.format("INSERT INTO %s (UserName, Password, Email) VALUES (%s, %s, %s);",
//                getFQTN(), u.getUsername(true), u.hashPassword(u.getPhrase(true)), u.getEmail(true));
        System.out.println(query);
        return query;
    }

    public ArrayList<Integer> checkLoginDetails(User u)  {
        // return : HTTP Code, User ID,
        ArrayList<Integer> toReturn = new ArrayList<>(List.of(-1,-1,-1));
        ResultSet rs = null;
        try{
            ArrayList response = this.execQuery(this.SQLSearchUser(u.getUsername(false)));
            System.out.println("Response from main driver: " +  response);
            rs = (ResultSet) response.get(1);
            toReturn.set(0, (int)response.get(0));
            /// new exception nested
            if(rs != null && rs.next()){
                String dbResponse = (String)rs.getObject(3);
                toReturn.set(0,200);
                System.out.println(u.getPhrase(false));
                if(u.getPhrase(true).replace("\"'", "").equals(dbResponse)){
                    int userId = (int)rs.getObject(1);
                    toReturn.set(1,userId);
                    toReturn.set(2,200);
                    System.out.println("user found. 200 OK");
                } else {
                    toReturn.set(2,-200);
                    System.out.println("Incorrect Password. -200 OK");
                }
            } else if(rs != null && !rs.next()){
                toReturn.set(0, 404);
                System.out.println("User not found");
            }
        }
        catch (SQLException e){
            System.out.println("NO Connection: \nset User Login ID exception: " + e.getMessage() );
            System.out.println(e.getClass());
        }
        catch (Exception ex){
            System.out.println("set User Login ID SQL \n unknown Exception caught: " + ex.getMessage());
            System.out.println(ex.getClass());
            toReturn.set(0,-12345);
        }
        return toReturn;


    }


//    public int userCheck(User u){
//        try{
//            ResultSet rs = getStatement().executeQuery(SQLSearchUser(u.getUsername(false)));
//            System.out.println("Searching User");
//            if (rs.next()){
//                return 200;
//            }
//        }catch(Exception e){
//            System.out.println("400 BAD REQUEST");
//            return 400; //BAD REQUEST
//        }
//        System.out.printf("user %s NOT FOUND ERROR 404 \n",u.getUsername(false));
//        return 404; // NOT FOUND
//    }
//
//    public int userExists(User u){
//
//        System.out.println("***********In User Exists Check********");
//
//        try{
//            ResultSet rs = getStatement().executeQuery(SQLSearchAllUsersQuery());
//            System.out.println("Checking user credentials");
//            while(rs.next()){
//                if(rs.getObject(2).equals(u.getUsername(false))){
//                    System.out.printf("username: '%s' found\n", u.getUsername(false));
//                    if(Arrays.equals(rs.getObject(3).toString().toCharArray(),u.getPchar())){
//                        System.out.println("username and passwordmatch!");
//                        //return true;
//                        return Integer.parseInt(rs.getObject(1).toString());
//                    }
//                    else{
//                        System.out.printf("incorrect password\n");
//                    }
//                }
//            }
//        }catch(Exception e){
////            System.out.println("Error found. " + e);
//            System.out.println("400 BAD REQUEST");
//            return 400; //BAD REQUEST
//            //return false;
//        }
//        System.out.printf("user %s NOT FOUND ERROR 404 \n",u.getUsername(false));
//        return 404; // NOT FOUND
//        //return false;
//
//    }
    public String SQLSearchUser(String u){
        String query = "SELECT * FROM " + getFQTN() + " WHERE UserName = '" + u + "';";
        return query;
    }

    private String SQLSearchAllUsersQuery(){
        String query = "SELECT * FROM " + getFQTN();
        return query;
    }


    public static void main(String[] args) {
//        DatabaseUserDriver connct = new DatabaseUserDriver(f);
//        connct.getData();
    }


}