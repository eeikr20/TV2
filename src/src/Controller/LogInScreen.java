package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class LogInScreen {
    @FXML
    private TextField fiUsername;
    @FXML
    private PasswordField fiPassword;

    @FXML
    private void exit(MouseEvent event) {

        MainFX.setScene("/FXML/ConsumerView.fxml", "Search for program" );

    }
    @FXML
    private void signUp(MouseEvent event) {
        //MainFX.db.getCurrentCustomer().login.signUp(fiUsername.getText(), fiPassword.getText());

        MainFX.setScene("/FXML/SignUpScreen.fxml", "Sign Up" );

    }
    @FXML
    private void confirmLogIn(MouseEvent event) {
        int login = MainFX.db.login.login(fiUsername.getText(), fiPassword.getText());
        switch (login){
            case -1 -> Controller.popup("Username is not in the database");
            case -2 -> Controller.popup("Password does not match the username");
            case 1 -> MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
        }
        //ConsumerView.class.setBtLogIn();
    }

//    void popup(String input){
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Error message");
//        alert.setHeaderText(null);
//        alert.setContentText(input);
//
//        alert.showAndWait();
//    }

}
