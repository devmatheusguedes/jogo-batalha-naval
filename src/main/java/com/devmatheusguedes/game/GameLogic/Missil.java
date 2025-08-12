package com.devmatheusguedes.game.GameLogic;


import com.devmatheusguedes.game.entidade.Player;

import javax.swing.*;

public class Missil extends Radar {
    private ImageIcon imagemDeExplosao = new ImageIcon();


    public Boolean lancarMissil(int posX, int posY, Player player){
        if (localizarEmbarcacao(posX, posY, player)){
            var barco = reconhecerBarco(posX, posY, player);
            if (barco != null) {
                System.out.println("você encontrou o barco: "+ mostrarNomeBarco(barco));
                return true;
            }
        }
        System.out.println("nenhum navio nas posicções: x= "+posX + " y= "+posY);
        return false;
    }

    public ImageIcon getImagemDeExplosao() {
        return imagemDeExplosao;
    }

    public void setImagemDeExplosao(ImageIcon imagemDeExplosao) {

        this.imagemDeExplosao = imagemDeExplosao;
    }

}
