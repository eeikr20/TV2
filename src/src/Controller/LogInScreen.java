package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogInScreen {

    @FXML
    void exit(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/LogInScreen.fxml"), "Log In" );

    }
    @FXML
    void signUp(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("SignUpScreen.fxml"), "Sign Up" );

    }
    @FXML
    void confirmLogIn(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/HomeScreen.fxml"), "Home Screen" );

    }

}
