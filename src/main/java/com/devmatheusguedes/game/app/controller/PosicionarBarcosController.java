package com.devmatheusguedes.game.app.controller;

import com.devmatheusguedes.game.app.util.DragContextImage;
import com.devmatheusguedes.game.app.util.DragDataFormat;
import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.Barco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    private void montarTabuleiro(int tamanho) {
        tabuleiro.getChildren().clear();
        tabuleiro.getColumnConstraints().clear();
        tabuleiro.getRowConstraints().clear();

        // define constraints de colunas
        for (int i = 0; i < tamanho; i++){
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setPrefWidth(50);
            tabuleiro.getColumnConstraints().add(constraints);
        }

        // define constraints de linhas
        for (int j = 0; j < tamanho; j++){
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(50);
            tabuleiro.getRowConstraints().add(rowConstraints);
        }

        // Monta as células (StackPane) do tabuleiro
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                StackPane quadrado = new StackPane();
                quadrado.setPrefSize(50, 50);
                GridPane.setRowIndex(quadrado, i);
                GridPane.setColumnIndex(quadrado, j);
                // usa a mesma cor padrão definida em CSS; caso queira inline, mantenha consistente
                quadrado.setStyle("-fx-background-color: #adb5bd;");
                carregarEstiloDeElemento(quadrado);
                addEventoDeDragAndDropNoTabuleiro(quadrado, i, j);
                tabuleiro.getChildren().add(quadrado);
            }
        }
    }


    // passo2: adcionar os barcos a barra lateral esquerda ok
    // passo2: adicionar os barcos à barra lateral esquerda (corrigido: fecha FileInputStream)
    public void addImageBarcosLeftPane(){
        List<Barco> barcos = player.getMeuTabulerio().getBarcos();
        for (Barco barco : barcos) {
            // try-with-resources garante que o stream seja fechado
            try (FileInputStream fis = new FileInputStream(barco.getTipoBarco().getPathImage())) {
                Image image = new Image(fis);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(80);
                imageView.setFitWidth(130);
                imageView.setPreserveRatio(true);

                // adicionando o id do barco (uso Integer, mas String funciona bem)
                barcoEventoDeDragAndDrop(imageView, barco.getTipoBarco().getId());

                // se quiser mostrar tooltip com id
                Tooltip.install(imageView, new Tooltip("ID: " + barco.getTipoBarco().getId()));

                boxBarcos.getChildren().add(imageView);
            } catch (IOException e) {
                System.out.println("falha ao carregar imagem de barco: " + e.getMessage());
            }
        }
    }


    // passo 3: mover os barcos para o tabuleiro
    // passo 3: configurar drag no ImageView de origem
    private void barcoEventoDeDragAndDrop(ImageView sourceImage, int id){
        // armazenamos o id no node para facilitar acesso posterior
        sourceImage.setUserData(id);

        // Ao iniciar o drag: guardamos referência estática e colocamos conteúdo no Dragboard
        sourceImage.setOnDragDetected(event -> {
            // guarda referência temporária ao ImageView original (para clonar depois)
            DragContextImage.barcoImagem = sourceImage;

            // inicia drag operation
            Dragboard dragboard = sourceImage.startDragAndDrop(TransferMode.MOVE);

            // cria o conteúdo: colocamos a imagem (para visual) e o ID (via DataFormat personalizado)
            ClipboardContent content = new ClipboardContent();
            content.putImage(sourceImage.getImage()); // facilita obter a imagem no drop
            content.put(DragDataFormat.SHIP_FORMAT, sourceImage.getUserData()); // id do barco (Integer)
            dragboard.setContent(content);

            // Criar snapshot sem transparência
            // snapshot da imagem original
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image snapshot = sourceImage.snapshot(params, null);

// Cria uma nova imagem com opacidade total (forçando alfa 1.0)
            WritableImage opaqueImage = new WritableImage((int) snapshot.getWidth(), (int) snapshot.getHeight());
            PixelReader reader = snapshot.getPixelReader();
            PixelWriter writer = opaqueImage.getPixelWriter();

            for (int y = 0; y < snapshot.getHeight(); y++) {
                for (int x = 0; x < snapshot.getWidth(); x++) {
                    Color color = reader.getColor(x, y);
                    writer.setColor(x, y, new Color(color.getRed(), color.getGreen(), color.getBlue(), 1.0)); // opaco
                }
            }

            dragboard.setDragView(opaqueImage, event.getX(), event.getY());



            event.consume();
        });

        // Ao terminar o drag (sucesso ou não) limpamos o contexto e, se sucesso e TransferMode.MOVE,
        // podemos remover o ImageView original da lista lateral (se esse for o comportamento desejado).
        sourceImage.setOnDragDone(event -> {
            // Se a transferência foi um MOVE e foi completada com sucesso, remova o original
            if (event.getTransferMode() == TransferMode.MOVE && event.isDropCompleted()) {
                // remove da vbox (impede posicionar o mesmo barco mais de uma vez)
                boxBarcos.getChildren().remove(sourceImage);
            }
            // independente do resultado, limpamos o contexto
            DragContextImage.barcoImagem = null;
            event.consume();
        });
    }


    private void addEventoDeDragAndDropNoTabuleiro(StackPane quadrado, int row, int col){
        // indica que a célula pode aceitar o drop de um navio
        quadrado.setOnDragOver(event -> {
            if (event.getDragboard().hasContent(DragDataFormat.SHIP_FORMAT) &&
                    event.getGestureSource() != quadrado) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // feedback visual ao entrar com o navio
        quadrado.setOnDragEntered(event -> {
            if (event.getDragboard().hasContent(DragDataFormat.SHIP_FORMAT)) {
                quadrado.setStyle("-fx-background-color: #74c0fc;");
            }
        });

        // remove o efeito ao sair (usa a cor padrão do tabuleiro)
        quadrado.setOnDragExited(event -> quadrado.setStyle("-fx-background-color: #adb5bd;"));

        // quando o navio é solto
        quadrado.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean sucesso = false;

            if (db.hasContent(DragDataFormat.SHIP_FORMAT)) {
                // recupera o objeto ImageView original (referência estática)
                ImageView originalImage = DragContextImage.barcoImagem;

                if (originalImage != null) {
                    // Cria uma cópia visual do navio mantendo imagem e propriedades relevantes
                    ImageView clone = new ImageView(originalImage.getImage());

                    // preserva tamanho e proporção
                    clone.setFitWidth(originalImage.getFitWidth());
                    clone.setFitHeight(originalImage.getFitHeight());
                    clone.setPreserveRatio(originalImage.isPreserveRatio());

                    // preserva rotação e transforms (se necessário)
                    clone.setRotate(originalImage.getRotate());
                    clone.getTransforms().addAll(originalImage.getTransforms());

                    // copia o userData (id do navio) para manter associação
                    clone.setUserData(originalImage.getUserData());

                    // adiciona no tabuleiro (visual)
                    quadrado.getChildren().add(clone);

                    // comunicar à sua lógica de jogo: (exemplo)
                    int idBarco = (Integer) originalImage.getUserData();
                    // Aqui você chamaria: player.getMeuTabulerio().posicionarBarco(idBarco, row, col);
                    // ou chamar GameLogic.posicionarBarco(...). Faça validação de sobreposição e bounds.
                    System.out.println("Navio " + idBarco + " posicionado em (" + row + ", " + col + ")");

                    sucesso = true;
                }

                // limpando o contexto aqui também — a remoção final acontece em dragDone
                DragContextImage.barcoImagem = null;
            }

            event.setDropCompleted(sucesso);
            event.consume();
        });
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
                            montarTabuleiro(tamanhoTabuleiro);
                        }
                    }
                }
        );
        btnCancelar.setOnAction(e -> {
            ScrenManager.loadScreen(ScrenManager.POSICIONAR_BARCOS_FXML, 800, 500);
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


}

