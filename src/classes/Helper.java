package classes;

import javax.swing.*;
import java.awt.*;
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

    public static void resize(Component jp, int width, int height){
        jp.setMinimumSize(new Dimension(width, height));
        jp.setPreferredSize(new Dimension(width, height));
        jp.setMaximumSize(jp.getPreferredSize());
        jp.setMinimumSize(jp.getPreferredSize());

    }

}

