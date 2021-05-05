import Controller.HomeScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public  Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomeScreen.fxml"));
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
