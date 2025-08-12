package com.devmatheusguedes.game.GameLogic;

import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.TipoBarco;

public  class Radar {

    public boolean localizarEmbarcacao(int posX, int posY, Player player){
        return player.getMeuTabulerio().getPosicao()[posX][posY] != 0;
    }
    public TipoBarco reconhecerBarco(int x, int y,Player player){
        int[][] tabuleiro = player.getMeuTabulerio().getPosicao();
        TipoBarco barco;
        for (TipoBarco t : TipoBarco.values()){
            if (t.getId() == tabuleiro[x][y]){
                barco = t;
                return barco;
            }
        }
        return null;
    }

    public String mostrarNomeBarco(TipoBarco barco){
       return barco.getNome();
    }
}
