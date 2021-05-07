package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreateScreen {

    @FXML
    void exit(MouseEvent event) throws IOException {

        MainFX.setScene(getClass().getResource("/FXML/HomeScreen.fxml"), "Home Screen" );

    }
}
