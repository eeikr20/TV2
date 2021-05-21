package Controller;

import javafx.fxml.FXML;
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
    void exit(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/ConsumerView.fxml"), "Search for program" );

    }
    @FXML
    void signUp(MouseEvent event) throws IOException {
        //MainFX.db.getCurrentCustomer().login.signUp(fiUsername.getText(), fiPassword.getText());

        MainFX.setScene(getClass().getResource("SignUpScreen.fxml"), "Sign Up" );

    }
    @FXML
    void confirmLogIn(MouseEvent event) throws IOException {
        MainFX.db.getCurrentCustomer().login.login(fiUsername.getText(), fiPassword.getText());
        /*
        if (String fiUsername !& fiPassword == )
        System.out.println("Wrong Username and/or Password");
        else
         */
            // Set current user to account the correct crudential is ergo. Admin or producer or account
        // perhaps a popup screen to display succesfully on login
        MainFX.setScene(getClass().getResource("/FXML/ConsumerView.fxml"), "Home Screen" );

    }

}
