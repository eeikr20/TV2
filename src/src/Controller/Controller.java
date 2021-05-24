package Controller;

import javafx.scene.control.Alert;

public class Controller {
    public static void popup(String input){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText(input);

        alert.showAndWait();
    }
}
