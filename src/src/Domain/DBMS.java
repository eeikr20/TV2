package Domain;

import Database.PGSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

//public class DBMS extends Application {
public class DBMS {
    //Attributes
    //private boolean run;
    //private static Stage stage;
    public static Scanner scanner = new Scanner(System.in);
    public static PGSQL pgSQL = new PGSQL();

    //public static Customer currentCustomer;
    public static Customer currentCustomer = new Customer("", "", -1, "visitor");
    public static Program currentProgram = new Program("");
    public static Cast currentCast = new Cast("");
    public static String at = "";
    public Login login = new Login();
    public Search search = new Search();
    public Verification verification = new Verification();
    public Crediting crediting = new Crediting();
    public Notification notification = new Notification();
    public Favorites favorites = new Favorites();


//    public static Stage getStage() {
//        return stage;
//    }

    //constructor
    public DBMS(){
        //this.run = true;
        //this.currentUser = new Visitor();
        //this.postgresDB = new PostgresDB();
        //this.currentCustomer = new Customer("", "", -1, "visitor");
        initTest();
    }
    //Methods

    public static PGSQL getPgSQL() {
        return pgSQL;
    }

//    public void runCommand(){
//        initTest();
//        while (run){
//            System.out.println("The customer is of type: " + currentCustomer.type);
//            System.out.println("Awaiting input");
//            String s = scanner.nextLine();
//            if (s.equals("end")){
//                run=false;
//            }
//            else {
//                //currentUser.runCommand(s);
//                //currentCustomer.runCommand(s);
//            }
//        }
//        System.out.println("farvel");
//
//        pgSQL.returnQuery("SELECT * FROM users");
//        pgSQL.clearDB();
//    }
    private void viewPrograms(){

    }
    private void viewPeople(){

    }
    private void searchPrograms(){

    }
    private void searchPeople(){

    }
    private void searchByViews(){

    }
    private void searchByRating(){

    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public void addToFavorites(int program){
        pgSQL.addToFavorites(currentCustomer.id, program);
    }

    /*
        public void setCurrentUser(User user){
            this.currentUser = user;
        }
    */
    public void initTest() {
        //Producer producer = new Producer("producer", "producer", "producer");
        //DB.users.put("producer", producer);
        pgSQL.query("INSERT INTO users VALUES ('producer','producer',-3,'producer');");

        //Administrator administrator = new Administrator("administrator", "admin", "admin");
        //DB.users.put("admin", administrator);
        pgSQL.query("INSERT INTO users VALUES ('admin','admin',-2,'administrator');");

        //Program p1 = new Program("Star Wars");
        //Program p2 = new Program("Lord of the Rings");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Star Wars', DEFAULT, -3, TRUE, 100, 8.5)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Schindlers List', DEFAULT, -3, TRUE, 10, 10)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Lord of the Rings', DEFAULT, -3, TRUE, 1000, 9)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Forrest Gump', DEFAULT, -3, TRUE, 1, 8.6)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('The Wolf of Wall Street', DEFAULT, -3, TRUE, 1, 8.4)");
        DBMS.pgSQL.query("INSERT INTO program VALUES ('Indiana Jones', DEFAULT, -3, TRUE, 15, 8.8)");

        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Mark Hamill', DEFAULT, 1, TRUE, 15, 9.8)");
        //new Role("Star Wars", "Mark Hamill", "Luke Skywalker");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(2,2,'Luke Skywalker', TRUE)");
        DBMS.pgSQL.query("INSERT INTO casts VALUES ('Harrison Ford', DEFAULT, 1, TRUE, 16, 9.7)");
        //new Role("Star Wars", "Harrison Ford", "Han Solo");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(2,3,'Han Solo', TRUE)");
        DBMS.pgSQL.query("INSERT INTO credit VALUES(7,3,'Indiana Jones', TRUE)");

        //DB.programs.put("Star Wars", p1);
        //DB.programs.put("Lord of the Rings", p2);
    }
}
