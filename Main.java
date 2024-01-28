import Methods.BattleMethods;
import Methods.DesignMethods;
import Personagens.Heroes.Barbaro;
import Personagens.Heroes.Guerreiro;
import Personagens.Heroes.Paladino;
import Personagens.IPersonagem;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BattleMethods battleMethods = new BattleMethods();
        DesignMethods designMethods = new DesignMethods();
        Scanner scanner = new Scanner(System.in);
        String fileOrPlay = "";

        while(!fileOrPlay.equals("relatorio") && !fileOrPlay.equals("jogar"))
        {
            System.out.println("Você Gostaira de acessar o Relatorio ou Jogar ??");
            System.out.println("--> Relatorio <--");
            System.out.println("-->   Jogar   <--");
            fileOrPlay = scanner.nextLine().toLowerCase();

            switch(fileOrPlay){

                case "relatorio" -> battleMethods.searchingFile();

                case "jogar" -> {

                    System.out.println("Digite seu nickName:");
                    String nickname = scanner.nextLine();
                    String whichHeroes = battleMethods.startingBattle(nickname);

                    switch (whichHeroes) {

                        case "guerreiro" -> {
                            Guerreiro guerreiro = new Guerreiro();
                            designMethods.whichHeroDesign("Guerreiro");

                            IPersonagem monster = battleMethods.creatRandomMonster();

                            battleMethods.campBattle(monster, guerreiro, nickname);
                        }

                        case "barbaro" -> {
                            Barbaro barbaro = new Barbaro();
                            designMethods.whichHeroDesign("Barbaro");

                            IPersonagem monster = battleMethods.creatRandomMonster();

                            battleMethods.campBattle(monster, barbaro, nickname);
                        }

                        case "paladino" -> {
                            Paladino paladino = new Paladino();
                            designMethods.whichHeroDesign("Paladino");

                            IPersonagem monster = battleMethods.creatRandomMonster();

                            battleMethods.campBattle(monster, paladino, nickname);
                        }

                        default -> System.out.println("Falha ao escolher a Classe do Heroi.");
                    }
                }
                default -> {
                    System.out.println("Escolha Incorreta!");
                    System.out.println("Insira Relatorio ou Jogar.");
                    System.out.println();
                }
            }
        }
        scanner.close();
    }
}