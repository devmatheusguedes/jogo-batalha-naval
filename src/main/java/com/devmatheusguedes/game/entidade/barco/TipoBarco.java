package com.devmatheusguedes.game.entidade.barco;

public enum TipoBarco {
    DESTROIER("Destroier", 2, 1),
    CRUZADOR("Cruzado", 3, 2),
    SUBMARINO("Submariono", 3, 3),
    ENCOURACADO("Encouraçado", 4, 4),
    PORTA_AVIOES("Porta-Aviôes", 5, 5);

    private int id;
    private String nome;
    private int tamanho;

    TipoBarco(String nome, int tamanho, int id){
        this.nome = nome;
        this.tamanho = tamanho;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
