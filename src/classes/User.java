package classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import org.mindrot.jbcrypt.BCrypt;

public class User {

    private String username;
    private char[] password;
    private String email;


    public User(String u, char[] p, String e){
        this.username = u;
        this.password = p;
        this.email = e;
    }
    public String getUsername(boolean b){
        if(b) return q(this.username);
        return this.username;
    }
    public char[] getPchar(){
        return this.password;
    }

    public String getHash(){
        return q(hashPassword());
    }
    public String getPhrase(boolean b) throws Exception {
        String pw = String.valueOf(this.password);
        if(b) {
            try{
                return hashPassword();
            } catch(Exception e){
                System.out.println("Algorithm Error: " + e);
                return "";
            }
        }
        return q(pw);
    }
    public String getEmail(boolean b){
        if(b) return q(this.email);
        return this.email;
    }

    public String q(String s){
        return "\'"+s+"\'";
    }

    public String hashPassword() {
        StringBuilder sb = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(String.valueOf(this.password).getBytes("UTF-8"));
            //convert byte array to Hexadecimal String
            sb = new StringBuilder(2*hash.length);
            for(byte b : hash){
                sb.append(String.format("%02x", b&0xff));
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exc){
            System.out.println(exc.getLocalizedMessage());
        }

        return sb.toString();
    }

    public String hashPassword(String s) {
        StringBuilder sb = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest((s).getBytes("UTF-8"));
            //convert byte array to Hexadecimal String
            sb = new StringBuilder(2*hash.length);
            for(byte b : hash){
                sb.append(String.format("%02x", b&0xff));
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exc){
            System.out.println(exc.getLocalizedMessage());
        }

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        User u = new User("adrian", "pas".toCharArray(), "email");
        System.out.println(u.getPhrase(false));
        System.out.println(u.getPchar());

        String input = "paSsword";
        String hashPword = u.hashPassword(input);
        System.out.println(hashPword);
        String input2 = "password";
        String hashPword2 = u.hashPassword(input2);
        System.out.println(hashPword2);
        System.out.println(hashPword2.equals(hashPword));
        ;
    }

}


