package com.devmatheusguedes.game.GameLogic;

import com.devmatheusguedes.game.PosicaoDeBarcoExepcion.ExepcionDePosicao;
import com.devmatheusguedes.game.entidade.Player;
import com.devmatheusguedes.game.entidade.barco.Barco;
import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;
import com.devmatheusguedes.game.validador.ValidarPosicaoDoBarcoNoTauleiro;

import java.util.List;

public class PosicionarTabuleiro {
    private ValidarPosicaoDoBarcoNoTauleiro validar;
    public PosicionarTabuleiro(){
        this.validar = new ValidarPosicaoDoBarcoNoTauleiro();
    }


    public Boolean posicionarNaviosNoTabuleiro(int xInicial, int yInicial,Barco barco,
                                               boolean ishorizontal,  Player player) throws ExepcionDePosicao {
            Tabuleiro tabuleiro = player.getMeuTabulerio();
            if (!validar.temEspacoVazio(tabuleiro.getPosicao(), xInicial, yInicial, ishorizontal, barco)){
                throw new ExepcionDePosicao("não tem espaço o suficiente na posição indicada");
            }
            if (ishorizontal){
                posicionarNavioHorizontal(xInicial, yInicial, barco, player);
            }else posicionarNavioVertical(xInicial, yInicial, barco, player);
            return true;


    }


    private void posicionarNavioHorizontal(int linha, int coluna, Barco barco, Player player){
        for (int i = 0; i<barco.getTipoBarco().getTamanho(); i++){
            player.getMeuTabulerio().getPosicao()[linha][coluna +i]=barco.getTipoBarco().getId();
        }
    }
    private void posicionarNavioVertical(int linha, int coluna, Barco barco, Player player){
        for (int i = 0; i<barco.getTipoBarco().getTamanho(); i++){
            player.getMeuTabulerio().getPosicao()[linha +i][coluna]=barco.getTipoBarco().getId();
        }
    }



    private void mostrarCoordenadaDoNavio(Player player, int id){
        List<Barco> barcos = player.getMeuTabulerio().getBarcos();
        barcos.forEach(barco -> {
            if (barco.getTipoBarco().getId() == id){
                System.out.println("\t\t\t\t\t\t\t"+barco.getTipoBarco().getNome());
                mostrarCoordenadas(barco.getCoordenadas());
            }
        });
    }

    public void mostrarCoordenadas(int[][] tabuleiro){
        for (int x = 0; x<tabuleiro.length; x++) {
            System.out.println();
            System.out.print(x+"\t|");
            for (int j =0; j<tabuleiro.length; j++) {
                System.out.print(tabuleiro[x][j]+"\t");
            }
        }
        System.out.println();
    }

}
