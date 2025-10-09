package com.devmatheusguedes.game.app.controller;

import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.Barco;
import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PosicionarBarcosController {
    @FXML
    public BorderPane root;
    @FXML
    public RadioButton normal;
    @FXML
    public RadioButton medio;
    @FXML
    public RadioButton grande;
    @FXML
    public ToggleGroup grupo;
    private static Player player;
    @FXML
    public GridPane tabuleiro;


    @FXML
    private VBox boxBarcos;

    @FXML
    private Button btnCancelar, btnComecar, btnPosicionarAleatorio;

    @FXML
    private static  int tamanhoTabuleiro = 10; // Pode ser parametrizado
    //private boolean orientacaoHorizontal = true;


    @FXML
    public void initialize() {
        player = new Player("matheus");
        configurarBotoes();
        montarTabuleiro(tamanhoTabuleiro);
        addImageBarcosLeftPane();
        estiloDaCena();

    }
    public void iniciar(){

    }

    private void montarTabuleiro(int tamanho) {
        tabuleiro.getChildren().clear();
        tabuleiro.getColumnConstraints().clear();
        tabuleiro.getRowConstraints().clear();
        for (int i = 0; i < tamanho; i++){
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPrefWidth(30);
            tabuleiro.getColumnConstraints().add(constraints);
        }

        for (int j = 0; j<tamanho; j++){
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(30);
            tabuleiro.getRowConstraints().add(rowConstraints);
        }
        // passo 1: fazer o tabuleiro de acordo com o tamanho especificado
        tabuleiro.getRowConstraints().clear();
        tabuleiro.getColumnConstraints().clear();
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                StackPane quadrado = new StackPane();
                if (tamanho <= 10){
                    quadrado.setPrefSize(30, 30);
                }else {
                    quadrado.setPrefSize(30, 30);
                }
                GridPane.setRowIndex(quadrado, i);
                GridPane.setColumnIndex(quadrado, j);
                quadrado.setStyle(" -fx-background-color: #adb5bd;");
                carregarEstiloDeElemento(quadrado);
                tabuleiro.getChildren().add(quadrado);
            }
        }
        // passo2: adcionar os barcos a barra lateral esquerda
        // passo 3: mover os barcos para o tabuleiro
        // passo 4: pegar a posição dos barcos e verificar se esta tudo ok
        // passo 5: se estiver tudo ok, colocar a imagem do barco na posição indicada.
    }

    // metodo para adicionar os barcos na barra lateral
    public void addImageBarcosLeftPane(){
        List<Barco> barcos = player.getMeuTabulerio().getBarcos();
        for (Barco barco: barcos
             ) {
            try {
                FileInputStream fis = new FileInputStream(barco.getTipoBarco().getPathImage());
                Image image = new Image(fis);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(70);
                imageView.setFitWidth(100);
                boxBarcos.getChildren().add(imageView);
            }catch (IOException e){
                System.out.println("falha ao carregar imagem de barco: " + e.getMessage());
            }
        }
    }

    public void estiloDaCena(){
        String css = Objects.requireNonNull(getClass().getResource("/com/devmatheusguedes/game/app/css/batalha_naval.css")).toExternalForm();
        tabuleiro.getStylesheets().add(css);
        tabuleiro.getStyleClass().add("tabuleiro");
    }

    private void carregarEstiloDeElemento(StackPane node){
        String css =(Objects.requireNonNull(getClass().getResource("/com/devmatheusguedes/game/app/css/batalha_naval.css")).toExternalForm());
        node.getStylesheets().add(css);
        node.getStyleClass().add("quadrado");
    }


    private void configurarBotoes() {
        grupo.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                        if (grupo.getSelectedToggle() != null){
                            String selected = ((RadioButton) grupo.getSelectedToggle()).getText();
                            tamanhoTabuleiro = Integer.parseInt(selected);
                            if (tamanhoTabuleiro == 30){
                                ScrenManager.getMainStage().setMaximized(true);
                            }
                            montarTabuleiro(tamanhoTabuleiro);
                        }
                    }
                }
        );
        btnCancelar.setOnAction(e -> {
            root.setCenter(new Label("OK"));
            // Fechar a janela ou limpar estado
        });

        btnPosicionarAleatorio.setOnAction(e -> {
            System.out.println("Posicionamento aleatório.");
            // Lógica de colocar barcos aleatoriamente
        });

        btnComecar.setOnAction(e -> {
            System.out.println("Começando o jogo!");
            // Validar se todos os barcos foram posicionados
        });
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PosicionarBarcosController.player = player;
    }
}

