package tmarkuszewski.mvc;

import java.util.Scanner;

public class Controller {
    private int numberOfPlayers;
    private Player[] tableOfPlayers;

    public Controller(){


        View.render();                                      // Metoda render() bez argsów wyświetla ekran powitalny
        setNumberOfPlayers();                               // Pytamy o liczbę graczy
        tableOfPlayers = new Player[numberOfPlayers+1];     // Tworzymy tabelę graczy
        setPlayers();                                       // Tworzymy graczy
        View.render(tableOfPlayers);


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
        Player computerPlayer = new Player("Computer"); //tworzymy gracza dla komputera...
        tableOfPlayers[0] = computerPlayer; //... i dodajemy do tabeli pod indeks 0
        for (int i = 1; i <= numberOfPlayers ; i++) {
            String playerName = "Player "+i;
            View.render(playerName);
            String input = getStringFromConsole();
            if (!input.isBlank()) {
                playerName = input;
                if (playerName.length()>8){
                    playerName = playerName.substring(0,8); //ograniczamy długość do 8 znaków;
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
