package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpScreen {
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmationPassword;

    @FXML
    void createAccount(ActionEvent event) {


    }
    @FXML
    void passwordVisibility(ActionEvent event) {

    }
    @FXML
    void exit(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/HomeScreen.fxml"), "Home Screen" );

    }
}
