package Controller;

import Domain.DBMS;
import Domain.Role;
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
    public TextField fiRole;
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
    private ComboBox idAdminName;

    @FXML
    public void initialize(){
        String[] allAdmins = MainFX.db.search.getAllAdmins();
        for(String s : allAdmins){
            if(s!=null)
                idAdminName.getItems().add(s);
        }

        idName.setText(MainFX.db.currentProgram.getName());

        if(MainFX.db.currentCustomer.type.equals("administrator")){
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
        credits = MainFX.db.search.getAllProgramCredits(MainFX.db.currentProgram.getId());
        for(String s : credits){
            if(s!=null)
                idCreditList.getItems().add(s);
        }
    }
    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/CreateScreen.fxml", "My Programs" );
    }
    @FXML
    public void addCredit(MouseEvent event) {
        if(idCastList.getSelectionModel().getSelectedItem()==null){
            Controller.popup("You have to select something to visit");
            return;
        }
        String role = fiRole.getText();
        if(role.equals("")){
            Controller.popup("Enter a role");
            return;
        }
        String name = idCastList.getSelectionModel().getSelectedItem().toString();
        int id = Integer.parseInt(name.substring(name.indexOf(" | ")+3, name.length()));
        new Role(MainFX.db.currentProgram.getId(), id, role);
        fiRole.clear();
        idCreditList.getItems().clear();
        populateCredits();
    }

    @FXML
    public void searchString(MouseEvent event) {
        String input = fiCastName.getText();
        idCastList.getItems().clear();
        if(input.equals("")){
            return;
        }
        String[] cast = null;
        if(MainFX.db.getCurrentCustomer().type.equals("administrator")){
            cast = MainFX.db.search.viewAdminCast(input);
        }
        else {
            cast = MainFX.db.search.viewMyCast(input);
        }
        for(String s : cast){
            if(s!=null)
                idCastList.getItems().add(s);
        }
        idCastList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idCastList.getSelectionModel().getSelectedItem().toString();
                    String id = idCastList.getSelectionModel().getSelectedItem().toString().substring(name.indexOf(" | ")+3, name.length());
                    int i = Integer.parseInt(id);
                    MainFX.db.at = "cast";
                    MainFX.db.search.viewCastCredits(i);

                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }

    @FXML
    public void removeCredit(MouseEvent event) {
        if(idCreditList.getSelectionModel().getSelectedItem()==null){
            Controller.popup("You have to select something to visit");
            return;
        }

        String name = idCreditList.getSelectionModel().getSelectedItem().toString();
        int id = Integer.parseInt(name.substring(name.indexOf("#")+1, name.indexOf(" | ")));
        MainFX.db.crediting.removeCredit(MainFX.db.currentProgram.getId(), id);

        fiRole.clear();
        idCreditList.getItems().clear();
        populateCredits();
    }

    @FXML
    public void addNewCast(MouseEvent event) {
        String input = fiNewCastName.getText();
        String admin = idAdminName.getValue().toString();
        if(input.equals("")){
            return;
        }
        MainFX.db.crediting.createCast(input, admin);
        fiNewCastName.clear();
    }

    @FXML
    public void verifyProgram(MouseEvent event) {
        MainFX.db.verification.verifyProgram(DBMS.pgSQL.searchSQL.getUserName(MainFX.db.currentProgram.getOwner()), MainFX.db.currentProgram.getName());
    }

    @FXML
    public void verifyCast(MouseEvent event) {
        String credit = idCastList.getSelectionModel().getSelectedItem().toString();
        int id = Integer.parseInt(credit.substring(credit.indexOf(" | ")+3));
        MainFX.db.verification.verifyCast(DBMS.pgSQL.searchSQL.getUserName(MainFX.db.currentProgram.getOwner()), id);
    }

    @FXML
    public void verifyCredit(MouseEvent event) {
        String credit = idCreditList.getSelectionModel().getSelectedItem().toString();
        int id = Integer.parseInt(credit.substring(credit.indexOf("#")+1, credit.indexOf(" | ")));
        String role = credit.substring(credit.indexOf(" | ")+3);
        MainFX.db.verification.verifyCredit(MainFX.db.currentProgram.getId(), id, role);
    }
}