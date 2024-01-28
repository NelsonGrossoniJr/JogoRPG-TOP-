package Personagens.Heroes;

import Methods.DiceMethods;
import Personagens.IPersonagem;

public class Barbaro implements IPersonagem {
    DiceMethods diceMethods = new DiceMethods();

    private int pontosDeVida = 13;
    private final int forca = 6;
    private final int defesa = 1;
    private final int agilidade = 3;
    private int fatorDeDano = diceMethods.dice6Sides() + diceMethods.dice6Sides();

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
