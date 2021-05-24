package Controller;

import Domain.DBMS;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CreditView {
    @FXML
    public ListView idCreditList;
    @FXML
    private Label idName;
    @FXML
    public Label idViews;
    @FXML
    public Label idRating;

    @FXML
    public void initialize(){
        idName.setText(DBMS.currentProgram.getName());
        idViews.setText("Views: " + DBMS.currentProgram.getViews());
        idRating.setText("Views: " + DBMS.currentProgram.getAvgRating());
        populateCredits();
    }
    private void populateCredits(){
        String[] credits = MainFX.db.search.getProgramCredits(DBMS.currentProgram.getId());
        for(String s : credits){
            if(s!=null)
                idCreditList.getItems().add(s);
        }
    }

    @FXML
    private void back(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Search for program" );
    }
}
