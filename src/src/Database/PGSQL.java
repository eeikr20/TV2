package Database;

import Controller.MainFX;
import java.sql.*;

public class PGSQL {
    public static Connection connection;
    public NotificationSQL notificationSQL = new NotificationSQL();
    public LoginSQL loginSQL = new LoginSQL();
    public SearchSQL searchSQL = new SearchSQL();
    public FavoritesSQL favoritesSQL = new FavoritesSQL();
    public VerifySQL verifySQL = new VerifySQL();
    public CreditingSQL creditingSQL = new CreditingSQL();


    public PGSQL() {
        connection = connect();
    }
    public void incProgramView(String name) {
        query("UPDATE program SET views = " + (1 + MainFX.db.currentProgram.getViews()) + " WHERE name = '" + name + "'");
        if (!MainFX.db.currentCustomer.type.equals("visitor")){
            query("INSERT INTO history VALUES(" + MainFX.db.currentCustomer.id + ", " + favoritesSQL.getProgramID(name) + ")");
        }
    }

    public static Statement statement = null;

    public Connection connect() {
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
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

    public void addToFavorites(int user, int program){
        query("INSERT INTO favorites VALUES (" + user + ", " + program + ")");
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
        query("DROP TABLE updates");
        query("DROP TABLE favorites");
        query("DROP TABLE history");
        query("DROP TABLE credit");
        query("DROP TABLE comments");
        query("DROP TABLE ratings");
        query("DROP TABLE program");
        query("DROP TABLE casts");
        query("DROP TABLE users");
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


    public String getPassword(String query){
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
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

    public String getProgramName(int id) {
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void incCastView(int id) {
        query("UPDATE casts SET views = " + (1 + MainFX.db.currentCast.getViews()) + " WHERE id = " + id);
    }
    public void incProgramView(int name) {
        query("UPDATE program SET views = " + (1 + MainFX.db.currentProgram.getViews()) + " WHERE name = '" + name + "'");
    }
    public String[] getHistory(int id) {
        String[] list = new String[100];
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT program FROM history WHERE userid = " + id);
            int i = 0;
            while (rs.next()){
                list[i] = getProgramName(rs.getInt(1));
                i = i + 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void addComment(String comment, int userid, int programid) {
        query("INSERT INTO comments VALUES('" + comment + "', " + userid + ", " + programid + ")");
    }

    private boolean alreadyRated(int userid, int programid){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select count(*) from ratings where userid = " + userid + "AND programid = " + programid);
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

    public void addRating(int rate, int userid, int programid) {

        if(alreadyRated(userid, programid)){
            query("UPDATE ratings SET score = " + rate + " WHERE userid = " + userid + "AND programid = " + programid);
        }
        else {
            query("INSERT INTO ratings VALUES(" + rate + ", " + userid + ", " +  programid + ")");
        }
    }

    public float calculateRating(int id) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT AVG(score) FROM ratings WHERE programid = " + id);
            rs.next();
            return rs.getFloat(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public void setRating(int id, float newRating) {
        query("UPDATE program SET avgrating = " + newRating + " WHERE id = " + id);
    }
}
