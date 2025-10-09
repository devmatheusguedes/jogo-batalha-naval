package com.devmatheusguedes.game.app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/***
 * Classe para gerenciamento de telas
 */
public class ScrenManager {
    private static Stage mainStage;
    private static FXMLLoader fxmlLoader;
    private static Parent root;



    public static void loadScreen(String fxml, int width, int height){
        try {
            fxmlLoader = new FXMLLoader(ScrenManager.class.getResource(fxml));
            root = fxmlLoader.load();

            mainStage.setScene(new Scene(root, width, height));
            mainStage.show();
            mainStage.centerOnScreen();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        ScrenManager.root = root;
    }


    public static Stage getMainStage() {
        return mainStage;
    }
    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public static void setFxmlLoader(FXMLLoader fxmlLoader) {
        ScrenManager.fxmlLoader = fxmlLoader;
    }


    public  static void setMainStage(Stage stage){
        mainStage = stage;
    }
}
