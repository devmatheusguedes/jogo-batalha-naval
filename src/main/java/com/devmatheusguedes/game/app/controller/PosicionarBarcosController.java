package com.devmatheusguedes.game.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class PosicionarBarcosController {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button btnBatalhar;
    @FXML
    public Button btnAleatorio;
    @FXML
    public Button btnVoltar;
    @FXML
    private GridPane gridPosicao;

    public PosicionarBarcosController(){
    }

    @FXML
    public void initialize() {

        for(int i = 0; i < 10; i++){
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(10);
            gridPosicao.getColumnConstraints().add(col);
        }
        for(int i = 0; i < 10; i++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(10);
            gridPosicao.getRowConstraints().add(row);
        }


        int size = 10; // dimensões 10x10
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle cell = new Rectangle(30, 30);
                cell.getStyleClass().add("grid-cell");
                int r = row, c = col;
                cell.setOnMouseClicked(e -> onCellClick(r, c));
                gridPosicao.add(cell, col, row);
            }
        }
    }

    private void onCellClick(int row, int col) {
        // lógica para “selecionar” posição de barco
        System.out.printf("Clicou em %d, %d%n", row, col);
    }


    public void batalhar(ActionEvent actionEvent) {

    }

    public void aleatorio(ActionEvent actionEvent){
         System.out.println("posicionando barcos aleatoriamente");
    }

    public void voltar(ActionEvent actionEvent){
        System.out.println("voltando ao menu principal");
    }

}
