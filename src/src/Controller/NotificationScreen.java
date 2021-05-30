package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class NotificationScreen {
    @FXML
    public ListView idList;

    @FXML
    public void initialize(){
        populate();
        MainFX.db.notification.readUpdates(MainFX.db.currentCustomer.id);
    }
    private void populate(){
        String[] list = MainFX.db.notification.getNotifications(MainFX.db.currentCustomer.id);
        for(String s : list){
            if(s!=null)
                idList.getItems().add(s);
        }
    }

    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }

    @FXML
    public void remove(MouseEvent mouseEvent) {
        MainFX.db.notification.eraseUpdates(MainFX.db.currentCustomer.id);
        idList.getItems().clear();
    }
}
