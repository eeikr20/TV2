package Controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeScreen {

    @FXML
    private Button btSignIn;
    @FXML
    private Button btlogin;
    @FXML
    private TextField searchBar;
    @FXML
    private ListView searchList;




    @FXML
    void logIn(ActionEvent event) throws IOException {
        System.out.println("123");
        FXMLLoader root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/LogInScreen.fxml"));
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
        Scene scene = new Scene(root.load(), 600, 400);
        Stage stage = new Stage();
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
