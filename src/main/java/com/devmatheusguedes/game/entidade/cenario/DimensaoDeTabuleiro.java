package com.devmatheusguedes.game.entidade.cenario;

public enum DimensaoDeTabuleiro {
        PEQUENO(10, 10),
        NORMAL(20, 20),
        MEDIO(40, 40),
        GRANDE(60, 60);

        private int x;
        private int y;

        DimensaoDeTabuleiro(int x, int y){
            this.x = x;
            this.y = y;
        }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
