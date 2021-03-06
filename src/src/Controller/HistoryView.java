package Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class HistoryView {
    @FXML
    public ListView idList;

    @FXML
    private void initialize(){
        idList.getItems().clear();
        populate();
        idList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idList.getSelectionModel().getSelectedItem().toString();
                    MainFX.db.at = "program";
                    MainFX.db.search.viewProgramCredits(name);
                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }
    private void populate(){
        String[] list = MainFX.db.currentCustomer.getHistory(MainFX.db.currentCustomer.id);
        for(String s : list){
            if(s!=null)
                idList.getItems().add(s);
        }
    }

    public void visit(MouseEvent mouseEvent) {
        String name = idList.getSelectionModel().getSelectedItem().toString();
        MainFX.db.at = "program";
        MainFX.db.search.viewProgramCredits(name);
        MainFX.setScene("/FXML/CreditView.fxml", name );
    }

    public void exit(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
}
