package tmarkuszewski.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private int numberOfPlayers;
    private final Player[] tableOfPlayers; // tablica z graczami


    public Controller(){
    /*
    *   Ustawienia startowe gry
    *  */


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
            resetPlayers(tableOfPlayers);
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

            /*
            * Teraz do akcji rusza komputer z obmyslona swoja strategia w klasie Strategy
            *
            * */

            Strategy computerStrategy = new Strategy(tableOfPlayers);
            String strategy = computerStrategy.toString();
            Player computer = tableOfPlayers[0];
            View.render(tableOfPlayers,0);
            do {
                Card cardForPlayer = deck.getCardFromDeck();                    // Pobieramy kartę dla komputera
                giveNextCardToPlayer(0, computer, cardForPlayer);   //aktualizujemy jego karty
                //View.render(tableOfPlayers,0);
                if (!computer.getHasFinished()){                                // Jesli komputer nie skonczył
                    //View.render(tableOfPlayers,0);
                    boolean decision = computerStrategy.computerPlay(computer); // to decyduje czy grać dalej,
                    View.computerSay (decision,strategy);                                // wyświetla informację,
                    computer.setHasFinished(!decision);                         // aktualizuje pole hasFinished
                }

            }while(!computer.getHasFinished());                                 // aż komputer skończy grę

            View.render(tableOfPlayers,-1);



            /*
            * Wybór zwycięzcy
            * */

            List<Integer>listOfWinners = getListOfWinners (tableOfPlayers);
            updatePlayersWins(listOfWinners,tableOfPlayers);
            View.showWinners (listOfWinners,tableOfPlayers);



         }while (playAgain());



    }

    /*
    * Przywracanie wartości domyślnych graczy
    * */
    private void resetPlayers(Player[] tableOfPlayers) {
        for (Player player : tableOfPlayers) {
            player.reset();
        }
    }

    /*
    * Pytanie czy następna gra
    * */
    private boolean playAgain() {
        View.showNextGameQuestion();
        boolean validAnswer = false;
        String answer;
        while (!validAnswer) {
            answer = getStringFromConsole().toUpperCase();
            if (answer.equals("N") || answer.equals("NEXT")) {
                validAnswer = true;
                return true;
            }
            if (answer.equals("Q") || answer.equals("QUIT")) {
                validAnswer = true;
                return false;
            }
        }
        return false;

    }


    /*
    * Aktulizacja liczby zwycięstw
    * */
    private void updatePlayersWins(List<Integer> listOfWinners, Player[] tableOfPlayers) {
        for (int number:listOfWinners) {
            tableOfPlayers[number].incrementPlayerNumberOfWins();
        }
    }


    /*
    * Ustalanie listy zwycięzców
    * */
    private List<Integer> getListOfWinners(Player[] tableOfPlayers) {
       List<Integer> listOfWinners = new ArrayList<>();
       HandStatus maxStatus = HandStatus.Normal;
       int maxScore = 0;
        for (int i = 0; i < tableOfPlayers.length; i++) {
            Player player = tableOfPlayers[i];
            HandStatus playerStatus = player.getPlayerHandStatus();

            if (playerStatus.equals(maxStatus)){             //W przypadku gdy statusy są równe porównujemy punkty
                if (player.getPlayerScore() > maxScore){    // jesli wiecej punktow to czyscimy liste
                    listOfWinners.clear();
                    listOfWinners.add(i);               //dodajemy aktualny numer do listy
                    maxScore = player.getPlayerScore(); // aktualizujemy max wynik
                }
                if (player.getPlayerScore() == maxScore){ // jesli statusy i punkty rowne to remis
                    if (!listOfWinners.contains(i)) listOfWinners.add(i);
                }

            }
            if (playerStatus.compare(playerStatus,maxStatus) > 0){   // Porównujemy statusy kart graczy
                listOfWinners.clear();                              // Jesli aktualny jest wyzszy czyścimy liste
                listOfWinners.add(i);                               // dodajemy nr aktualny gracza do listy
                maxStatus = playerStatus;                           // aktualizujemy max status
                maxScore = player.getPlayerScore();                 // i punkty
            }
        }

        return listOfWinners;
    }

    /*
    * Pytanie gracza o decyzję
    * */
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

    /*
    * Metoda do pobierania odpowiedzi graczy
    * */
    private String getStringFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    /*
    * Metoda do walidacji liczby graczy
    * */
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

    /*
    * Tworzenie tabeli z graczami
    * */
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

    /*
    * Dodawanie karty do "ręki" gracza
    * */
    private void giveNextCardToPlayer(int playerNumber, Player player, Card card){
        View.showCard (playerNumber, player, card);
        player.updatePlayerHand(card);
        View.render(tableOfPlayers,playerNumber);

    }
}
