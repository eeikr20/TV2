package Database;

import Domain.DB;
import Domain.Program;

import java.sql.*;

public class PostgresDB {
    Connection connection;


    public PostgresDB() {
        connection = connect();
        query("CREATE TABLE users(name TEXT, password TEXT, id INTEGER, type TEXT);");
        query("CREATE TABLE updates(msg TEXT, userid INTEGER, read BOOLEAN);");
        query("CREATE TABLE favorites(userid INTEGER, program INT);");
        query("CREATE TABLE history(userid INTEGER, program INTEGER);");
        query("CREATE TABLE program(name TEXT, id INTEGER, owner INTEGER, verified BOOLEAN);");
        query("CREATE TABLE casts(name TEXT, id INTEGER, owner INTEGER, verified BOOLEAN);");
        query("CREATE TABLE credit(program INTEGER, castid INTEGER, role TEXT);");
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
    public void viewPrograms(){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM program");
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
            ResultSet rs = statement.executeQuery("SELECT name FROM casts");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void clearDB(){
        query("DROP TABLE users");
        query("DROP TABLE updates");
        query("DROP TABLE favorites");
        query("DROP TABLE history");
        query("DROP TABLE program");
        query("DROP TABLE casts");
        query("DROP TABLE credit");
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
