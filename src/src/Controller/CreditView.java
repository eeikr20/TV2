package Controller;

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
        idUsername.setText(MainFX.db.currentCustomer.name);
        idRateBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if(MainFX.db.at.equals("program")){
            idName.setText(MainFX.db.currentProgram.getName());
            idViews.setText("Views: " + MainFX.db.currentProgram.getViews());
            float rating = MainFX.db.currentProgram.getAvgRating();
            if(rating == 0f){
                idRating.setText("Rating: none");
            }
            else {
                idRating.setText("Rating: " + MainFX.db.currentProgram.getAvgRating());
            }
            idCommentTag.setVisible(true);
            idCommentList.setVisible(true);
            idRating.setVisible(true);
            populateComments();
        }
        else if(MainFX.db.at.equals("cast")){
            idName.setText(MainFX.db.currentCast.getName());
            idViews.setText("Views: " + MainFX.db.currentCast.getViews());
            idRating.setText("Rating: " + MainFX.db.currentCast.getAvgRating());
            idCommentTag.setVisible(false);
            idCommentList.setVisible(false);
            idCommentText.setVisible(false);
            idRateBox.setVisible(false);
            idRating.setVisible(false);
            btRate.setVisible(false);
            btComment.setVisible(false);
        }
        setVisibility();

        if(MainFX.db.at.equals("program") && !MainFX.db.currentCustomer.type.equals("visitor")){
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
                    if(MainFX.db.at.equals("program")){
                        int id = Integer.valueOf(name.substring(name.indexOf("#")+1, name.indexOf(" | ")));
                        MainFX.db.at = "cast";
                        MainFX.db.search.viewCastCredits(id);
                    }
                    else if(MainFX.db.at.equals("cast")){
                        name = name.substring(0, name.indexOf(" | "));
                        MainFX.db.at = "program";
                        MainFX.db.search.viewProgramCredits(name);
                    }
                    MainFX.setScene("/FXML/CreditView.fxml", name );
                }
            }
        });
    }
    private void setVisibility(){
        if(MainFX.db.currentCustomer.type.equals("visitor")){
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
        if(MainFX.db.at.equals("program")) {
            credits = MainFX.db.search.getProgramCredits(MainFX.db.currentProgram.getId());
        }
        else if (MainFX.db.at.equals("cast")){
            credits = MainFX.db.search.getCastCredits(MainFX.db.currentCast.getId());
        }

        for(String s : credits){
            if(s!=null)
                idCreditList.getItems().add(s);
        }
    }
    private void populateComments(){
        idCommentList.getItems().clear();
        String[] credits = null;
        if(MainFX.db.at.equals("program")) {
            credits = MainFX.db.search.getComments(MainFX.db.currentProgram.getId());
        }
        for(String s : credits){
            if(s!=null)
                idCommentList.getItems().add(s);
        }
    }

    @FXML
    public void back(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Search for program" );
    }

    public void addFavorite(MouseEvent mouseEvent) {
        MainFX.db.addToFavorites(MainFX.db.currentProgram.getId());
    }

    public void addComment(MouseEvent mouseEvent) {
        String comment = idCommentText.getText();
        MainFX.db.currentCustomer.addComment(comment, MainFX.db.currentProgram.getId());
        populateComments();
    }

    public void addRating(MouseEvent mouseEvent) {
        int rate = Integer.parseInt(idRateBox.getValue().toString());
        MainFX.db.currentProgram.addRating(rate, MainFX.db.currentCustomer.id);
        idRating.setText("Rating: " + MainFX.db.currentProgram.getAvgRating());
    }
}
