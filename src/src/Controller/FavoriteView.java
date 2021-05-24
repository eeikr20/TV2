package Controller;

import Domain.DBMS;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FavoriteView {
    @FXML
    public ListView idList;

    @FXML
    private void initialize(){
        idList.getItems().clear();
        populate();
    }

    private void populate(){
        String[] list = MainFX.db.favorites.get(DBMS.currentCustomer.id);
        for(String s : list){
            if(s!=null)
                idList.getItems().add(s);
        }
    }

    public void remove(MouseEvent mouseEvent) {
        if(idList.getSelectionModel().getSelectedItem()==null){
            Controller.popup("You have to select something to remove");
            return;
        }
        String name = idList.getSelectionModel().getSelectedItem().toString();
        MainFX.db.favorites.remove(DBMS.currentCustomer.id, name);
        idList.getItems().clear();
        populate();
    }

    public void visit(MouseEvent mouseEvent) {
        if(idList.getSelectionModel().getSelectedItem()==null){
            Controller.popup("You have to select something to visit");
            return;
        }
        String name = idList.getSelectionModel().getSelectedItem().toString();
        System.out.println(name);
        DBMS.at = "program";
        MainFX.db.search.viewProgramCredits(name);
        MainFX.setScene("/FXML/CreditView.fxml", name );
    }

    public void exit(MouseEvent mouseEvent) {
        MainFX.setScene("/FXML/ConsumerView.fxml", "Home Screen" );
    }
}
