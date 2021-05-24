package Database;

import Domain.DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoritesSQL {
    public void add(int userID, int programID){
        DBMS.pgSQL.query("INSERT INTO favorites VALUES(" + userID + "," + programID + ")");
    }
    public int getProgramID(String program){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select id from program where name = '" + program + "'");
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public void remove(int userID, String program){
        DBMS.pgSQL.query("DELETE FROM favorites WHERE userid = " + userID + " AND program = " + getProgramID(program));
    }
    private String getProgramName(int id){
        String s = "";
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select name from program where id = " + id);
            while (rs.next()){
                s = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String[] get(int userID) {
        String[] res = new String[50];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT program FROM favorites WHERE userid = " + userID);
            //String[] list = new String[];
            int i = 0;
            while (rs.next()){
                res[i] = getProgramName(rs.getInt(1));
                //System.out.println(rs.getString(1));
                i = i + 1;
            }
            //UPDATE table_name SET twitter_handle = '@taylorswift13' WHERE id = 2;
            //PGSQL.query("UPDATE updates SET read = TRUE WHERE userid = " + userID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
