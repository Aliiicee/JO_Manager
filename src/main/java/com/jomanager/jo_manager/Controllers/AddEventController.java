package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Models.Sport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddEventController implements Initializable {
    public ChoiceBox<String> sport_choiceBox;
    public DatePicker datePicker;
    public Button add_button;
    public TextField time_txtField;
    public Text error_txt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error_txt.setVisible(false);
        initSportChoiceBox();
        add_button.setOnAction(event -> onSubmit());
    }

    private boolean checkFields() {
        boolean result = false;

        if (datePicker.getValue() != null && time_txtField.getText() != null && sport_choiceBox.getValue() != null) {
            result = true;
        }

        return result;
    }

    private void addEvent() {
        Model.getInstance().getDatabaseDriver().addEvent(Model.getInstance().getDatabaseDriver().getSportId(sport_choiceBox.getValue()), datePicker.getValue().toString() + " " + time_txtField.getText());
    }

    private void initSportChoiceBox() {
        Model.getInstance().setSportsList();
        List<String> sportsName = new ArrayList<>();
        for (Sport sport : Model.getInstance().getSportsList()) {
            sportsName.add(sport.getName().getValue());
        }
        sport_choiceBox.getItems().addAll(sportsName);
    }

    private void onSubmit() {
        if (checkFields()) {
            addEvent();
            Stage stage = (Stage) add_button.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_txt.setVisible(true);
        }
    }
}
