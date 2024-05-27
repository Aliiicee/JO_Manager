package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddSportController implements Initializable {
    public TextField sport_textField;
    public Button add_button;
    public Text error_txt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error_txt.setVisible(false);
        add_button.setOnAction(event -> onSubmit());
    }

    private void addSport() {
        Model.getInstance().getDatabaseDriver().addSport(sport_textField.getText());
    }

    private boolean checkFields() {
        boolean result = false;

        if (sport_textField.getText() != null && !sport_textField.getText().equals("")) {
            result = true;
        }

        return result;
    }

    private void onSubmit() {
        if (checkFields()) {
            addSport();
            Stage stage = (Stage) add_button.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_txt.setVisible(true);
        }
    }
}
