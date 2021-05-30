package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpScreen {
    @FXML
    public TextField fiPasswordshow;
    @FXML
    public PasswordField fiPassword2;
    @FXML
    public TextField fiPassword2show;
    @FXML
    private TextField fiUsername;
    @FXML
    private PasswordField fiPassword;
    @FXML
    private ComboBox idType;
    private boolean showPassword;
    @FXML
    private void initialize(){
        showPassword = false;
        fiPasswordshow.setVisible(false);
        fiPassword2show.setVisible(false);
        if(MainFX.db.getCurrentCustomer().type.equals("administrator")){
            idType.getItems().addAll("account", "producer", "administrator");
            idType.setVisible(true);
        }
        else {
            idType.setVisible(false);
        }
    }

    @FXML
    void createAccount(MouseEvent event) {
        if(MainFX.db.login.userInDB(fiUsername.getText())){
            Controller.popup("Username is already in the database");
            return;
        }
        if(fiPassword.getText().equals("")){
            Controller.popup("Enter a password");
            return;
        }
        if(fiPassword2.getText().equals("")){
            Controller.popup("Enter the password again in the second field");
            return;
        }
        if(!fiPassword.getText().equals(fiPassword2.getText())){
            Controller.popup("Passwords do not match");
            return;
        }
        if(MainFX.db.getCurrentCustomer().type.equals("administrator")){
            superAccount();
        }
        else {
            MainFX.db.login.signUp(fiUsername.getText(), fiPassword.getText());
            normalAccount();
        }
    }
    private void superAccount(){
        MainFX.db.login.createSuperUser(fiUsername.getText(), fiPassword.getText(), idType.getValue().toString());
        Controller.popup("Successfully created a new user.");
        fiUsername.clear();
        fiPassword.clear();
        fiPasswordshow.clear();
        fiPassword2.clear();
        fiPassword2show.clear();
    }
    private void normalAccount(){
        MainFX.db.login.login(fiUsername.getText(), fiPassword.getText());
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
    @FXML
    void passwordVisibility(MouseEvent event) {
        if (showPassword) {
            fiPassword.setText(fiPasswordshow.getText());
            fiPassword.setVisible(true);
            fiPasswordshow.setVisible(false);

            fiPassword2.setText(fiPassword2show.getText());
            fiPassword2.setVisible(true);
            fiPassword2show.setVisible(false);

            showPassword = false;
            return;
        }
        fiPasswordshow.setText(fiPassword.getText());
        fiPasswordshow.setVisible(true);
        fiPassword.setVisible(false);

        fiPassword2show.setText(fiPassword2.getText());
        fiPassword2show.setVisible(true);
        fiPassword2.setVisible(false);

        showPassword = true;
    }
    @FXML
    void exit(MouseEvent event) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
    @FXML
    void logIn(MouseEvent event) {
        MainFX.setScene("/FXML/LogInScreen.fxml", "Log In" );
    }
}
