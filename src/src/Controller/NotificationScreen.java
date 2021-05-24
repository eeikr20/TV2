package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class NotificationScreen {
    @FXML
    private ListView idNotificationList;
    @FXML
    private Button btRemoveNotification;
    @FXML
    private Button btExit;

    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
}
