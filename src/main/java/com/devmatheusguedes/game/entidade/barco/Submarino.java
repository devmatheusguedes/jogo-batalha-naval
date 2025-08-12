package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class Submarino extends Barco{
    public Submarino(Tabuleiro tabuleiro){
        super(TipoBarco.SUBMARINO, tabuleiro);
    }
}
