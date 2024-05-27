package com.jomanager.jo_manager.Models;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.jomanager.jo_manager.Views.ViewFactory;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
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
    private ObservableList<Athlete> athletesByCountry;
    private ObservableList<Athlete> athletesBySport;
    private ObservableList<Country> countriesList;
    private ObservableList<Sport> sportsList;
    private ObservableList<Athlete> athletesList;
    private ObservableList<Event> eventsList;
    private Calendar calendar = new Calendar("Evénements des J.O");
    CalendarSource calendarSource = new CalendarSource("Mes Calendriers");

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.topMedalsByCountries = FXCollections.observableArrayList();
        this.topMedalsByAthletes = FXCollections.observableArrayList();
        this.allMedalsByCountries = FXCollections.observableArrayList();
        this.allMedalsByAthletes = FXCollections.observableArrayList();
        this.athletesByCountry = FXCollections.observableArrayList();
        this.athletesBySport = FXCollections.observableArrayList();
        this.countriesList = FXCollections.observableArrayList();
        this.sportsList = FXCollections.observableArrayList();
        this.athletesList = FXCollections.observableArrayList();
        this.eventsList = FXCollections.observableArrayList();
        setCalendar();
        calendarSource.getCalendars().addAll(calendar);
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

    public void setCalendar() {
        setEventsList();
        calendar.clear();
        for (Event event : getEventsList()) {
            Entry<String> eventEntry = new Entry<>(getDatabaseDriver().getSportName(event.getSportId().getValue()) + " " + event.getDate().getValue());
            String[] eventDateSplit = event.getDate().getValue().split(" ");
            eventEntry.changeStartDate(LocalDate.parse(eventDateSplit[0]));
            eventEntry.changeStartTime(LocalTime.parse(eventDateSplit[1]));
            eventEntry.changeEndTime(LocalTime.parse(eventDateSplit[1]).plusHours(1));
            calendar.addEntry(eventEntry);
        }
    }

    public CalendarSource getCalendarSource() {
        return calendarSource;
    }

    public void setTopMedalsByCountries() {
        if (topMedalsByCountries != null) {
            topMedalsByCountries.clear();
        }
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
        if (topMedalsByAthletes != null) {
            topMedalsByAthletes.clear();
        }
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

    public void setAllMedalsByCountries() {
        if (allMedalsByCountries != null) {
            allMedalsByCountries.clear();
        }
        ResultSet resultSet = databaseDriver.getMedalsData("all", 1, 0, -1);
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
                allMedalsByCountries.add(new Medal(flagCountry, "", goldenMedalsAmount, silverMedalsAmount, bronzeMedalsAmount, medalsAmount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Medal> getAllMedalsByCountries() {
        return allMedalsByCountries;
    }

    public void setAllMedalsByAthletes() {
        if (allMedalsByAthletes != null) {
            allMedalsByAthletes.clear();
        }
        ResultSet resultSet = databaseDriver.getMedalsData("all", 0, 1, -1);
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
                allMedalsByAthletes.add(new Medal(flagCountry, athleteName, goldenMedalsAmount, silverMedalsAmount, bronzeMedalsAmount, medalsAmount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Medal> getAllMedalsByAthletes() {
        return allMedalsByAthletes;
    }

    public void setAthletesByCountry(int countryId) {
        if (athletesByCountry != null) {
            athletesByCountry.clear();
        }
        ResultSet resultSet = databaseDriver.getAthletesData("byCountry", countryId, 0);
        try {
            while (resultSet.next()) {
                String flagCountry = databaseDriver.getCountryName(resultSet.getInt("country_id"));
                String athleteName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                LocalDate birthDate = LocalDate.parse(resultSet.getString("birthdate"));
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                String gender = resultSet.getString("gender");
                int height = resultSet.getInt("height");
                double weight = resultSet.getDouble("weight");
                athletesByCountry.add(new Athlete(flagCountry, athleteName, age, gender, height, weight));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Athlete> getAthletesByCountry() {
        return athletesByCountry;
    }

    public void setAthletesBySport(int sportId) {
        if (athletesBySport != null) {
            athletesBySport.clear();
        }
        ResultSet resultSet = databaseDriver.getAthletesData("bySport", 0, sportId);
        try {
            while (resultSet.next()) {
                String flagCountry = databaseDriver.getCountryName(resultSet.getInt("country_id"));
                String athleteName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                LocalDate birthDate = LocalDate.parse(resultSet.getString("birthdate"));
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                String gender = resultSet.getString("gender");
                int height = resultSet.getInt("height");
                double weight = resultSet.getDouble("weight");
                athletesBySport.add(new Athlete(flagCountry, athleteName, age, gender, height, weight));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Athlete> getAthletesBySport() {
        return athletesBySport;
    }

    public void setCountriesList() {
        if (countriesList != null) {
            countriesList.clear();
        }
        ResultSet resultSet = databaseDriver.getCountries();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("country_id");
                String countryName = resultSet.getString("name");
                countriesList.add(new Country(id, countryName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Country> getCountriesList() {
        return countriesList;
    }

    public void setSportsList() {
        if (sportsList != null) {
            sportsList.clear();
        }
        ResultSet resultSet = databaseDriver.getSports();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("sport_id");
                String sportName = resultSet.getString("name");
                sportsList.add(new Sport(id, sportName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Sport> getSportsList() {
        return sportsList;
    }

    public void setAthletesList() {
        if (athletesList != null) {
            athletesList.clear();
        }
        ResultSet resultSet = databaseDriver.getAthletes();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("athlete_id");
                String flagCountry = databaseDriver.getCountryName(resultSet.getInt("country_id"));
                String athleteName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                LocalDate birthDate = LocalDate.parse(resultSet.getString("birthdate"));
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                String gender = resultSet.getString("gender");
                int height = resultSet.getInt("height");
                double weight = resultSet.getDouble("weight");
                athletesList.add(new Athlete(flagCountry, athleteName, age, gender, height, weight));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Athlete> getAthletesList() {
        return athletesList;
    }

    public void setEventsList() {
        if (eventsList != null) {
            eventsList.clear();
        }
        ResultSet resultSet = databaseDriver.getEvents();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("event_id");
                String date = resultSet.getString("date");
                Integer sportId = resultSet.getInt("sport_id");
                eventsList.add(new Event(id, date, sportId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Event> getEventsList() {
        return eventsList;
    }
}
