package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSQL {
    public boolean userInDB(String name){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select count(*) from users where name = '" + name + "'");
            rs.next();
            if(rs.getInt(1)==0){
                return false;
            }
            else {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public String getPassword(String name){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select password from users where name = '" + name + "'");
            rs.next();
            return rs.getString(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String[] setUserData(String name){
        String[] res = new String[4];

        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT * FROM users WHERE name = '" + name + "'");
            while (rs.next()){
                res[0] = rs.getString(1);
                res[1] = rs.getString(2);
                res[2] = rs.getString(3);
                res[3] = rs.getString(4);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }
}
