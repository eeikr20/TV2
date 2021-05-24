package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpScreen {
    @FXML
    private TextField fiUsername;
    @FXML
    private TextField email;
    @FXML
    private PasswordField fiPassword;
    @FXML
    private PasswordField confirmationPassword;
    @FXML
    private ComboBox idType;

    @FXML
    private void initialize(){
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
        int i = MainFX.db.login.signUp(fiUsername.getText(), fiPassword.getText());
        if(i == -1){
            Controller.popup("Username is already in the database");
        }
        if(MainFX.db.getCurrentCustomer().type.equals("administrator")){
            superAccount();
        }
        else {
            normalAccount();
        }
    }
    private void superAccount(){
        System.out.println(idType.getValue().toString());
        MainFX.db.login.createSuperUser(fiUsername.getText(), fiPassword.getText(), idType.getValue().toString());
    }
    private void normalAccount(){
        MainFX.db.login.login(fiUsername.getText(), fiPassword.getText());
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
    @FXML
    void passwordVisibility(ActionEvent event) {

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
