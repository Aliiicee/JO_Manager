package com.jomanager.jo_manager.Views;

import com.jomanager.jo_manager.Controllers.MedalsCellController;
import com.jomanager.jo_manager.Models.Medal;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class MedalsCellFactory extends ListCell<Medal> {
    @Override
    protected void updateItem(Medal medal, boolean empty) {
        super.updateItem(medal, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/MedalsCell.fxml"));
            MedalsCellController controller = new MedalsCellController(medal);
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
