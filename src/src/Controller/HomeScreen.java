package Controller;

import Domain.DBMS;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HomeScreen {
    @FXML
    void logIn(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/LogInScreen.fxml"), "Log In" );

    }
    @FXML
    void exit() throws IOException {
        MainFX.setScene(getClass().getResource("/FXML/HomeScreen.fxml"), "Home Screen");

    }

    @FXML
    void goNext(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/ConsumerView.fxml"), "Search for program" );

    }

    @FXML
    void create(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/CreateScreen.fxml"), "Create" );

    }

    @FXML
    void searchBar(ActionEvent event){

    }
    @FXML
    void signUp(MouseEvent event) throws IOException{


        MainFX.setScene(getClass().getResource("/FXML/SignUpScreen.fxml"), "Sign Up" );


    }
}
