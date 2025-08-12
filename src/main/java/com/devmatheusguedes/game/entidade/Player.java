package com.devmatheusguedes.game.entidade;

import com.devmatheusguedes.game.entidade.cenario.Tabuleiro;

public class Player {
    private String nome;
    private Tabuleiro meuTabulerio;



    public Player(String nome){
        this.nome = nome;
        this.meuTabulerio = new Tabuleiro();
    }


    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Tabuleiro getMeuTabulerio() {
        return meuTabulerio;
    }

    public void setMeuTabulerio(Tabuleiro meuTabulerio) {
        this.meuTabulerio = meuTabulerio;
    }






}
