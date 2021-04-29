package tmarkuszewski.mvc;

import java.util.Scanner;

public class Controller {

    private int numberOfPlayers;
    private final Player[] tableOfPlayers; // tablica z graczami


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
        * Główna pętla programu
        * wykonuje się aż gracze nie zdecydują o wyjściu z gry
        * */
        do {
            View.clearScreen();
            View.render(tableOfPlayers);
            Deck deck = new Deck();             //
            /*
            * Pętla for ... powtarza czynności dla każdego gracza
            * */
            for (int i = 1 ; i <numberOfPlayers ; i++) {
                Player player = tableOfPlayers[i];
                do {


                }while (!player.getHasFinished());
            }


         }while (playAgain);



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

    private String getStringFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
