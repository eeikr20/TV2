package Domain;

import Database.PostgresDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBMS extends Application {
    //Attributes
    //public static User currentUser;
    public static Customer currentCustomer;
    private boolean run;
    private static Stage stage;
    public static PostgresDB postgresDB = new PostgresDB();


    @Override
    public void start(Stage stage) throws Exception {
        DBMS.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomeScreen.fxml"));
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }
    public void launcher(){launch();}

    public static Stage getStage() {
        return stage;
    }

    //constructor
    public DBMS(){
        this.run = true;
        //todo update current user?
        //this.currentUser = new Visitor();
        this.currentCustomer = new Customer("", "", 0, "visitor");
        //this.postgresDB = new PostgresDB();
    }
    //Methods

    public static PostgresDB getPostgresDB() {
        return postgresDB;
    }

    public void runCommand(){
        initTest();
        while (run){
            System.out.println("The customer is of type: " + currentCustomer.type);
            System.out.println("Awaiting input");
            String s = DB.scanner.nextLine();
            if (s.equals("end")){
                run=false;
            }
            else {
                //currentUser.runCommand(s);
                currentCustomer.runCommand(s);
            }
        }
        System.out.println("farvel");

        postgresDB.returnQuery("SELECT * FROM users");
        postgresDB.clearDB();
    }
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
    /*
    public void setCurrentUser(User user){
        this.currentUser = user;
    }
*/
    public void initTest() {
        //Producer producer = new Producer("producer", "producer", "producer");
        //DB.users.put("producer", producer);
        postgresDB.query("INSERT INTO users VALUES ('producer','producer',0,'producer');");

        //Administrator administrator = new Administrator("administrator", "admin", "admin");
        //DB.users.put("admin", administrator);
        postgresDB.query("INSERT INTO users VALUES ('admin','admin',0,'administrator');");

        Program p1 = new Program("Star Wars");
        Program p2 = new Program("Lord of the Rings");

        DBMS.postgresDB.query("INSERT INTO casts VALUES ('Mark Hamill', DEFAULT, 1, TRUE)");
        new Role("Star Wars", "Mark Hamill", "Luke Skywalker");
        DBMS.postgresDB.query("INSERT INTO casts VALUES ('Harrison Ford', DEFAULT, 1, TRUE)");
        new Role("Star Wars", "Harrison Ford", "Han Solo");

        //DB.programs.put("Star Wars", p1);
        //DB.programs.put("Lord of the Rings", p2);
    }
}
