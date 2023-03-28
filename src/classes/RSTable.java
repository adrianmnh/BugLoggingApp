package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RSTable extends ArrayList<Object> {

    public List<String> header;
    public List<Object> data;
    int rowCount;
    int colCount;

    public RSTable(ResultSet rs){
        processResultSet(rs);
    }
    private void processResultSet(ResultSet rs){
        try{

            if(rs != null){
                this.colCount = rs.getMetaData().getColumnCount();
                this.header = new ArrayList<String>();
                for (int i = 1; i <= colCount; i++){
                    this.header.add(rs.getMetaData().getColumnName(i).toUpperCase());
                }

                data = new ArrayList<Object>();

                while (rs.next()) {
                    Object[] o = new Object[colCount];
                    for (int col = 1; col <= colCount; col++){
                        o[col-1] = rs.getObject(col);
                    }
                    this.data.add(o);
                }
                this.rowCount = data.size();
            }
        } catch (SQLException e){
        }
    };

    public Object get(String colName, int row){
        colName = colName.toUpperCase();
        if(header.contains(colName)){
            int col = header.indexOf(colName);
            System.out.println(data.get(row));
        }

        return null;
    }

    public String toString(){
        String s = header.toString();
        for (Object d: data ) {
            s += "\n" + Arrays.toString((Object[])d);
        }
        return s;
    }
}
