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
        //"C:/Program Files/PostgreSQL/13/bin/psql" -U postgres -h 127.0.0.1 -f C:/Users/ander/Documents/tv2DBtest1
/*
        try {
            Runtime rt = Runtime.getRuntime();
            String executeSqlCommand = "\"C:/Program Files/PostgreSQL/13/bin/psql\" -U postgres -h 127.0.0.1 -f C:/Users/ander/Documents/tv2DBtest1";
            rt.exec("SET PGPASSWORD=postgres");
            Process pr = rt.exec(executeSqlCommand);
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
*/
/*
        try {
            ProcessBuilder  rt = new ProcessBuilder();
            String executeSqlCommand = "\"C:/Program Files/PostgreSQL/13/bin/psql\" -U postgres -h 127.0.0.1 -f C:/Users/ander/Documents/tv2DBtest1 tv2";
            //rt.command("SET PGPASSWORD=postgres");
            rt.command(executeSqlCommand);
            //int exitVal = pr.waitFor();
            //System.out.println("Exited with error code " + exitVal);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
*/
/*
        try {
            String line;
            Process p = Runtime.getRuntime().exec
                    ("C:/Program Files/PostgreSQL/13/bin/psql -U postgres -d tv2 -h jdbc:postgresql://localhost:5432/ -f C:/Users/ander/Documents/tv2DBtest1");
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
*/

//        //Registering the Driver
//        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        DriverManager.registerDriver(new com.postgresql.jdbc.Driver());
//        //DriverManager.registerDriver(new Class.forName("org.postgresql.Driver"));
//        //Getting the connection
//        String mysqlUrl = "jdbc:mysql://localhost/talakai_noppi";
//        Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
//        System.out.println("Connection established......");
//        //Initialize the script runner
//        ScriptRunner sr = new ScriptRunner(con);
//        //Creating a reader object
//        Reader reader = new BufferedReader(new FileReader("E:\\sampleScript.sql"));
//        //Running the script
//        sr.runScript(reader);
        connection = connect();



//        query("CREATE TABLE users(name TEXT, password TEXT, id SERIAL PRIMARY KEY NOT NULL, type TEXT);");
//        query("CREATE TABLE program(name TEXT, id SERIAL PRIMARY KEY NOT NULL, owner INTEGER REFERENCES users(id), verified BOOLEAN, views INTEGER, avgrating FLOAT);");
//        query("CREATE TABLE casts(name TEXT, id SERIAL PRIMARY KEY NOT NULL, owner INTEGER REFERENCES users(id), verified BOOLEAN, views INTEGER, avgrating FLOAT);");
//        query("CREATE TABLE updates(msg TEXT, userid INTEGER REFERENCES users(id), read BOOLEAN);");
//        query("CREATE TABLE history(userid INTEGER REFERENCES users(id), program INTEGER REFERENCES program(id));");
//        query("CREATE TABLE favorites(userid INTEGER REFERENCES users(id), program INTEGER REFERENCES program(id));");
//        query("CREATE TABLE credit(program INTEGER REFERENCES program(id), castid INTEGER REFERENCES casts(id), role TEXT, verified BOOLEAN);");
//        query("CREATE TABLE comments(msg TEXT, userid INTEGER REFERENCES users(id), programid INTEGER REFERENCES program(id));");
//        query("CREATE TABLE ratings(score INTEGER, userid INTEGER REFERENCES users(id), programid INTEGER REFERENCES program(id));");
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

//    private String getCastName(int id){
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("select name from casts where id = " + id);
//            rs.next();
//            return rs.getString(1);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return "";
//    }
//    public int getCastID(String name){
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("select id from casts where name = " + name);
//            rs.next();
//            return rs.getInt(1);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }
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

//    public void returnQuery(String query) {
//        try {
//            statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()){
//                System.out.print(rs.getString(1) + " ");
//                System.out.print(rs.getString(2 ) + " ");
//                System.out.print(rs.getString(3 ) + " ");
//                System.out.println(rs.getString(4));
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public void incCastView(int id) {
    //public void incCastView(String name) {
        //query("UPDATE program SET views = " + (1 + DBMS.currentProgram.getViews()) + " WHERE name = " + id);
        query("UPDATE casts SET views = " + (1 + MainFX.db.currentCast.getViews()) + " WHERE id = " + id);
    }
    public void incProgramView(int name) {
        //public void incCastView(String name) {
        //query("UPDATE program SET views = " + (1 + DBMS.currentProgram.getViews()) + " WHERE name = " + id);
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

    public Float calculateRating(int id) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT AVG(score) FROM ratings WHERE programid = " + id);
            rs.next();
            return rs.getFloat(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1f;
    }

    public void setRating(int id, float newRating) {
        query("UPDATE program SET avgrating = " + newRating + " WHERE id = " + id);
    }
    /*
    public int ratingCount(int id){
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select count(*) from ratings where programid = " + id);
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int ratingSum(int id) {
        try {
            PGSQL.statement = PGSQL.connection.createStatement();
            ResultSet rs = PGSQL.statement.executeQuery("select SUM(ratings) where programid = " + id);
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    */
}
