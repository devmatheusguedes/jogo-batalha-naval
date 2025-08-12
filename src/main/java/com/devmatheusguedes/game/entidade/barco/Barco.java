package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.GameLogic.Missil;
import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

import javax.swing.*;
import java.util.Arrays;

public class Barco {
    private Missil missil;
    private TipoBarco tipoBarco;
    private int[][] coordenadas;
    private ImageIcon imagemBarco;

    public Barco(TipoBarco tipoBarco, Tabuleiro tabuleiro){
        this.tipoBarco = tipoBarco;
        int x = tabuleiro.getTamanhoDoTabuleiro().getX();
        int y = tabuleiro.getTamanhoDoTabuleiro().getY();
        this.coordenadas = new int[x][y];
    }


    public Missil getMissil() {
        return missil;
    }

    public void setMissil(Missil missil) {
        this.missil = missil;
    }

    public TipoBarco getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(TipoBarco tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public int[][] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public ImageIcon getImagemBarco() {
        return imagemBarco;
    }

    public void setImagemBarco(ImageIcon imagemBarco) {
        this.imagemBarco = imagemBarco;
    }

    @Override
    public String toString() {
        return "Barco{" +
                "missil=" + missil +
                ", tipoBarco=" + tipoBarco +
                ", coordenadas=" + Arrays.toString(coordenadas) +
                ", imagemBarco=" + imagemBarco +
                '}';
    }
}
