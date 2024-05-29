package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Athlete;
import com.jomanager.jo_manager.Models.Country;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Views.AthleteCellFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class AthletesController implements Initializable {
    public ListView<Athlete> athletes_listview;
    public ChoiceBox<String> country_choicebox;
    public Button add_athlete_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_athlete_button.setOnAction(event -> onAdd());
        initCountriesChoiceBox();
        initAthletesByCountryListView();
        athletes_listview.setItems(Model.getInstance().getAthletesByCountry());
        athletes_listview.setCellFactory(e -> new AthleteCellFactory());
    }

    private void initCountriesChoiceBox() {
        Model.getInstance().setCountriesList();
        List<String> countriesName = new ArrayList<>();
        for (Country country : Model.getInstance().getCountriesList()) {
            countriesName.add(country.getName().getValue());
        }
        country_choicebox.getItems().addAll(countriesName);
        country_choicebox.setValue("France");
        country_choicebox.setOnAction(this::refreshList);
    }

    private void initAthletesByCountryListView() {
        try {
            Model.getInstance().setAthletesByCountry(Model.getInstance().getDatabaseDriver().getCountryId(country_choicebox.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshList(ActionEvent event) {
        initAthletesByCountryListView();
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().showAddAthleteWindow();
    }
}
