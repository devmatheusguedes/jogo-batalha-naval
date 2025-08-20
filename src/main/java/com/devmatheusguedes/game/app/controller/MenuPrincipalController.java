package com.devmatheusguedes.game.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
        // 1) Cria o FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // 2) Define explicitamente a localização do FXML
        loader.setLocation(
                getClass().getResource(
                        "/com/devmatheusguedes/game/app/view/PosicionarBarcos.fxml"
                )
        );
        // 3) Executa o load() e recebe o Parent
        Parent root = loader.load();

        // 4) Troca a cena atual pela nova
        Stage stage = (Stage) ((Node) actionEvent.getSource())
                .getScene()
                .getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public void sair(ActionEvent actionEvent) {
        System.out.println("saindo");
    }

    public void carregar(ActionEvent actionEvent) {
        System.out.println("carregando");
    }
}
