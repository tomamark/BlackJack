package tmarkuszewski.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private int numberOfPlayers;
    private Player[] tableOfPlayers; // tablica z graczami


    public Controller(){
    /*
    *   Ustawienia startowe gry
    *  */

        boolean playAgain = false;   // koniec gry
        boolean isGameFinished;     // koniec pojedynczej partii



        View.render();                                      // Metoda render() bez argsów wyświetla ekran powitalny
        setNumberOfPlayers();                               // Ustawiamy liczbę graczy
        tableOfPlayers = new Player[numberOfPlayers+1];     // Tworzymy tabelę graczy
        setPlayers();                                       // Tworzymy graczy

        /*
        * Główna pętla programu (do...while)
        * wykonuje się aż gracze nie zdecydują o wyjściu z gry
        * */
        do {                                    //while (playAgain)
            View.clearScreen();
            //View.render(tableOfPlayers,5);
            Deck deck = new Deck();             //generujemy karty
            /*
            * Pętla for ... powtarza czynności dla każdego gracza
            * */
            for (int i = 1 ; i <numberOfPlayers+1 ; i++) {
                View.render(tableOfPlayers,i);
                Player player = tableOfPlayers[i];
                do {
                    Card cardForPlayer = deck.getCardFromDeck();  // Pobieramy kartę dla gracza
                    giveNextCardToPlayer(i, player, cardForPlayer);
                    if (!player.getHasFinished()) {
                        askForPlayerDecision(i, player);
                    }


                }while (!player.getHasFinished());

            }

            Strategy computerStrategy = new Strategy(tableOfPlayers);
            View.render(tableOfPlayers,0);
            // TODO: 04.05.2021  Komputer dobiera karty zgodnie ze strategią

            /*
            * Wybór zwycięzcy
            * */

            List<Integer>listOfWinners = getListOfWinners (tableOfPlayers);
            updatePlayersWins(listOfWinners,tableOfPlayers);
            View.showWinners (listOfWinners,tableOfPlayers);



         }while (playAgain);



    }

    private void updatePlayersWins(List<Integer> listOfWinners, Player[] tableOfPlayers) {
        for (int number:listOfWinners) {
            tableOfPlayers[number].incrementPlayerNumberOfWins();
        }
    }

    private List<Integer> getListOfWinners(Player[] tableOfPlayers) {
       List<Integer> listOfWinners = new ArrayList<>();
       int maxStatus = 0;
       int maxScore = 0;
        for (int i = 0; i < tableOfPlayers.length; i++) {
            Player player = tableOfPlayers[i];
            int playerStatus = player.getPlayerHandStatus();

            if (playerStatus == maxStatus){             //W przypadku gdy statusy są równe porównujemy punkty
                if (player.getPlayerScore() > maxScore){    // jesli wiecej punktow to czyscimy liste
                    listOfWinners.clear();
                    listOfWinners.add(i);               //dodajemy aktualny numer do listy
                    maxScore = player.getPlayerScore(); // aktualizujemy max wynik
                }
                if (player.getPlayerScore() == maxScore){ // jesli statusy i punkty rowne to remis
                    if (!listOfWinners.contains(i)) listOfWinners.add(i);
                }

            }
            if (playerStatus > maxStatus){                // Porównujemy statusy kart graczy
                listOfWinners.clear();                    // Jesli aktualny jest wyzszy czyścimy liste
                listOfWinners.add(i);                     // dodajemy nr aktualny gracza do listy
                maxStatus = playerStatus;                 // aktualizujemy max status
                maxScore = player.getPlayerScore();       // i punkty
            }
        }

        return listOfWinners;
    }

    private void askForPlayerDecision(int playerNumber, Player player) {
        View.showQuestion(playerNumber, player);
        boolean validAnswer = false;
        String answer;
        while (!validAnswer){
           answer = getStringFromConsole().toUpperCase();
            if (answer.equals("P") || answer.equals("PASS")){
                player.setHasFinished(true);
                validAnswer = true;
            }
            if (answer.equals("N") || answer.equals("NEXT")){
                validAnswer = true;
            }
        }
    }

    private String getStringFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }



    private void setNumberOfPlayers() {
        String input = getStringFromConsole();
        try{
            numberOfPlayers = Integer.parseInt(input);
            if (numberOfPlayers < 0 || numberOfPlayers > 4){
                numberOfPlayers = 1;
            }
        }catch (NumberFormatException ex){
            numberOfPlayers = 1;
        }

    }

    private void setPlayers(){
        Player computerPlayer = new Player("Computer");     //tworzymy gracza dla komputera...
        tableOfPlayers[0] = computerPlayer;                           //... i dodajemy do tabeli pod indeks 0
        for (int i = 1; i <= numberOfPlayers ; i++) {
            String playerName = "Player "+i;
            View.render(playerName);
            String input = getStringFromConsole();
            if (!input.isBlank()) {
                playerName = input;
                if (playerName.length()>8){
                    playerName = playerName.substring(0,8);    //ograniczamy długość do 8 znaków;
                }
            }

            Player nextPlayer = new Player(playerName);     //tworzymy gracza
            tableOfPlayers[i] = nextPlayer;                 //dodajemy do tabeli

        }
    }
    private void giveNextCardToPlayer(int playerNumber, Player player, Card card){
        View.showCard (playerNumber, player, card);
        player.updatePlayerHand(card);
        View.render(tableOfPlayers,playerNumber);

    }
}
