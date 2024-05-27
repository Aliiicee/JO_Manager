package com.jomanager.jo_manager.Controllers;

import com.calendarfx.view.CalendarView;
import com.jomanager.jo_manager.Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class EventsController implements Initializable {
    public CalendarView calendarView;
    public Button add_event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_event.setOnAction(event -> onAdd());
        initCalendarView();
    }

    private void initCalendarView() {
        Model.getInstance().setCalendar();
        calendarView.getCalendarSources().addAll(Model.getInstance().getCalendarSource());
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().showAddEventWindow();
    }
}
