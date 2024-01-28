package Methods;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BattleMetodsPlus {

    public void heroMostPlayed(List<String> fileLinesCSV)
    {
        AtomicInteger contadorBarbaroAtom = new AtomicInteger(0);
        AtomicInteger contadorPaladinoAtom = new AtomicInteger(0);
        AtomicInteger contadorGuerreiroAtom = new AtomicInteger(0);

        fileLinesCSV.stream()
                .map(line -> line.split(";"))
                .forEach(columns -> {
                    switch (columns[1].toLowerCase()) {
                        case "barbaro" -> contadorBarbaroAtom.incrementAndGet();
                        case "paladino" -> contadorPaladinoAtom.incrementAndGet();
                        case "guerreiro" -> contadorGuerreiroAtom.incrementAndGet();
                        default -> System.out.println("Erro ao buscar contador de heroes.");
                    }
                });
        int contadorBarbaro = contadorBarbaroAtom.get();
        int contadorPaladino = contadorPaladinoAtom.get();
        int contadorGuerreiro = contadorGuerreiroAtom.get();

        int mostPlayedCounter = contadorBarbaro;
        String nameMostPlayed = "Barbaro";

        if (contadorPaladino > mostPlayedCounter) {
            mostPlayedCounter = contadorPaladino;
            nameMostPlayed = "Paladino";
        }

        if (contadorGuerreiro > mostPlayedCounter) {
            mostPlayedCounter = contadorGuerreiro;
            nameMostPlayed = "Guerreiro";
        }

        System.out.println("O heroi mais jogado foi o: " + nameMostPlayed);
    }

    public void monsterMostPlayed(List<String> fileLinesCSV)
    {
        AtomicInteger contadorMortoVivoAtom = new AtomicInteger(0);
        AtomicInteger contadorOrcAtom = new AtomicInteger(0);
        AtomicInteger contadorKoboldAtom = new AtomicInteger(0);

        fileLinesCSV.stream()
                .map(line -> line.split(";"))
                .forEach(columns -> {
                    switch (columns[3].toLowerCase()) {
                        case "mortovivo" -> contadorMortoVivoAtom.incrementAndGet();
                        case "orc" -> contadorOrcAtom.incrementAndGet();
                        case "kobold" -> contadorKoboldAtom.incrementAndGet();
                        default -> System.out.println("Erro ao buscar contador de heroes.");
                    }
                });
        int contadorMortoVivo = contadorMortoVivoAtom.get();
        int contadorOrc = contadorOrcAtom.get();
        int contadorKobold = contadorKoboldAtom.get();

        int mostPlayedCounter = contadorMortoVivo;
        String nameMostPlayed = "MortoVivo";

        if (contadorOrc > mostPlayedCounter) {
            mostPlayedCounter = contadorOrc;
            nameMostPlayed = "Orc";
        }

        if (contadorKobold > mostPlayedCounter) {
            mostPlayedCounter = contadorKobold;
            nameMostPlayed = "Kobold";
        }

        System.out.println("O monstro mais jogado foi o: " + nameMostPlayed);
    }

    public void calcTotalPoints(List<String> fileLinesCSV)
    {
        AtomicInteger contadorGanhadorAtom = new AtomicInteger(0);


        fileLinesCSV.stream()
                .map(line -> line.split(";"))
                .forEach(columns -> {
                    if(columns[2].equalsIgnoreCase("ganhou"))
                    {
                        contadorGanhadorAtom.incrementAndGet();
                    }
                });

        int contadorGanhador = contadorGanhadorAtom.get();

        int totalPoints = contadorGanhador * 100;

        System.out.println("O total de pontos do Jogador é: " + totalPoints);
    }


}
