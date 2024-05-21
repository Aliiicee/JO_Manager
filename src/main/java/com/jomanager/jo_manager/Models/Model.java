package com.jomanager.jo_manager.Models;

import com.jomanager.jo_manager.Views.ViewFactory;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private ObservableList<Medal> topMedalsByCountries;
    private ObservableList<Medal> topMedalsByAthletes;
    private ObservableList<Medal> allMedalsByCountries;
    private ObservableList<Medal> allMedalsByAthletes;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.topMedalsByCountries = FXCollections.observableArrayList();
        this.topMedalsByAthletes = FXCollections.observableArrayList();
        this.allMedalsByCountries = FXCollections.observableArrayList();
        this.allMedalsByAthletes = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void setTopMedalsByCountries() {
        ResultSet resultSet = databaseDriver.getMedalsData("all", 1, 0, 3);
        try {
            while (resultSet.next()) {
                int countryId = resultSet.getInt("country_id");
                ResultSet bronzeResultSet = databaseDriver.getMedalsData("bronze", countryId, 0, 0);
                ResultSet silverResultSet = databaseDriver.getMedalsData("silver", countryId, 0, 0);
                ResultSet goldResultSet = databaseDriver.getMedalsData("gold", countryId, 0, 0);
                String flagCountry = databaseDriver.getCountryName(countryId);
                int goldenMedalsAmount = goldResultSet.getInt("cnt");
                int silverMedalsAmount = silverResultSet.getInt("cnt");
                int bronzeMedalsAmount = bronzeResultSet.getInt("cnt");
                int medalsAmount = resultSet.getInt("cnt");
                topMedalsByCountries.add(new Medal(flagCountry, "", goldenMedalsAmount, silverMedalsAmount, bronzeMedalsAmount, medalsAmount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Medal> getTopMedalsByCountries() {
        return topMedalsByCountries;
    }

    public void setTopMedalsByAthletes() {
        ResultSet resultSet = databaseDriver.getMedalsData("all", 0, 1, 3);
        try {
            while (resultSet.next()) {
                int athleteId = resultSet.getInt("athlete_id");
                ResultSet bronzeResultSet = databaseDriver.getMedalsData("bronze", 0, athleteId, 0);
                ResultSet silverResultSet = databaseDriver.getMedalsData("silver", 0, athleteId, 0);
                ResultSet goldResultSet = databaseDriver.getMedalsData("gold", 0, athleteId, 0);
                ResultSet atheleteResultSet = databaseDriver.getAthleteById(athleteId);
                String flagCountry = databaseDriver.getCountryName(atheleteResultSet.getInt("country_id"));
                String athleteName = atheleteResultSet.getString("first_name") + " " + atheleteResultSet.getString("last_name");
                int goldenMedalsAmount = goldResultSet.getInt("cnt");
                int silverMedalsAmount = silverResultSet.getInt("cnt");
                int bronzeMedalsAmount = bronzeResultSet.getInt("cnt");
                int medalsAmount = resultSet.getInt("cnt");
                topMedalsByAthletes.add(new Medal(flagCountry, athleteName, goldenMedalsAmount, silverMedalsAmount, bronzeMedalsAmount, medalsAmount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Medal> getTopMedalsByAthletes() {
        return topMedalsByAthletes;
    }
}
