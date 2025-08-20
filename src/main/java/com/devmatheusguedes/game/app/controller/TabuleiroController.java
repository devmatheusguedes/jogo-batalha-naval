package com.devmatheusguedes.game.app.controller;

import com.devmatheusguedes.game.entidade.Player;

public class TabuleiroController {
    public void retirarParteDobarco(int x, int y, Player player){
        System.out.println("retirando barco do tabuleiro de "+player.getNome());
        player.getMeuTabulerio().getPosicao()[x][y] = 0;

    }

    public int verificarSeTemBarcosNoTabuleiro(Player player){
        int[][] posicao = player.getMeuTabulerio().getPosicao();
        int num = 0;
        for (int [] i : posicao){
            for (int j = 0; j < posicao.length; j++){
                num += i[j];
            }
        }
        return num;
    }
        }
