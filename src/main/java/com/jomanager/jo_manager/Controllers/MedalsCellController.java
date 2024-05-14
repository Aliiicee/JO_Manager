package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Medals;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MedalsCellController implements Initializable {
    public ImageView flag_img;
    public Label name_lbl;
    public Label golden_lbl;
    public ImageView golden_imgView;
    public Label silver_lbl;
    public ImageView silver_imgView;
    public Label bronze_lbl;
    public ImageView bronze_imgView;
    public Label all_medals_lbl;
    public ImageView all_medals_imgView;

    private final Medals medals;

    public MedalsCellController(Medals medals) {
        this.medals = medals;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
