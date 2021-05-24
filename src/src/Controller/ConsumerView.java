package Controller;


import Domain.DBMS;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class ConsumerView {
    public Button btHistory;
    @FXML
    private Button btLogIn;
    @FXML
    private Button btSignUp;
    @FXML
    private Button btLogOut;
    @FXML
    private Button btCreate;
    @FXML
    private Button btFavorites;
    @FXML
    private Button btReadUpdates;
    @FXML
    private Button btCreateSuperUser;
    @FXML
    private Button btVerification;
    @FXML
    private Label idUsername;
    @FXML
    private Label idUpdateCount;
    @FXML
    private RadioButton idPrograms;
    @FXML
    private RadioButton idCasts;
    @FXML
    private ListView idList;
    @FXML
    private TextField idSearchBar;
    @FXML
    private Button btGo;

    private ToggleGroup group = new ToggleGroup();

    @FXML
    public void initialize(){
        setVisibility();
        idPrograms.setToggleGroup(group);
        idCasts.setToggleGroup(group);
        idPrograms.setSelected(true);
    }
    private void setUpdates(){
        int newUpdageCount = MainFX.db.notification.newUpdateCount(MainFX.db.getCurrentCustomer().id);
        if(newUpdageCount == 0){
            idUpdateCount.setText("");
        }
        else{
            idUpdateCount.setText("Unread notifications: " + newUpdageCount);
            //MainFX.db.getCurrentCustomer().notification.readUpdates(MainFX.db.getCurrentCustomer().id);
        }
    }
    private void setVisibility(){
        idUsername.setText(MainFX.db.getCurrentCustomer().name);
        setUpdates();
        String type = MainFX.db.getCurrentCustomer().type;
        boolean ifVisitor = type.equals("visitor");
        btLogIn.setVisible(ifVisitor);
        btSignUp.setVisible(ifVisitor);
        btLogOut.setVisible(!ifVisitor);
        btFavorites.setVisible(!ifVisitor);
        btHistory.setVisible(!ifVisitor);
        if(type.equals("administrator") || type.equals("producer")){
            btCreate.setVisible(true);
            btReadUpdates.setVisible(true);
        }
        else{
            btCreate.setVisible(false);
            btReadUpdates.setVisible(false);
        }
        if(type.equals("administrator")){
            btCreateSuperUser.setVisible(true);
            btVerification.setVisible(true);
        }
        else {
            btCreateSuperUser.setVisible(false);
            btVerification.setVisible(false);
        }
    }


    @FXML
    void logIn(MouseEvent event) {
        MainFX.setScene("/FXML/LogInScreen.fxml", "Log In" );
    }
    @FXML
    void exit() {
        DBMS.pgSQL.clearDB();
        MainFX.setScene("/FXML/HomeScreen.fxml", "Home Screen");
        //Platform.exit();
    }
    @FXML
    void create(MouseEvent event) {
        MainFX.setScene("/FXML/CreateScreen.fxml", "Create" );
    }

    @FXML
    void signUp(MouseEvent event) {
        MainFX.setScene("/FXML/SignUpScreen.fxml", "Sign Up" );
    }
    @FXML
    void logOut(MouseEvent event){
        MainFX.db.getCurrentCustomer().resetCustomer("", "", -1, "visitor");
        setVisibility();
    }

    public void createSuperUser(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/SignUpScreen.fxml", "Sign Up" );
    }

    public void favorites(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/FavoriteView.fxml", "Favorites");
    }

    public void readUpdates(MouseEvent mouseEvent) {
    }

    public void searchAll(MouseEvent mouseEvent) {
        idList.getItems().clear();
        searcher(MainFX.db.search.viewAllPrograms(), MainFX.db.search.viewAllCast());
    }

    public void searchViews(MouseEvent mouseEvent) {
        idList.getItems().clear();
        searcher(MainFX.db.search.sortViewesPrograms(), MainFX.db.search.sortViewesCast());
    }

    public void searchRates(MouseEvent mouseEvent) {
        idList.getItems().clear();
        searcher(MainFX.db.search.sortRatesPrograms(), MainFX.db.search.sortRatesCast());
    }
    public void searchString(MouseEvent mouseEvent) {
        String input = idSearchBar.getText();
        idList.getItems().clear();
        if(input.equals("")){
            return;
        }
        searcher(MainFX.db.search.viewPrograms(input), MainFX.db.search.viewCast(input));
    }

    private void searcher(String[] program, String[] cast){
        RadioButton getBt = (RadioButton) group.getSelectedToggle();
        String btType = getBt.getText();
        if(btType.equals("Programs")){
            DBMS.at = "program";
            for(String s : program){
                if(s!=null)
                    idList.getItems().add(s);
            }
        }
        else if(btType.equals("Casts")){
            DBMS.at = "cast";
            for(String s : cast){
                if(s!=null)
                    idList.getItems().add(s);
            }
        }
        idList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idList.getSelectionModel().getSelectedItem().toString();
                    System.out.println(name);
                    if(btType.equals("Programs")){
                        DBMS.at = "program";
                        MainFX.db.search.viewProgramCredits(name);
                    }
                    else if(btType.equals("Casts")){
                        DBMS.at = "cast";
                        MainFX.db.search.viewCastCredits(name);
                    }
                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }

    public void history(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/HistoryView.fxml", "History" );
    }
}
