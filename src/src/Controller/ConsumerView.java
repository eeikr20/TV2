package Controller;



import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


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
        }
        else {
            btCreateSuperUser.setVisible(false);
        }
    }


    @FXML
    void logIn(MouseEvent event) {
        MainFX.setScene("/FXML/LogInScreen.fxml", "Log In" );
    }
    @FXML
    void exit() {
        MainFX.db.pgSQL.clearDB();
        MainFX.setScene("/FXML/HomeScreen.fxml", "Home Screen");
    }
    @FXML
    void create(MouseEvent event) {
        MainFX.setScene("/FXML/CreateScreen.fxml", "Program chooser" );
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
        MainFX.setScene("/FXML/NotificationScreen.fxml", "Notifications");
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
            MainFX.db.at = "program";
            for(String s : program){
                if(s!=null)
                    idList.getItems().add(s);
            }
        }
        else if(btType.equals("Casts")){
            MainFX.db.at = "cast";
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

                    if(btType.equals("Programs")){
                        MainFX.db.at = "program";
                        MainFX.db.search.viewProgramCredits(name);
                    }
                    else if(btType.equals("Casts")){
                        String id = idList.getSelectionModel().getSelectedItem().toString().substring(name.indexOf(" | ")+3, name.length());
                        int i = Integer.valueOf(id);
                        MainFX.db.at = "cast";
                        MainFX.db.search.viewCastCredits(i);
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
