package com.jomanager.jo_manager.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Athlete {
    private final StringProperty flagCountry;
    private final StringProperty athleteName;
    private final StringProperty athleteAge;
    private final StringProperty athleteGender;
    private final StringProperty athleteHeight;
    private final StringProperty athleteWeight;

    public Athlete(String flagCountry, String athleteName, String athleteAge, String athleteGender, String athleteHeight, String athleteWeight) {
        this.flagCountry = new SimpleStringProperty(this, "flagCountry", flagCountry);
        this.athleteName = new SimpleStringProperty(this, "athleteName", athleteName);
        this.athleteAge = new SimpleStringProperty(this, "athleteAge", athleteAge);
        this.athleteGender = new SimpleStringProperty(this, "athleteGender", athleteGender);
        this.athleteHeight = new SimpleStringProperty(this, "athleteHeight", athleteHeight);
        this.athleteWeight = new SimpleStringProperty(this, "athleteWeight", athleteWeight);
    }

    public StringProperty flagCountryProperty() {
        return this.flagCountry;
    }

    public StringProperty athleteNameProperty() {
        return this.athleteName;
    }

    public StringProperty athleteAgeProperty() {
        return this.athleteAge;
    }

    public StringProperty athleteGenderProperty() {
        return this.athleteGender;
    }

    public StringProperty athleteHeightProperty() {
        return this.athleteHeight;
    }

    public StringProperty athleteWeightProperty() {
        return this.athleteWeight;
    }
}
