package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpScreen {
    @FXML
    private TextField fiUsername;
    @FXML
    private TextField email;
    @FXML
    private PasswordField fiPassword;
    @FXML
    private PasswordField confirmationPassword;

    @FXML
    void createAccount(MouseEvent event) {
        System.out.println(fiUsername.getText());
        System.out.println(fiPassword.getText());

        MainFX.db.getCurrentCustomer().login.signUp(fiUsername.getText(), fiPassword.getText());

    }
    @FXML
    void passwordVisibility(ActionEvent event) {

    }
    @FXML
    void exit(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/ConsumerView.fxml"), "Home Screen" );

    }
}
