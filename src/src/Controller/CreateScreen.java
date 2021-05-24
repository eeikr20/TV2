package Controller;

import Domain.DBMS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreateScreen {
    @FXML
    public TextField myProgram;
    @FXML
    public ListView idList;


    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }

    public void searchString(MouseEvent mouseEvent) {
        String input = myProgram.getText();
        idList.getItems().clear();
        if(input.equals("")){
            return;
        }
        String[] cast = MainFX.db.search.viewPrograms(input);
        for(String s : cast){
            if(s!=null)
                idList.getItems().add(s);
        }
        idList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idList.getSelectionModel().getSelectedItem().toString();
                    DBMS.at = "program";
                    MainFX.db.search.viewCastCredits(name);
                    MainFX.setScene("/FXML/EditScreen.fxml", name );
                }
            }
        });
    }
}
