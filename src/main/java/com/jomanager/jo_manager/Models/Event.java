package com.jomanager.jo_manager.Models;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private final IntegerProperty id;
    private final StringProperty date;
    private final IntegerProperty sportId;

    public Event(Integer id, String date, Integer sportId) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.date = new SimpleStringProperty(this, "date", date);
        this.sportId = new SimpleIntegerProperty(this, "sportId", sportId);
    }

    public IntegerProperty getId() {return id;}

    public StringProperty getDate() {return date;}

    public IntegerProperty getSportId() {return sportId;}
}
