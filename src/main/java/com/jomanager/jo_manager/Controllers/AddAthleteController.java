package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Country;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Models.Sport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAthleteController implements Initializable {
    public TextField firstName_textField;
    public TextField lastName_txtField;
    public DatePicker datePicker;
    public CheckBox maleCheckBox;
    public CheckBox femaleCheckBox;
    public CheckBox otherCheckBox;
    public TextField height_txtField;
    public TextField weight_txtField;
    public TextField country_txtField;
    public ChoiceBox<String> sport_choiceBox;
    public Button add_button;
    public Text error_txt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error_txt.setVisible(false);
        initSportChoiceBox();
        add_button.setOnAction(event -> onSubmit());
    }

    private boolean checkFields() {
        boolean result = false;

        if (firstName_textField.getText() != null && lastName_txtField.getText() != null && datePicker.getValue() != null && height_txtField.getText() != null && weight_txtField.getText() != null && country_txtField.getText() != null && sport_choiceBox.getValue() != null && (maleCheckBox.isSelected() || femaleCheckBox.isSelected() || otherCheckBox.isSelected())) {
            result = true;
        }

        return result;
    }

    private void addAthlete() {
        String gender = getGenderChoice();
        if (Model.getInstance().getDatabaseDriver().getCountryId(country_txtField.getText()) == 0 || Model.getInstance().getDatabaseDriver().getCountryId(country_txtField.getText()) == null) {
            Model.getInstance().getDatabaseDriver().addCountry(country_txtField.getText());
        }
        Integer countryId = Model.getInstance().getDatabaseDriver().getCountryId(country_txtField.getText());
        Integer sportId = Model.getInstance().getDatabaseDriver().getSportId(sport_choiceBox.getValue());
        Model.getInstance().getDatabaseDriver().addAthlete(firstName_textField.getText(), lastName_txtField.getText(), datePicker.getValue(), gender, Integer.parseInt(height_txtField.getText()), Double.parseDouble(weight_txtField.getText()), countryId, sportId);
    }

    private void onSubmit() {
        if (checkFields()) {
            addAthlete();
            Stage stage = (Stage) add_button.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_txt.setVisible(true);
        }
    }

    private String getGenderChoice() {
        String choice = null;
        if (maleCheckBox.isSelected()) {
            choice = "M";
        } else if (femaleCheckBox.isSelected()) {
            choice = "F";
        }

        return choice;
    }

    private void initSportChoiceBox() {
        Model.getInstance().setSportsList();
        List<String> sportsName = new ArrayList<>();
        for (Sport sport : Model.getInstance().getSportsList()) {
            sportsName.add(sport.getName().getValue());
        }
        sport_choiceBox.getItems().addAll(sportsName);
    }

}
