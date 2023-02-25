package classes;

import javax.management.Query;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public interface DatabaseCommands {


    public void changeColumnNames();

    public void addData();



    //public void deleteRow(int i);




}

//UPDATE table SET column1 = new_value1, column2 = new_value2
//WHERE condition;

//DELETE FROM daily_sales WHERE store_state = 'DL'
///ALTER TABLE tbl AUTO_INCREMENT = 1000;

//CREATE TABLE tablename ( ... ) ENGINE=InnoDB AUTO_INCREMENT=1000;

//INSERT INTO people VALUES ( "Jim", 45, 1.75" ), ( "Peggy", 6, 1.12" )
//create function show_user_runes() returns INTEGER DETERMINISTIC NO SQL return @userid_var;
//create view user_runes_view as select * from user_runes where userid = show_user_runes()
//select * from (select @userid_var :=10 p) parm , user_runes_view s
//select userid, runeid from (select @userid_var :=10 p) parm , user_runes s
//UPDATE employees SET address = '1300 Carter St', city = 'San Jose', postalcode = 95125, region = 'CA'
//WHERE employeeID = 3;



