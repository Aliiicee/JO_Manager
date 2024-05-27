package com.jomanager.jo_manager.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
    private final IntegerProperty id;
    private final StringProperty name;

    public Country(Integer id, String name) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.id = new SimpleIntegerProperty(this, "id", id);
    }

    public StringProperty getName() {
        return name;
    }

    public IntegerProperty getId() {return id;}
}
