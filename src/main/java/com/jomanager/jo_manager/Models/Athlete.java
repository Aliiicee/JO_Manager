package com.jomanager.jo_manager.Models;

import javafx.beans.property.*;

public class Athlete {
    private final StringProperty flagCountry;
    private final StringProperty athleteName;
    private final IntegerProperty athleteAge;
    private final StringProperty athleteGender;
    private final IntegerProperty athleteHeight;
    private final DoubleProperty athleteWeight;

    public Athlete(String flagCountry, String athleteName, Integer athleteAge, String athleteGender, Integer athleteHeight, Double athleteWeight) {
        this.flagCountry = new SimpleStringProperty(this, "flagCountry", flagCountry);
        this.athleteName = new SimpleStringProperty(this, "athleteName", athleteName);
        this.athleteAge = new SimpleIntegerProperty(this, "athleteAge", athleteAge);
        this.athleteGender = new SimpleStringProperty(this, "athleteGender", athleteGender);
        this.athleteHeight = new SimpleIntegerProperty(this, "athleteHeight", athleteHeight);
        this.athleteWeight = new SimpleDoubleProperty(this, "athleteWeight", athleteWeight);
    }

    public StringProperty flagCountryProperty() {
        return this.flagCountry;
    }

    public StringProperty athleteNameProperty() {
        return this.athleteName;
    }

    public IntegerProperty athleteAgeProperty() {
        return this.athleteAge;
    }

    public StringProperty athleteGenderProperty() {
        return this.athleteGender;
    }

    public IntegerProperty athleteHeightProperty() {
        return this.athleteHeight;
    }

    public DoubleProperty athleteWeightProperty() {
        return this.athleteWeight;
    }
}
