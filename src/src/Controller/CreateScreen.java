package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreateScreen {

    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
}
