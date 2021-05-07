package Domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBMS {
    //Attributes
    public static User currentUser;
    private boolean run;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    //constructor
    public DBMS(){
        this.run = true;
        this.currentUser = new Visitor();
    }
    //Methods
    public void runCommand(){
        initTest();
        while (run){
            System.out.println("Awaiting input");
            String s = DB.scanner.nextLine();
            if (s.equals("end")){
                run=false;
            }
            else {
                currentUser.runCommand(s);
            }
        }
        System.out.println("farvel");

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
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void initTest() {
        Producer producer = new Producer("producer", "producer", "producer");
        DB.users.put("producer", producer);

        Administrator administrator = new Administrator("administrator", "admin", "admin");
        DB.users.put("admin", administrator);
    }
}
