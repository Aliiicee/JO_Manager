module com.jomanager.jo_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires de.jensd.fx.glyphs.fontawesome;

    opens com.jomanager.jo_manager to javafx.fxml;
    exports com.jomanager.jo_manager;
    exports com.jomanager.jo_manager.Controllers;
    exports com.jomanager.jo_manager.Models;
    exports com.jomanager.jo_manager.Views;
}