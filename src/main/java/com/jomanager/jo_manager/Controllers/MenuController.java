package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button dashboard_btn;
    public Button athletes_btn;
    public Button sports_btn;
    public Button events_btn;
    public Button results_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDasboard());
        athletes_btn.setOnAction(event -> onAthletes());
        sports_btn.setOnAction(event -> onSports());
        events_btn.setOnAction(event -> onEvents());
    }

    private void onEvents() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("Events");
    }

    private void onSports() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("Sports");
    }

    private void onAthletes() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("Athletes");
    }

    private void onDasboard() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("Dashboard");
    }
}
