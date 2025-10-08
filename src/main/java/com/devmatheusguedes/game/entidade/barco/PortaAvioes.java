package com.devmatheusguedes.game.entidade.barco;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

import javax.swing.*;

public class PortaAvioes extends Barco{

    public PortaAvioes(Tabuleiro tabuleiro) {
        super(TipoBarco.PORTA_AVIOES, tabuleiro);
    }


}
