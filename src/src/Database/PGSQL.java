package Database;

import Domain.DBMS;

import java.sql.*;

public class PGSQL {
    public static Connection connection;
    public NotificationSQL notificationSQL = new NotificationSQL();
    public LoginSQL loginSQL = new LoginSQL();
    public SearchSQL searchSQL = new SearchSQL();


    public PGSQL() {
        connection = connect();
        query("CREATE TABLE users(name TEXT, password TEXT, id SERIAL NOT NULL, type TEXT);");
        query("CREATE TABLE updates(msg TEXT, userid INTEGER, read BOOLEAN);");
        query("CREATE TABLE favorites(userid INTEGER, program INT);");
        query("CREATE TABLE history(userid INTEGER, program INTEGER);");
        query("CREATE TABLE program(name TEXT, id SERIAL NOT NULL, owner INTEGER, verified BOOLEAN, views INTEGER, avgrating FLOAT);");
        query("CREATE TABLE casts(name TEXT, id SERIAL NOT NULL, owner INTEGER, verified BOOLEAN, views INTEGER, avgrating FLOAT);");
        query("CREATE TABLE credit(program INTEGER, castid INTEGER, role TEXT, verified BOOLEAN);");
        query("CREATE TABLE comments(msg TEXT, userid INTEGER, program INTEGER);");
        query("CREATE TABLE ratings(score INTEGER, userid INTEGER, program INTEGER);");
    }
    public void incProgramView(String name){
        //UPDATE table_name SET age=22 WHERE id = 1;
        query("UPDATE program SET views = " + (1 + DBMS.currentProgram.getViews()) + " WHERE name = '" + name + "'");
    }

    public static Statement statement = null;

    public Connection connect() {
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tv2","postgres","postgres");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }

    public static void query(String query) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int sqlContains(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public void clearDB(){
        query("DROP TABLE users");
        query("DROP TABLE updates");
        query("DROP TABLE favorites");
        query("DROP TABLE history");
        query("DROP TABLE program");
        query("DROP TABLE casts");
        query("DROP TABLE credit");
        query("DROP TABLE comments");
        query("DROP TABLE ratings");
    }

    public int getID(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    private String getCastName(int id){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select name from casts where id = " + id);
            rs.next();
            return rs.getString(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String getPassword(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            //System.out.println(rs.getString(1));
            rs.next();
            return rs.getString(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public boolean getVerification(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            if(rs.getString(1).contains("t")){
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private String getProgramName(int id) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select name from program where id = " + id);
            rs.next();
            return rs.getString(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public void viewCastCredits(String query) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(getProgramName(rs.getInt(1)) + ": ");
                System.out.println(rs.getString(3 ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void returnQuery(String query) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2 ) + " ");
                System.out.print(rs.getString(3 ) + " ");
                System.out.println(rs.getString(4));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
