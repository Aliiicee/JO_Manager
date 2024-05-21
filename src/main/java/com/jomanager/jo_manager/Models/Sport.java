package com.jomanager.jo_manager.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sport {
    private final StringProperty name;

    public Sport(String name) {
        this.name = new SimpleStringProperty(this, "name", name);
    }

    public StringProperty nameProperty() {
       return this.name;
    }
}
