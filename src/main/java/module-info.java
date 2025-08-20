module com.devmatheusguedes.game.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;

    opens com.devmatheusguedes.game.app to javafx.fxml;
    exports com.devmatheusguedes.game.app;
    exports com.devmatheusguedes.game.app.controller;
    opens com.devmatheusguedes.game.app.controller to javafx.fxml;
}