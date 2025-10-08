package com.devmatheusguedes.game.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {
    public Button btnCarregarJogo;
    public Button btnSair;
    public AnchorPane panePrincipal;
    @FXML
    Button btnJogar;





    @FXML
    public void jogar(ActionEvent actionEvent) throws IOException {
        String fxml = "/com/devmatheusguedes/game/app/view/PosicionarBarcos.fxml";
        ScrenManager.loadScreen(fxml, 800, 500);

        PosicionarBarcosController posicionarBarcosController = ScrenManager.getFxmlLoader().getController();
        posicionarBarcosController.setstage(ScrenManager.getMainStage());
        posicionarBarcosController.carergarTela(ScrenManager.getRoot());
    }


    public void sair(ActionEvent actionEvent) {
        System.out.println("saindo");
    }

    public void carregar(ActionEvent actionEvent) {
        System.out.println("carregando");
    }
}
