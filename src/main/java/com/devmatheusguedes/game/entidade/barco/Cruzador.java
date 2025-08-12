package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class Cruzador extends Barco{

    public Cruzador(Tabuleiro tabuleiro){
        super(TipoBarco.CRUZADOR, tabuleiro);
    }
}
