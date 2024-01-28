package Methods;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import Personagens.Heroes.Barbaro;
import Personagens.Heroes.Guerreiro;
import Personagens.Heroes.Paladino;
import Personagens.IPersonagem;
import Personagens.Monsters.*;

public class BattleMethods extends BattleMetodsPlus {

    DesignMethods designMethods = new DesignMethods();
    public void searchingFile(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nickname para achar o arquivo e gerar o relatorio: ");
        String searchFile = scanner.nextLine().toLowerCase();

        String basePath = "src\\temp";
        Path path = Paths.get(basePath, searchFile + ".csv").toAbsolutePath();

        Scanner fileReaderCSV = null;
        try{
            fileReaderCSV = new Scanner(new File(path.toUri()));
        }catch(FileNotFoundException ex){
            System.out.println("Não achou nenhum arquivo com esse nome.");
            System.out.println();
        }

        List<String> fileLinesCSV = new ArrayList<>();
        try{
            while (Objects.requireNonNull(fileReaderCSV).hasNextLine()) {
                fileLinesCSV.add(fileReaderCSV.nextLine());
            }
            fileReaderCSV.close();
        } catch(NullPointerException ignored){}

        heroMostPlayed(fileLinesCSV);
        monsterMostPlayed(fileLinesCSV);
        calcTotalPoints(fileLinesCSV);

        scanner.close();
        Objects.requireNonNull(fileReaderCSV).close();

    }


    public String startingBattle(String nickname){

        Scanner scanner = new Scanner(System.in);

        String whichHeroes = "";
        while(!whichHeroes.equals("guerreiro") && !whichHeroes.equals("barbaro") && !whichHeroes.equals("paladino"))
        {
            System.out.println("Olá "+ nickname + ", com qual Classe de  você deseja jogar ?");
            System.out.println("--> Guerreiro <--");
            System.out.println("--> Paladino  <--");
            System.out.println("--> Barbaro   <--");

            whichHeroes = scanner.nextLine().toLowerCase();

            if (!whichHeroes.equals("guerreiro") && !whichHeroes.equals("barbaro") && !whichHeroes.equals("paladino"))
            {
                System.out.println("Classe Incorreta!");
                System.out.println("Insira Guerreiro, Barbaro ou Paladino.");
                System.out.println();
            }
        }
        scanner.close();

        return whichHeroes;
    }


    public IPersonagem creatRandomMonster(){

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ Criando um monstro aleatorio ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        Random random = new Random();

        IPersonagem randomMonster = null;
        MortoVivo mortoVivo = new MortoVivo();
        Orc orc = new Orc();
        Kobold kobold = new Kobold();

        int randomNumber = random.nextInt(3) + 1;

        switch (randomNumber) {

            case 1 -> {
                randomMonster = mortoVivo;
                designMethods.creatRandomMonsterDesign(mortoVivo);
            }

            case 2 -> {
                randomMonster = orc;
                designMethods.creatRandomMonsterDesign(orc);
            }

            case 3 -> {
                randomMonster = kobold;
                designMethods.creatRandomMonsterDesign(kobold);
            }

            default -> System.out.println("Falha ao sortear o monstro.");
        }

        return randomMonster;
    }

    public void campBattle(IPersonagem monster, IPersonagem heroe, String nickNamePessoa) throws IOException {

        int contadorRodadas = 0;
        Date dataAtual = new Date();
        String isHeroWin;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(dataAtual);

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓ Entrou no Campo de Batalha com o Monstro: " + monster.getClass().getSimpleName() + ", e com o Heroi: " + heroe.getClass().getSimpleName() + ". ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓ E só sairá quando a vida do Monstro ou do Heroi for igual ou menor a zero. ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

        while(!(monster.getPontosDeVida() <= 0 || heroe.getPontosDeVida() <= 0))
        {
            System.out.println();

            contadorRodadas++;

            IPersonagem personagem = whoStarts(monster, heroe);

            if(personagem instanceof MortoVivo || personagem instanceof Orc || personagem instanceof Kobold)
            {
                int attackPower = calcAttack(personagem);

                int defensePower = calcDefense(heroe);

                if(attackPower > defensePower)
                {
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ O poder de ataque do Monstro foi maior que o poder de defesa do Heroi. ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    int powerDamage = calcDamage(personagem);

                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ O poder de dano do Monstro ira descontar no ponto de vida do Heroi[" + heroe.getPontosDeVida() + "] ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    int remainingLife = heroe.getPontosDeVida() - powerDamage;
                    heroe.setPontosDeVida(remainingLife);

                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ A vida do heroi agora é: " + heroe.getPontosDeVida() + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    if(personagem.getPontosDeVida() <= 0 || heroe.getPontosDeVida() <= 0)
                    {
                        if(heroe.getPontosDeVida() <= 0){
                            isHeroWin = "Perdeu";
                        } else {
                            isHeroWin = "Ganhou";
                        }
                        System.out.println(dataFormatada +  ";" + heroe.getClass().getSimpleName() + ";" + isHeroWin + ";" + personagem.getClass().getSimpleName() + ";" + contadorRodadas);

                        String basePath = "src\\temp";
                        Path path = Paths.get(basePath, nickNamePessoa + ".csv").toAbsolutePath();

                        FileWriter csvWriter = new FileWriter(new File(path.toUri()), true);
                        BufferedWriter bufferedWriter = new BufferedWriter(csvWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);

                        printWriter.println(dataFormatada +  ";" + heroe.getClass().getSimpleName() + ";" + isHeroWin + ";" + monster.getClass().getSimpleName() + ";" + contadorRodadas);
                        printWriter.close();
                        bufferedWriter.close();
                        csvWriter.close();

                    }
                }
                else
                {
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓ Nada aconteceu, o atacante nao tem poder suficiente ou é igual ao do adversário ▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                }
            }

            else if(personagem instanceof Guerreiro || personagem instanceof Barbaro || personagem instanceof Paladino)
            {
                int attackPower = calcAttack(personagem);

                int defensePower = calcDefense(monster);

                if(attackPower > defensePower)
                {
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ O poder de ataque do heroi foi maior que o poder de defesa do monstro. ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    int powerDamage = calcDamage(personagem);

                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓ O poder de dano do Heroi ira descontar no ponto de vida do Monstro[" + monster.getPontosDeVida() + "] ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    int remainingLife = monster.getPontosDeVida() - powerDamage;
                    monster.setPontosDeVida(remainingLife);

                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ A vida do monstro agora é: " + monster.getPontosDeVida() + " ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");


                    if(personagem.getPontosDeVida() <= 0 || monster.getPontosDeVida() <= 0)
                    {
                        if(personagem.getPontosDeVida() <= 0){
                            isHeroWin = "Perdeu";
                        } else {
                            isHeroWin = "Ganhou";
                        }
                        System.out.println(dataFormatada +  ";" + personagem.getClass().getSimpleName() + ";" + isHeroWin + ";" + monster.getClass().getSimpleName() + ";" + contadorRodadas);

                        String basePath = "src\\temp";
                        Path path = Paths.get(basePath, nickNamePessoa + ".csv").toAbsolutePath();

                        FileWriter csvWriter = new FileWriter(new File(path.toUri()), true);
                        BufferedWriter bufferedWriter = new BufferedWriter(csvWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);

                        printWriter.println(dataFormatada +  ";" + personagem.getClass().getSimpleName() + ";" + isHeroWin + ";" + monster.getClass().getSimpleName() + ";" + contadorRodadas);
                        printWriter.close();
                        bufferedWriter.close();
                        csvWriter.close();
                    }
                }
                else
                {
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓ Nada aconteceu, o atacante nao tem poder suficiente ou é igual ao do adversário ▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                }
            }
        }

    }
    
    public IPersonagem whoStarts(IPersonagem monster, IPersonagem heroe) {

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ Verificando qual Personagem ira começar irá começar... ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

        boolean verifyLoop = true;
        IPersonagem whoReturn = null;

            while (verifyLoop) {

                int initiativeHeroe = dice10Sides() + heroe.getAgilidade();
                int initiativeMonster = dice10Sides() + monster.getAgilidade();

                    if (initiativeHeroe > initiativeMonster) {
                        whoReturn = heroe;
                        verifyLoop = false;

                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ Heroi vai começar ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

                    } else if (initiativeHeroe < initiativeMonster) {
                        whoReturn = monster;
                        verifyLoop = false;

                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ Monstro vai começar ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                    }
                }

        return whoReturn;
    }


    public int calcAttack(IPersonagem personagem) {

        String type = "ataque";
        int attackPower = dice10Sides() + personagem.getAgilidade() + personagem.getForca();
        designMethods.calcDesign(personagem, attackPower, type);

        return attackPower;
    }

    public int calcDefense (IPersonagem personagem) {

        String type = "defesa";
        int defensePower = dice10Sides() + personagem.getAgilidade() + personagem.getDefesa();
        designMethods.calcDesign(personagem, defensePower, type);
        return defensePower;
    }

    public int calcDamage(IPersonagem personagem) {

        String type = "dano";
        int damagePower = personagem.getFatorDeDano() + personagem.getForca();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        designMethods.calcDesign(personagem, damagePower, type);
        return damagePower;
    }

    public int dice10Sides(){
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

}
