package com.jomanager.jo_manager.Controllers;

import com.calendarfx.view.page.DayPage;
import com.jomanager.jo_manager.Models.Medal;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Views.MedalsCellFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController implements Initializable {
    public Button country_btn;
    public Button athlete_btn;
    public ListView<Medal> medals_listview;
    public DayPage daypage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        daypage.getCalendarSources().addAll(Model.getInstance().getCalendarSource());
        country_btn.setOnAction(event -> onCountries());
        athlete_btn.setOnAction(event -> onAthletes());
        initMedalsByCountriesListView();
        medals_listview.setItems(Model.getInstance().getTopMedalsByCountries());
        Model.getInstance().getViewFactory().getDashboardMedalsListViewState().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals("byCountries")) {
                initMedalsByCountriesListView();
                medals_listview.setItems(Model.getInstance().getTopMedalsByCountries());
            } else {
                initMedalsByAthletesListView();
                medals_listview.setItems(Model.getInstance().getTopMedalsByAthletes());
            }
        });
        medals_listview.setCellFactory(e -> new MedalsCellFactory());
    }

    private void initMedalsByCountriesListView() {
        if (Model.getInstance().getTopMedalsByCountries().isEmpty()) {
            Model.getInstance().setTopMedalsByCountries();
        }
    }

    private void initMedalsByAthletesListView() {
        if (Model.getInstance().getTopMedalsByAthletes().isEmpty()) {
            Model.getInstance().setTopMedalsByAthletes();
        }
    }

    public void onCountries() {
        Model.getInstance().getViewFactory().getDashboardMedalsListViewState().set("byCountries");
    }

    public void onAthletes() {
        Model.getInstance().getViewFactory().getDashboardMedalsListViewState().set("byAthletes");
    }
}
