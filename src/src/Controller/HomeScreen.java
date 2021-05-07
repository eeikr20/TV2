package Controller;

import Domain.DBMS;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Domain.DB;
import java.io.IOException;


public class HomeScreen {



    @FXML
    void logIn(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/LogInScreen.fxml"), "Log In" );

    }
    @FXML
    void logOut() throws IOException {
        MainFX.setScene(getClass().getResource("/FXML/HomeScreen.fxml"), "Home Screen");
    }
    @FXML
    void create(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/CreateScreen.fxml"), "Create" );

    }

    @FXML
    void searchBar(ActionEvent event){

    }
    @FXML
    void signIn(MouseEvent event) throws IOException{


        MainFX.setScene(getClass().getResource("SignUpScreen.fxml"), "Sign Up" );


    }



}
