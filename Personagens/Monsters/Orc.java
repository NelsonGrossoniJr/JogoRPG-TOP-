package Personagens.Monsters;

import Methods.DiceMethods;
import Personagens.IPersonagem;

public class Orc implements IPersonagem {

    DiceMethods diceMethods = new DiceMethods();

    private int pontosDeVida = 20;
    private final int forca = 6;
    private final int defesa = 2;
    private final int agilidade = 2;
    private int fatorDeDano = diceMethods.dice8Sides();


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
