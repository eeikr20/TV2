package Controller;

import Domain.DBMS;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeScreen {





    @FXML
    void logIn(MouseEvent event) throws IOException {

        FXMLLoader root = new FXMLLoader(getClass().getResource("/FXML/LogInScreen.fxml"));
        Scene scene = new Scene(root.load(), 600, 400);

        Stage stage = DBMS.getStage();
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void searchBar(ActionEvent event){

    }
    @FXML
    void signIn(ActionEvent event){

    }


}
