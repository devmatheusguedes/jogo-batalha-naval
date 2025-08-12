package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class Destroier extends Barco{

    public Destroier(Tabuleiro tabuleiro) {
        super(TipoBarco.DESTROIER, tabuleiro);
    }
}
