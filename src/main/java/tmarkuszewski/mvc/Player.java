package tmarkuszewski.mvc;

import tmarkuszewski.mvc.Hand;

/*
*
* */
public class Player {

    private String playerName;          // nazwa gracza
    private int playerScore;            //wynik gracza w danej partii
    private int playerNumberOfWins;     //liczba wygranych partii
    private Hand playerHand;            //karty gracza
    private boolean isPlayerWinning;    //czy gracz prowadzi w partii


    protected Player(String playerName) {
        this.playerName = playerName;
        playerScore = 0;
        playerNumberOfWins = 0;
        playerHand = null;
        isPlayerWinning = false;
    }

    protected String getPlayerName() {
        return playerName;
    }
}
