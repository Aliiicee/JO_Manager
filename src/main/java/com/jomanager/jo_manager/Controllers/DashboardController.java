package com.jomanager.jo_manager.Controllers;

import com.calendarfx.view.page.DayPage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController implements Initializable {
    public Button country_btn;
    public Button athlete_btn;
    public Label date;
    public ListView medals_listview;
    public DayPage daypage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
