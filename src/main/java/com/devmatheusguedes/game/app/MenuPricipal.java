package com.devmatheusguedes.game.app;

import java.io.IOException;
import java.util.Objects;

import com.devmatheusguedes.game.app.controller.ScrenManager;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuPricipal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String url = "/com/devmatheusguedes/game/app/view/MenuPrincipal.fxml";
        ScrenManager.setMainStage(stage);
        ScrenManager.loadScreen(url, 800, 500);
    }

    public static void main(String[] args) {
        launch();
    }
}
