package com.jomanager.jo_manager.Controllers;

import com.calendarfx.view.page.DayPage;
import com.jomanager.jo_manager.Models.Medal;
import com.jomanager.jo_manager.Models.Model;
import com.jomanager.jo_manager.Views.MedalsCellFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController implements Initializable {
    public Button country_btn;
    public Button athlete_btn;
    public ListView<Medal> medals_listview;
    public DayPage daypage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().setTopMedalsByCountries();
        medals_listview.setItems(Model.getInstance().getTopMedalsByCountries());
        medals_listview.setCellFactory(e -> new MedalsCellFactory());
    }
}
