package classes;

import javax.swing.*;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static void processResultSet(ResultSet rs) {


    }

    public static void printCurrentClassName(){
        StackTraceElement t = Thread.currentThread().getStackTrace()[2];
        System.out.println(t.getClassName().replace("classes.", "::::")+":::"+t.getMethodName());
    }

}

