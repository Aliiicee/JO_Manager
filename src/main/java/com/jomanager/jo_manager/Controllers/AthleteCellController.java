package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Athlete;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AthleteCellController implements Initializable {
    public ImageView flag_img;
    public Label name_lbl;
    public Label age_lbl;
    public Label sex_lbl;
    public Label height_lbl;
    public Label weight_lbl;

    private final Athlete athlete;

    public AthleteCellController(Athlete athlete) {
        this.athlete = athlete;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
