package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.input.MouseEvent;

public class HomeScreen {
    
    @FXML
    void logIn(MouseEvent event) {
        MainFX.setScene("/FXML/LogInScreen.fxml", "Log In" );
    }
    @FXML
    void exit() {
        MainFX.setScene("/FXML/HomeScreen.fxml", "Home Screen");
    }

    @FXML
    void goNext(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Search for program" );
    }

    @FXML
    void create(MouseEvent event) {
        MainFX.setScene("/FXML/CreateScreen.fxml", "Create" );
    }

    @FXML
    void searchBar(ActionEvent event){

    }
    @FXML
    void signUp(MouseEvent event) {
        MainFX.setScene("/FXML/SignUpScreen.fxml", "Sign Up" );
    }
}
