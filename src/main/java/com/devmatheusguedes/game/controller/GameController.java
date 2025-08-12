package com.devmatheusguedes.game.controller;


import com.devmatheusguedes.game.GameLogic.GameLogic;
import com.devmatheusguedes.game.PosicaoDeBarcoExepcion.ExepcionDePosicao;
import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.Barco;

public class GameController {
        GameLogic gameLogic = new GameLogic();

    public Boolean situacaoDeBatalha(Player player){
        return gameLogic.situcaoDeBatalha(player);
    }

    public Boolean posicionarBarcoTabuleiro(int linha,
                                         int coluna, boolean ishorizontal,
                                         Barco barco,
                                         Player player)throws ExepcionDePosicao {
       return gameLogic.posicionarBarcoTabuleiro(linha, coluna, barco, ishorizontal, player);
    }

    public Boolean lancarMissil(int posX, int posY, Player player){
        return gameLogic.lancarMissil(posX, posY, player);
    }
}
