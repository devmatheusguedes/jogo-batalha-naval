package com.devmatheusguedes.game.GameLogic;

import com.devmatheusguedes.game.PosicaoDeBarcoExepcion.ExepcionDePosicao;
import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.Barco;
import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class GameLogic {
    Missil missil = new Missil();
    public Boolean situcaoDeBatalha(Player player){
        boolean continuarJogo = true;
        //verificar se o player tem, pelo menos, uma embarcação para batalhar.
        Tabuleiro tabuleiro = player.getMeuTabulerio();
        int x = tabuleiro.getTamanhoDoTabuleiro().getX();
        int y = tabuleiro.getTamanhoDoTabuleiro().getY();
        Radar radar = new Radar();
        for(int i = 0; i < x; i++){
            for (int j = 0; j<y; j++){
                    continuarJogo = Boolean.logicalAnd(continuarJogo, radar.localizarEmbarcacao(i, j, player));
            }
        }
        return continuarJogo;
    }

    public boolean posicionarBarcoTabuleiro(int linha, int coluna,
                                            Barco barco, boolean ishorizontal,
                                            Player player)throws ExepcionDePosicao {

        PosicionarTabuleiro posicionarTabuleiro = new PosicionarTabuleiro();
        return posicionarTabuleiro.posicionarNaviosNoTabuleiro(linha, coluna, barco, ishorizontal, player);
    }

    public Boolean lancarMissil(int posX, int posY, Player player){
        return missil.lancarMissil(posX, posY, player);
    }
}
