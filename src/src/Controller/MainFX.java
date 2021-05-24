package Controller;

import Domain.DBMS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFX extends Application {

    private static Stage stage;
    public static DBMS db = new DBMS();



    public static void setScene(String filepath, String title) {
        Parent root = null;
        try {
            root = FXMLLoader.load(MainFX.class.getResource(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        //this.db = new DBMS();
        this.setScene("/FXML/HomeScreen.fxml", "Home Screen");
    }

//    public Stage getStage() {
//        return stage;
//    }
//
//    public void setStage(Stage stage) {
//        DB d = new BD()
//        this.stage = stage;
//    }
}
