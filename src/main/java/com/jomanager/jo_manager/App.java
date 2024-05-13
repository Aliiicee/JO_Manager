package com.jomanager.jo_manager;

import com.jomanager.jo_manager.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showClientWindow();
    }
}
