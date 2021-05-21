package Database;

import Domain.DB;
import Domain.Program;

import java.sql.*;

public class PGSQL {
    Connection connection;


    public PGSQL() {
        connection = connect();
        query("CREATE TABLE users(name TEXT, password TEXT, id SERIAL NOT NULL, type TEXT);");
        query("CREATE TABLE updates(msg TEXT, userid INTEGER, read BOOLEAN);");
        query("CREATE TABLE favorites(userid INTEGER, program INT);");
        query("CREATE TABLE history(userid INTEGER, program INTEGER);");
        query("CREATE TABLE program(name TEXT, id SERIAL NOT NULL, owner INTEGER, verified BOOLEAN, views INTEGER, avgrating FLOAT);");
        query("CREATE TABLE casts(name TEXT, id SERIAL NOT NULL, owner INTEGER, verified BOOLEAN, views INTEGER);");
        query("CREATE TABLE credit(program INTEGER, castid INTEGER, role TEXT);");
        query("CREATE TABLE comments(msg TEXT, userid INTEGER, program INTEGER);");
        query("CREATE TABLE ratings(score INTEGER, userid INTEGER, program INTEGER);");
    }

    static Statement statement = null;

    public Connection connect() {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tv2","postgres","postgres");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }

    public void query(String query) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void readUpdates(int userID){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT msg FROM updates WHERE userid = " + userID);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            //UPDATE table_name SET twitter_handle = '@taylorswift13' WHERE id = 2;
            query("UPDATE updates SET read = TRUE WHERE userid = " + userID);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPrograms(){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM program WHERE verified = TRUE");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewCast(){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM casts WHERE verified = TRUE");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
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
    public String[] setUserData(String name){
        String[] res = new String[4];

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE name = '" + name + "'");
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

    public void viewProgramCredits(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.print(getCastName(rs.getInt(2)) + ": ");
                System.out.println(rs.getString(3 ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
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
