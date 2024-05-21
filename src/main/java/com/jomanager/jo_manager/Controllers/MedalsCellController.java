package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Medal;
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

    private final Medal medal;

    public MedalsCellController(Medal medal) {
        this.medal = medal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_lbl.textProperty().bind(medal.getAthleteName());
        golden_lbl.textProperty().bind(medal.getGoldenMedals_amount().asString());
        silver_lbl.textProperty().bind(medal.getSilverMedals_amount().asString());
        bronze_lbl.textProperty().bind(medal.getBronzeMedals_amount().asString());
        all_medals_lbl.textProperty().bind(medal.getMedals_amount().asString());
    }
}
