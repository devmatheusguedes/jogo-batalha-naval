package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class Encouracado extends Barco{
    public Encouracado(Tabuleiro tabuleiro){

        super(TipoBarco.ENCOURACADO, tabuleiro);
    }
}
