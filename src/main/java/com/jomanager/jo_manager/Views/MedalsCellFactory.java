package com.jomanager.jo_manager.Views;

import com.jomanager.jo_manager.Controllers.MedalsCellController;
import com.jomanager.jo_manager.Models.Medals;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class MedalsCellFactory extends ListCell<Medals> {
    @Override
    protected void updateItem(Medals medals, boolean empty) {
        super.updateItem(medals, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/MedalsCell.fxml"));
            MedalsCellController controller = new MedalsCellController(medals);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
