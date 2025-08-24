package com.devmatheusguedes.game.entidade.barco;

public enum TipoBarco {
    BOTE("Bote", 1, 0, "com/devmatheusguedes/game/app/images/barco0.png"),
    DESTROIER("Destroier", 2, 1, "com/devmatheusguedes/game/app/images/barco1.png"),
    CRUZADOR("Cruzado", 3, 2, "com/devmatheusguedes/game/app/images/barco2.png"),
    SUBMARINO("Submariono", 3, 3, "com/devmatheusguedes/game/app/images/barco3.png"),
    ENCOURACADO("Encouraçado", 4, 4, "com/devmatheusguedes/game/app/images/barco4.png"),
    PORTA_AVIOES("Porta-Aviôes", 5, 5, "com/devmatheusguedes/game/app/images/barco5.png");

    private int id;
    private String nome;
    private int tamanho;
    private String pathImage;

    TipoBarco(String nome, int tamanho, int id, String pathImage){
        this.nome = nome;
        this.tamanho = tamanho;
        this.id = id;
        this.pathImage = pathImage;
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

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
