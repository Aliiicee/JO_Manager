package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Athlete;
import com.jomanager.jo_manager.Models.Event;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Models.Sport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddMedalController implements Initializable {
    public ChoiceBox<String> athlete_choiceBox;
    public CheckBox bronzeCheckBox;
    public CheckBox silverCheckBox;
    public CheckBox goldCheckBox;
    public Button add_button;
    public Text error_txt;
    public ChoiceBox<String> event_choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error_txt.setVisible(false);
        initAthleteChoiceBox();
        initEventChoiceBox();
        add_button.setOnAction(event -> onSubmit());
    }

    private boolean checkFields() {
        boolean result = false;

        if (athlete_choiceBox.getValue() != null && (bronzeCheckBox.isSelected() || silverCheckBox.isSelected() || goldCheckBox.isSelected())) {
            result = true;
        }

        return result;
    }

    private void addMedal() {
        String medalType = getMedalType();
        String[] athleteFullName = athlete_choiceBox.getValue().split(" ");
        Integer athleteId = Model.getInstance().getDatabaseDriver().getAthleteId(athleteFullName[0], athleteFullName[1]);
        Integer sportId = null;
        Integer eventId = null;
        try {
            sportId = Model.getInstance().getDatabaseDriver().getAthleteById(athleteId).getInt("sport_id");
            eventId = Model.getInstance().getDatabaseDriver().getEventId(event_choiceBox.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Model.getInstance().getDatabaseDriver().addMedal(medalType, athleteId, eventId, sportId);
    }

    private void initAthleteChoiceBox() {
        Model.getInstance().setAthletesList();
        List<String> athletesName = new ArrayList<>();
        for (Athlete athlete : Model.getInstance().getAthletesList()) {
            athletesName.add(athlete.athleteNameProperty().getValue());
        }
        athlete_choiceBox.getItems().addAll(athletesName);
    }

    private void initEventChoiceBox() {
        Model.getInstance().setEventsList();
        List<String> eventsDate = new ArrayList<>();
        for (Event event : Model.getInstance().getEventsList()) {
            eventsDate.add(Model.getInstance().getDatabaseDriver().getSportName(event.getSportId().getValue()) + " " + event.getDate().getValue());
        }
        event_choiceBox.getItems().addAll(eventsDate);
    }

    private void onSubmit() {
        if (checkFields()) {
            addMedal();
            Stage stage = (Stage) add_button.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_txt.setVisible(true);
        }
    }

    private String getMedalType() {
        String choice = null;
        if (bronzeCheckBox.isSelected()) {
            choice = "bronze";
        } else if (silverCheckBox.isSelected()) {
            choice = "silver";
        } else if (goldCheckBox.isSelected()) {
            choice = "gold";
        }
        return choice;
    }
}
