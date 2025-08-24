package com.devmatheusguedes.game.entidade.cenario;

import com.devmatheusguedes.game.entidade.barco.*;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int[][] posicao;
    private List<Barco> barcos;
    private DimensaoDeTabuleiro tamanhoDoTabuleiro = DimensaoDeTabuleiro.NORMAL; // padrão

    public Tabuleiro(){
        this.barcos = new ArrayList<>(){};
        this.barcos.add(0, new Cruzador(this));
        this.barcos.add(1, new PortaAvioes(this));
        this.barcos.add(2, new Destroier(this));
        this.barcos.add(3, new Encouracado(this));
        this.barcos.add(4, new Submarino(this));
        this.posicao = new int[tamanhoDoTabuleiro.getX()][tamanhoDoTabuleiro.getY()];

    }

    public int[][] getPosicao() {
        return posicao;
    }

    public void setPosicao(int[][] posicao) {
        this.posicao = posicao;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public DimensaoDeTabuleiro getTamanhoDoTabuleiro() {
        return tamanhoDoTabuleiro;
    }

    public void setTamanhoDoTabuleiro(DimensaoDeTabuleiro tamanhoDoTabuleiro) {
        this.tamanhoDoTabuleiro = tamanhoDoTabuleiro;
    }


}
