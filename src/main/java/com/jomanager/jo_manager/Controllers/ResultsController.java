package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Medal;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Views.MedalsCellFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ResultsController implements Initializable {
    public ListView<Medal> results_listview;
    public Button add_medal_button;
    public Button country_btn;
    public Button athlete_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_medal_button.setOnAction(event -> onAdd());
        country_btn.setOnAction(event -> onCountries());
        athlete_btn.setOnAction(event -> onAthletes());
        Model.getInstance().setAllMedalsByCountries();
        results_listview.setItems(Model.getInstance().getAllMedalsByCountries());
        Model.getInstance().getViewFactory().getResultsMedalsListViewState().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals("byCountries")) {
                Model.getInstance().setAllMedalsByCountries();
                results_listview.setItems(Model.getInstance().getAllMedalsByCountries());
            } else {
                Model.getInstance().setAllMedalsByAthletes();
                results_listview.setItems(Model.getInstance().getAllMedalsByAthletes());
            }
        });
        results_listview.setCellFactory(e -> new MedalsCellFactory());
    }


    public void onCountries() {
        Model.getInstance().getViewFactory().getResultsMedalsListViewState().set("byCountries");
    }

    public void onAthletes() {
        Model.getInstance().getViewFactory().getResultsMedalsListViewState().set("byAthletes");
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().showAddMedalWindow();
    }
}
