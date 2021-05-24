package Database;

import Controller.MainFX;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchSQL {
    private int dbSize(String table){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT count(*) FROM " + table + " WHERE verified = TRUE");
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public String[] viewAllPrograms(){
        //System.out.println(dbSize("program"));
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE verified = TRUE");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public String[] viewAllCast(){
        String[] list = new String[dbSize("casts")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE verified = TRUE");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public String[] viewPrograms(String input){
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE verified = TRUE AND UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public String[] viewCast(String input){
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE verified = TRUE AND UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] sortViewesPrograms() {
        //System.out.println(dbSize("program"));
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE verified = TRUE ORDER BY views DESC");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] sortViewesCast() {
        //System.out.println(dbSize("program"));
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE verified = TRUE ORDER BY views DESC");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] sortRatesPrograms() {
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE verified = TRUE ORDER BY avgrating DESC");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] sortRatesCast() {
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE verified = TRUE ORDER BY avgrating DESC");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public String[] viewProgramCredits(int id){
        String[] res = new String[6];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select * from program where id = " + id);
            while (rs.next()){
                res[0] = rs.getString(1);
                res[1] = rs.getString(2);
                res[2] = rs.getString(3);
                res[3] = String.valueOf(rs.getBoolean(4));
                res[4] = rs.getString(5);
                res[5] = rs.getString(6);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public String[] getProgramCredits(int id){
        String[] res = new String[dbSize("credit")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select castid, role from credit where program = " + id);
            //ResultSet rs2 = PGSQL.statement.executeQuery("select role from credit where program = " + id);
            int i = 0;
            while (rs.next()){
                res[i] = getCastName(rs.getInt(1)) + " | " + rs.getString(2);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getCastName(int id){
        String s = "";
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select name from casts where id = " + id);
            while (rs.next()){
                s = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return s;
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

    public String[] viewCastCredits(int id) {
        String[] res = new String[6];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select * from casts where id = " + id);
            while (rs.next()){
                res[0] = rs.getString(1);
                res[1] = rs.getString(2);
                res[2] = rs.getString(3);
                res[3] = String.valueOf(rs.getBoolean(4));
                res[4] = rs.getString(5);
                res[5] = rs.getString(6);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public String[] getCastCredits(int id) {
        String[] res = new String[dbSize("credit")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select program, role from credit where castid = " + id);
            //ResultSet rs2 = PGSQL.statement.executeQuery("select role from credit where program = " + id);
            int i = 0;
            while (rs.next()){
                res[i] = getProgramName(rs.getInt(1)) + " | " + rs.getString(2);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public String[] getComments(int id) {
        String[] res = new String[500];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select userid, msg from comments where programid = " + id);
            //ResultSet rs2 = PGSQL.statement.executeQuery("select role from credit where program = " + id);
            int i = 0;
            while (rs.next()){
                res[i] = getUserName(rs.getInt(1)) + " : " + rs.getString(2);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public String getUserName(int id) {
        String s = "";
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select name from users where id = " + id);
            while (rs.next()){
                s = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String[] viewMyCast(String input) {
        String[] list = new String[dbSize("casts")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE (owner = " + MainFX.db.getCurrentCustomer().id + " OR verified = TRUE) AND UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] viewAdminPrograms(String input) {
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] viewMyPrograms(String input) {
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM program WHERE (owner = " + MainFX.db.getCurrentCustomer().id + " OR verified = TRUE) AND UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] getAllAdmins() {
        String[] list = new String[500];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM users WHERE type = 'administrator'");
            int i = 0;
            while (rs.next()){
                //System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public String[] viewAdminCast(String input) {
        String[] list = new String[dbSize("program")];
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("SELECT name FROM casts WHERE UPPER(name) LIKE UPPER('%" + input + "%')");
            int i = 0;
            while (rs.next()){
                System.out.println(rs.getString(1));
                list[i] = rs.getString(1);
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


}
