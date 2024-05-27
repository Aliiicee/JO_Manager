package com.jomanager.jo_manager.Views;

import com.jomanager.jo_manager.Controllers.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private final StringProperty selectedMenuItem;
    private StringProperty dashboardMedalsListViewState;
    private StringProperty resultsMedalsListViewState;
    private AnchorPane dashboardView;
    private AnchorPane athletesView;
    private AnchorPane sportsView;
    private AnchorPane eventsView;
    private AnchorPane resultsView;

    public ViewFactory() {
        this.selectedMenuItem = new SimpleStringProperty();
        this.dashboardMedalsListViewState = new SimpleStringProperty();
        dashboardMedalsListViewState.set("byCountries");
        this.resultsMedalsListViewState = new SimpleStringProperty();
        resultsMedalsListViewState.set("byCountries");
    }

    public StringProperty getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public StringProperty getDashboardMedalsListViewState() {
        return dashboardMedalsListViewState;
    }

    public StringProperty getResultsMedalsListViewState() {
        return resultsMedalsListViewState;
    }

    public AnchorPane getDashboardView() {
        try {
            dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
        } catch (Exception e){
            e.printStackTrace();
        }

        return dashboardView;
    }

    public AnchorPane getAthletesView() {
        try {
            athletesView = new FXMLLoader(getClass().getResource("/Fxml/Athletes.fxml")).load();
        } catch (Exception e){
            e.printStackTrace();
        }


        return athletesView;
    }

    public AnchorPane getSportsView() {
        try {
            sportsView = new FXMLLoader(getClass().getResource("/Fxml/Sports.fxml")).load();
        } catch (Exception e){
            e.printStackTrace();
        }


        return sportsView;
    }

    public AnchorPane getEventsView() {
        try {
           eventsView = new FXMLLoader(getClass().getResource("/Fxml/Events.fxml")).load();
        } catch (Exception e){
            e.printStackTrace();
        }


        return eventsView;
    }

    public AnchorPane getResultsView() {
        try {
            resultsView = new FXMLLoader(getClass().getResource("/Fxml/Results.fxml")).load();
        } catch (Exception e){
            e.printStackTrace();
        }


        return resultsView;
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    public void showAddAthleteWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AddAthlete.fxml"));
        createStage(loader);
    }

    public void showAddSportWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AddSport.fxml"));
        createStage(loader);
    }

    public void showAddMedalWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AddMedal.fxml"));
        createStage(loader);
    }

    public void showAddEventWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AddEvent.fxml"));
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("JO Manager");
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
