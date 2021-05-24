package Database;

import Domain.DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationSQL {

    public void readUpdates(int userID){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            /*
            ResultSet rs = PGSQL.statement.executeQuery("SELECT msg FROM updates WHERE userid = " + userID);
            //String[] list = new String[];
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            //UPDATE table_name SET twitter_handle = '@taylorswift13' WHERE id = 2;
            */
            PGSQL.query("UPDATE updates SET read = TRUE WHERE userid = " + userID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String[] getNotifications(int id) {
        String[] list = new String[1000];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT msg FROM updates WHERE userid = " + id);
            int i = 0;
            while (rs.next()){
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public int newUpdateCount(int userID){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT count(*) FROM updates WHERE read = FALSE AND userid = " + userID);
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public void addUpdate(String update, int userID) {
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            PGSQL.statement.executeUpdate("INSERT INTO updates VALUES ('" + update + "'," + userID + ", FALSE);");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void eraseUpdates(int userID) {
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            PGSQL.statement.executeUpdate("DELETE FROM updates WHERE userid = " + userID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
