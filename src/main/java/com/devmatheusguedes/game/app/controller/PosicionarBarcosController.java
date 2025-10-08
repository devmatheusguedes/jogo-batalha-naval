package com.devmatheusguedes.game.app.controller;

import com.devmatheusguedes.game.entidade.Player;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;

public class PosicionarBarcosController {

    public BorderPane root;
    public RadioButton normal;
    public RadioButton medio;
    public RadioButton grande;
    public ToggleGroup grupo;
    private Player player;
    private Stage stage;

    @FXML
    private GridPane gridTabuleiro;

    @FXML
    private VBox boxBarcos;

    @FXML
    private Button btnCancelar, btnComecar, btnPosicionarAleatorio;

    @FXML
    private static  int tamanhoTabuleiro = 10; // Pode ser parametrizado
    private boolean orientacaoHorizontal = true;

    public void setstage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        montarTabuleiro(tamanhoTabuleiro);
        adicionarBarcos();
        configurarBotoes();

    }

    private void montarTabuleiro(int tamanho) {
        gridTabuleiro.getChildren().clear();
        gridTabuleiro.getColumnConstraints().clear();
        gridTabuleiro.getRowConstraints().clear();

        for (int i = 0; i < tamanho; i++) {
            if (tamanho <= 10) {
                gridTabuleiro.getColumnConstraints().add(new ColumnConstraints(30));
                gridTabuleiro.getRowConstraints().add(new RowConstraints(30));
            }else {
                gridTabuleiro.getColumnConstraints().add(new ColumnConstraints(20));
                gridTabuleiro.getRowConstraints().add(new RowConstraints(20));
            }
        }

        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                StackPane celula = new StackPane();
                if (tamanho <= 10) {
                    celula.setPrefSize(30, 30);
                }else {
                    celula.setPrefSize(20, 20);
                }
                celula.setStyle("-fx-border-color: black; -fx-background-color: lightblue;");

                final int finalX = x;
                final int finalY = y;

                // Permitir drag over
                celula.setOnDragOver(event -> {
                    if (event.getGestureSource() != celula && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                });

                // Soltar barco
                celula.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    boolean sucesso = false;

                    if (db.hasString()) {
                        String barcoId = db.getString();
                        System.out.println("Barco '" + barcoId + "' posicionado em: " + finalX + ", " + finalY);
                        posicionarBarco(barcoId, finalX, finalY);
                        sucesso = true;
                    }
                    event.setDropCompleted(sucesso);
                    event.consume();
                });

                gridTabuleiro.add(celula, x, y);
            }
        }
    }

    private void adicionarBarcos() {
        int tamanho = 5;


            Image img = new Image(getClass().getResource("/com/devmatheusguedes/game/app/images/barco5.png").toExternalForm());
            ImageView barco = new ImageView(img);

            barco.setFitWidth(30 * tamanho);
            barco.setFitHeight(30);
            barco.setPreserveRatio(false);
            barco.setUserData("barco_" + tamanho);

            // arrastar
            barco.setOnDragDetected(event -> {
                Dragboard db = barco.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString((String) barco.getUserData());
                db.setContent(content);
                event.consume();
            });

            // rotacionar com duplo clique
            barco.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    orientacaoHorizontal = !orientacaoHorizontal;
                    System.out.println("Orientação trocada: " + (orientacaoHorizontal ? "Horizontal" : "Vertical"));
                }
            });

            boxBarcos.getChildren().add(barco);

    }

    private void posicionarBarco(String barcoId, int col, int row) {
        int tamanho = Integer.parseInt(barcoId.split("_")[1]);

        Image barcoImg = new Image(getClass().getResource("/com/devmatheusguedes/game/app/imagens/" + barcoId + ".png").toExternalForm());

        for (int i = 0; i < tamanho; i++) {
            ImageView parte = new ImageView(barcoImg);
            parte.setFitWidth(30);
            parte.setFitHeight(30);
            parte.setPreserveRatio(false);

            if (orientacaoHorizontal) {
                // Fatia horizontal
                parte.setViewport(new Rectangle2D(
                        i * (barcoImg.getWidth() / tamanho), 0,
                        barcoImg.getWidth() / tamanho, barcoImg.getHeight()
                ));
                gridTabuleiro.add(parte, col + i, row);
            } else {
                // Fatia vertical
                parte.setViewport(new Rectangle2D(
                        0, i * (barcoImg.getHeight() / tamanho),
                        barcoImg.getWidth(), barcoImg.getHeight() / tamanho
                ));
                gridTabuleiro.add(parte, col, row + i);
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
                                root.setPrefSize(800, 700);
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

    public void carergarTela(Parent root) throws IOException {
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
}

