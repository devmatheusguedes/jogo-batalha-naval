package com.devmatheusguedes.game.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipalController {
    public Button btnCarregarJogo;
    public Button btnSair;
    public AnchorPane panePrincipal;
    @FXML
    Button btnJogar;





    public void jogar(javafx.event.ActionEvent actionEvent) {
        System.out.println("clicado");
    }

    public void sair(ActionEvent actionEvent) {
        System.out.println("saindo");
    }

    public void carregar(ActionEvent actionEvent) {
        System.out.println("carregando");
    }
}
