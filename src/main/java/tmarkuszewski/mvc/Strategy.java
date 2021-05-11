package tmarkuszewski.mvc;

import java.util.Arrays;
import java.util.Comparator;

/*
* Klasa ustalająca strategię komputera
*
* Komputer analizuje sytuację pozostałych graczy
* i ustala cel do osiągnięcia (status kart i ewent. liczbę punktów
* niezbędnych do zwycięstwa w partii
* */
public class Strategy {

    private HandStatus desiredStatus;
    private int desiredScore;

    Strategy(Player[] tableOfPlayers){
        this.desiredStatus = HandStatus.Busted;
        this.desiredScore = 0;
        Player player;
        /*
        * Sprawdzamy jaki max status osiagneli gracze
        * */

        Player[] temporaryTableOfPlayers = tableOfPlayers.clone(); //klonujemy tabelę graczy
        /*
        * Komparator do porownywania wynikow graczy
        * Sortujemy najpierw po statusie a potem po punktach
        * */
        Comparator<Player> playerComparator = Comparator.comparing(Player::getPlayerHandStatus)
                .thenComparing(Player::getPlayerScore);

        Arrays.sort(temporaryTableOfPlayers,playerComparator); //sortujemy sklonowaną tabelę

        /*
        * Komputer ustala swój cel gry
        * */
        desiredScore = temporaryTableOfPlayers[temporaryTableOfPlayers.length-1].getPlayerScore();
        desiredStatus = temporaryTableOfPlayers[temporaryTableOfPlayers.length-1].getPlayerHandStatus();

        /*
        * na wypadek gdy wszyscy gracze mają furę
        * */
        if (desiredStatus == HandStatus.Busted){
            desiredStatus = HandStatus.Normal;
            desiredScore = 2;
        }
        /*          Stara wersja    */
        /*for (int i = 1; i <tableOfPlayers.length ; i++) {
            player = tableOfPlayers[i];
            HandStatus playerStatus = player.getPlayerHandStatus();
            if (playerStatus.compare(playerStatus,desiredStatus) > 0){
                this.desiredStatus = player.getPlayerHandStatus();
            }
        }
        *//*
        * jeśli ten status to Normal (2-20 pkt)
        * to sprawdzamy max liczbę punktów
        * *//*
        if (desiredStatus == HandStatus.Normal){
            for (int i = 1; i < tableOfPlayers.length; i++) {
                player = tableOfPlayers[i];
                int playerScore = player.getPlayerScore();
                HandStatus playerStatus = player.getPlayerHandStatus();
                if (playerScore > desiredScore && !playerStatus.equals(HandStatus.Busted)){
                    desiredScore = playerScore;
                }
            }
        }*/


    }
    /*
    * Metoda decydujaca czy komputer ma grać dalej czy pasować
    * */
    boolean computerPlay(Player computer){
        HandStatus actualComputerStatus = computer.getPlayerHandStatus();
        int actualComputerScore = computer.getPlayerScore();
        boolean decision = desiredStatus.compare(desiredStatus, actualComputerStatus) > 0; //sprawdzamy czy aktulany status komputera gorszy od oczekiwanego
        if (desiredStatus.equals(HandStatus.Normal)){   // Jeśli gramy do 21

            // to sprawdzamy czy mamy już dosyć punktów
            // mamy tyle punktów co chcieliśmy więc pass
            decision = actualComputerScore < desiredScore;                      // jeśli nie to gramy dalej
        }
        return decision;

    }
    public String toString(){
        String result = "I need ";
        if (desiredStatus == HandStatus.Normal){
            result += desiredScore + " points";
        }
        else result+=desiredStatus;
        return result;
    }
}
