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

public class PosicionarBarcosController {

    public BorderPane root;
    public RadioButton normal;
    public RadioButton medio;
    public RadioButton grande;
    public ToggleGroup grupo;
    private static Player player;

    @FXML
    private GridPane tabuleiro;

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
        montarTabuleiro(tamanhoTabuleiro);
        configurarBotoes();
        addImageBarcosLeftPane();

    }
    public void iniciar(){

    }

    private void montarTabuleiro(int tamanho) {
        tabuleiro = new GridPane();
        int[][] tamTab = player.getMeuTabulerio().getPosicao();
        int x = tamTab.length;
        int y = tamTab.length;
        // passo 1: fazer o tabuleiro de acordo com o tamanho especificado
        tabuleiro.getRowConstraints().clear();
        tabuleiro.getColumnConstraints().clear();
        for (int i = 0; i <= x; i++){
            for (int j = 0; j <= y; j++){

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


    private void configurarBotoes() {
        grupo.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                        if (grupo.getSelectedToggle() != null){
                            String selected = ((RadioButton) grupo.getSelectedToggle()).getText();
                            tamanhoTabuleiro = Integer.parseInt(selected);
                            montarTabuleiro(tamanhoTabuleiro);
                            if (tamanhoTabuleiro == 30){
                                ScrenManager.getMainStage().setMaximized(true);
                            }
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

