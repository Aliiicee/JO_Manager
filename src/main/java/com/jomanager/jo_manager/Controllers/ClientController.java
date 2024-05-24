package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Athletes" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAthletesView());
                case "Sports" -> client_parent.setCenter(Model.getInstance().getViewFactory().getSportsView());
                case "Events" -> client_parent.setCenter(Model.getInstance().getViewFactory().getEventsView());
                case "Results" -> client_parent.setCenter(Model.getInstance().getViewFactory().getResultsView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
