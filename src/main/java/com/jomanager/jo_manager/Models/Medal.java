package com.jomanager.jo_manager.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medal {
    private final StringProperty flagCountry;
    private final StringProperty athleteName;
    private final IntegerProperty goldenMedals_amount;
    private final IntegerProperty silverMedals_amount;
    private final IntegerProperty bronzeMedals_amount;
    private final IntegerProperty medals_amount;

    public Medal(String flagCountry, String athleteName, Integer goldenMedals_amount, Integer silverMedals_amount, Integer bronzeMedals_amount, Integer medals_amount) {
        this.flagCountry = new SimpleStringProperty(this, "flagCountry", flagCountry);
        this.athleteName = new SimpleStringProperty(this, "athleteName", athleteName);
        this.goldenMedals_amount = new SimpleIntegerProperty(this, "goldenMedals_amount", goldenMedals_amount);
        this.silverMedals_amount = new SimpleIntegerProperty(this, "silverMedals_amount", silverMedals_amount);
        this.bronzeMedals_amount = new SimpleIntegerProperty(this, "bronzeMedals_amount", bronzeMedals_amount);
        this.medals_amount = new SimpleIntegerProperty(this, "medals_amount", medals_amount);
    }

    public StringProperty getFlagCountry() {
        return this.flagCountry;
    }

    public StringProperty getAthleteName() {
        return this.athleteName;
    }

    public IntegerProperty getGoldenMedals_amount() {
        return this.goldenMedals_amount;
    }

    public IntegerProperty getSilverMedals_amount() {
        return this.silverMedals_amount;
    }

    public IntegerProperty getBronzeMedals_amount() {
        return this.bronzeMedals_amount;
    }

    public IntegerProperty getMedals_amount() {
        return this.medals_amount;
    }
}
