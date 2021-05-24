package Controller;

import Domain.DBMS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class CreditView {
    @FXML
    public ListView idCreditList;
    @FXML
    public Label idUsername;
    @FXML
    public TextField idCommentText;
    @FXML
    public ComboBox idRateBox;
    @FXML
    public ListView idCommentList;
    @FXML
    public Button btAddFavorite;
    @FXML
    public Button btComment;
    @FXML
    public Button btRate;
    public Label idCommentTag;
    @FXML
    private Label idName;
    @FXML
    public Label idViews;
    @FXML
    public Label idRating;

    @FXML
    public void initialize(){
        idUsername.setText(DBMS.currentCustomer.name);
        idRateBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(DBMS.at.equals("program")){
            idName.setText(DBMS.currentProgram.getName());
            idViews.setText("Views: " + DBMS.currentProgram.getViews());
            idRating.setText("Rating: " + DBMS.currentProgram.getAvgRating());
            idCommentTag.setVisible(true);
            idCommentList.setVisible(true);
            idRating.setVisible(true);
            populateComments();
        }
        else if(DBMS.at.equals("cast")){
            idName.setText(DBMS.currentCast.getName());
            idViews.setText("Views: " + DBMS.currentCast.getViews());
            idRating.setText("Rating: " + DBMS.currentCast.getAvgRating());
            idCommentTag.setVisible(false);
            idCommentList.setVisible(false);
            idCommentText.setVisible(false);
            idRateBox.setVisible(false);
            idRating.setVisible(false);
            btRate.setVisible(false);
            btComment.setVisible(false);
        }
        setVisibility();

        if(DBMS.at.equals("program") && !DBMS.currentCustomer.type.equals("visitor")){
            btAddFavorite.setVisible(true);
            btRate.setVisible(true);
            idRateBox.setVisible(true);
            idCommentText.setVisible(true);

        }
        else{
            btComment.setVisible(false);
            btAddFavorite.setVisible(false);
            btRate.setVisible(false);
            idRateBox.setVisible(false);
            idCommentText.setVisible(false);
        }
        populateCredits();
        idCreditList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    String name = idCreditList.getSelectionModel().getSelectedItem().toString();
                    name = name.substring(0, name.indexOf(" |"));
                    System.out.println(name);
                    if(DBMS.at.equals("program")){
                        DBMS.at = "cast";
                        MainFX.db.search.viewCastCredits(name);
                    }
                    else if(DBMS.at.equals("cast")){
                        DBMS.at = "program";
                        MainFX.db.search.viewProgramCredits(name);
                    }
                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }
    private void setVisibility(){
        if(DBMS.currentCustomer.type.equals("visitor")){
            btAddFavorite.setVisible(false);
            btComment.setVisible(false);
            btRate.setVisible(false);
            idCommentText.setVisible(false);
            idRateBox.setVisible(false);
        }
        else{
            btAddFavorite.setVisible(true);
            btComment.setVisible(true);
            btRate.setVisible(true);
            idCommentText.setVisible(true);
            idRateBox.setVisible(true);
        }
    }

    private void populateCredits(){
        String[] credits = null;
        if(DBMS.at.equals("program")) {
            credits = MainFX.db.search.getProgramCredits(DBMS.currentProgram.getId());
        }
        else if (DBMS.at.equals("cast")){
            credits = MainFX.db.search.getCastCredits(DBMS.currentCast.getId());
        }

        for(String s : credits){
            if(s!=null)
                idCreditList.getItems().add(s);
        }
    }
    private void populateComments(){
        idCommentList.getItems().clear();
        String[] credits = null;
        if(DBMS.at.equals("program")) {
            credits = MainFX.db.search.getComments(DBMS.currentProgram.getId());
        }
//        else if (DBMS.at.equals("cast")){
//            credits = MainFX.db.search.getCastCredits(DBMS.currentCast.getId());
//        }

        for(String s : credits){
            if(s!=null)
                idCommentList.getItems().add(s);
        }
    }

    @FXML
    private void back(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Search for program" );
    }

    public void addFavorite(MouseEvent mouseEvent) {
        MainFX.db.addToFavorites(DBMS.currentProgram.getId());
    }

    public void addComment(MouseEvent mouseEvent) {
        String comment = idCommentText.getText();
        DBMS.currentCustomer.addComment(comment, DBMS.currentProgram.getId());
        populateComments();
    }

    public void addRating(MouseEvent mouseEvent) {
        int rate = Integer.parseInt(idRateBox.getValue().toString());
        DBMS.currentProgram.addRating(rate, DBMS.currentCustomer.id);
        idRating.setText("Rating: " + DBMS.currentProgram.getAvgRating());
    }
}
