package com.devmatheusguedes.game.validador;

import com.devmatheusguedes.game.PosicaoDeBarcoExepcion.ExepcionDePosicao;
import com.devmatheusguedes.game.entidade.barco.Barco;

public class ValidarPosicaoDoBarcoNoTauleiro {
    public Boolean temEspacoVazio(int[][] ints, int x,
                                   int y, boolean isHorizontal,
                                   Barco barco) throws ExepcionDePosicao {
        if (isHorizontal){
            return verificarEspacoNaHorizontal(ints, x, y, isHorizontal, barco);
        }
        return verificarEspacoNaVertical(ints, x, y, isHorizontal, barco);
    }

    private Boolean verificarEspacoNaHorizontal(int[][] ints, int xInicial,
                                                int yInicial, boolean isHorizontal,  Barco barco){
        try {
            boolean temEspaco = true;
            for (int x = 0; x < barco.getTipoBarco().getTamanho(); x++) {
                if (xInicial+x > ints.length) throw new ExepcionDePosicao("O barco excede o tamanho do tabuleiro.");
                temEspaco = Boolean.logicalAnd(temEspaco, ints[xInicial + x][yInicial] == 0);
            }
            return temEspaco;
        }catch (ExepcionDePosicao e){
            throw new ExepcionDePosicao("A embarcação esta excedendo o tamanho do tabuleiro");
        }
    }
    private Boolean verificarEspacoNaVertical(int[][] ints, int xInicial, int yInicial, boolean isHorizontal,  Barco barco){
        boolean temEspaco = true;
        for (int y = 0; y<barco.getTipoBarco().getTamanho(); y++){
            if (yInicial+y > ints.length) return false;
            temEspaco = Boolean.logicalAnd(temEspaco, ints[xInicial][yInicial+y] == 0);
        }
        return temEspaco;
    }
}
