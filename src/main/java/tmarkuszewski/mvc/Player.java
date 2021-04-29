package tmarkuszewski.mvc;

import tmarkuszewski.mvc.Hand;

/*
*
* */
public class Player {

    private String playerName;          // nazwa gracza
    private int playerNumberOfWins;     //liczba wygranych partii
    private Hand playerHand;            //karty gracza
    private boolean isPlayerWinning;    //czy gracz prowadzi w partii
    private boolean hasFinished;


    protected Player(String playerName) {
        this.playerName = playerName;
        playerNumberOfWins = 0;
        playerHand = null;
        isPlayerWinning = false;
        hasFinished = false;
    }

    protected String getPlayerName() {
        return playerName;
    }

    protected String getPlayerHand() {
        return playerHand.getHandCards();
    }
    protected int getPlayerScore(){        //zwraca aktualny wynik gracza w partii
        if (playerHand == null){
            return 0;
        }
        return (playerHand.getHandScore());
    }

    public boolean getHasFinished() {
        return (hasFinished);
    }
    public void setHasFinished(boolean hasFinished){
        this.hasFinished = hasFinished;
    }

    /*protected void updatePlayerScore( cardValue) {
        playerHand.insertCardToHand(cardValue);
    }*/
}
