package Personagens.Monsters;

import Methods.DiceMethods;
import Personagens.IPersonagem;

public class MortoVivo implements IPersonagem {

    DiceMethods diceMethods = new DiceMethods();

    private int pontosDeVida = 25;
    private final int forca = 4;
    private final int defesa = 0;
    private final int agilidade = 1;
    private int fatorDeDano = diceMethods.dice4Sides() + diceMethods.dice4Sides();


    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getFatorDeDano() {
        return fatorDeDano;
    }
}