package Controller;

import Domain.DBMS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class CreateScreen {
    @FXML
    private TextField searchMyPrograms;
    @FXML
    private ListView idMyPrograms;
    @FXML
    private Button btExit;
    @FXML
    private Button btGo;
    @FXML
    private TextField fiNewProgramName;
    @FXML
    private Button idCreatenewProgram;

    @FXML
    public void createNewProgram(MouseEvent event){

    }

    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }

    public void searchString(MouseEvent mouseEvent) {
        String input = searchMyPrograms.getText();
        idMyPrograms.getItems().clear();
        if(input.equals("")){
            return;
        }
        String[] cast = MainFX.db.search.viewPrograms(input);
        for(String s : cast){
            if(s!=null)
                idMyPrograms.getItems().add(s);
        }
        idMyPrograms.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idMyPrograms.getSelectionModel().getSelectedItem().toString();
                    DBMS.at = "program";
                    MainFX.db.search.viewProgramCredits(name);
                    MainFX.setScene("/FXML/EditScreen.fxml", name );
                }
            }
        });
    }
}
