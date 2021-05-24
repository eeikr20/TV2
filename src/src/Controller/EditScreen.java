package Controller;

import Domain.DBMS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;



public class EditScreen {
    @FXML
    public ListView idCastList;
    @FXML
    public TextField fiNewCastName;
    @FXML
    private Button btAdd;
    @FXML
    private Label idName;
    @FXML
    private Button btVerifyProgram;
    @FXML
    private Button btVerifyCast;
    @FXML
    private Button btVerifyCredit;
    @FXML
    private Button btAddCredit;
    @FXML
    private Button btRemoveCredit;
    @FXML
    private ListView idCreditList;
    @FXML
    private ListView idCastlist;
    @FXML
    private TextField fiCastName;

    @FXML
    public void initialize(){
        idName.setText(DBMS.currentProgram.getName());

        if(DBMS.currentCustomer.type.equals("administrator")){
            btVerifyProgram.setVisible(true);
            btVerifyCast.setVisible(true);
            btVerifyCredit.setVisible(true);
        }
        else {
            btVerifyProgram.setVisible(false);
            btVerifyCast.setVisible(false);
            btVerifyCredit.setVisible(false);
        }
        populateCredits();
    }
    private void populateCredits(){
        String[] credits = null;
        credits = MainFX.db.search.getProgramCredits(DBMS.currentProgram.getId());
        for(String s : credits){
            if(s!=null)
                idCreditList.getItems().add(s);
        }
    }
    @FXML
    public void addCredit(MouseEvent event) {

    }

    @FXML
    public void searchString(MouseEvent event) {
        String input = fiCastName.getText();
        idCastList.getItems().clear();
        if(input.equals("")){
            return;
        }
        String[] cast = MainFX.db.search.viewMyCast(input);
        for(String s : cast){
            if(s!=null)
                idCastList.getItems().add(s);
        }
        idCastList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idCastList.getSelectionModel().getSelectedItem().toString();
                    DBMS.at = "cast";
                    MainFX.db.search.viewCastCredits(name);
                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }

    @FXML
    public void removeCredit(MouseEvent event) {

    }

    @FXML
    public void addNewCast(MouseEvent event) {
        String input = fiNewCastName.getText();
        if(input.equals("")){
            return;
        }
        MainFX.db.crediting.createCast(input);
    }

    @FXML
    public void verifyProgram(MouseEvent event) {

    }

    @FXML
    public void verifyCast(MouseEvent event) {

    }

    @FXML
    public void verifyCredit(MouseEvent event) {

    }


}