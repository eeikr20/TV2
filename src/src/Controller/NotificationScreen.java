package Controller;

import Domain.DBMS;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class NotificationScreen {
    @FXML
    public ListView idList;

    @FXML
    public void initialize(){
        populate();
        DBMS.pgSQL.notificationSQL.readUpdates(DBMS.currentCustomer.id);
    }
    private void populate(){
        String[] list = DBMS.pgSQL.notificationSQL.getNotifications(DBMS.currentCustomer.id);
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
        DBMS.pgSQL.notificationSQL.eraseUpdates(DBMS.currentCustomer.id);
        idList.getItems().clear();
    }
}
