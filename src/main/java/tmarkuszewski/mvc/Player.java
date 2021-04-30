package tmarkuszewski.mvc;

import tmarkuszewski.BlackJack;
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
        playerHand = new Hand();
        isPlayerWinning = false;
        hasFinished = false;
    }

    protected String getPlayerName() {
        return playerName;
    }

    protected String getPlayerHand() {
        return playerHand.getHandCards();
    }
    protected String getPlayerStatus(){
        HandStatus status = playerHand.getHandStatus();
        String result = "";
        switch (status){
            case Normal -> {
                result = (hasFinished)? "Passed":"Still in play...";
                break;
            }
            case BlackJack -> {
                result =  "BlackJack !!!";
            break;
            }
            case Wink -> {
                result ="Wink!!!! Wooow!!!";
            break;
            }
            case Busted -> {
                result = "Oh man... you are busted! :-(";
                break;
            }
        }
        return result;
    }
    protected int getPlayerScore(){        //zwraca aktualny wynik gracza w partii

        return (playerHand.getHandScore());
    }

    protected boolean getHasFinished() {
        return (hasFinished);
    }
    public void setHasFinished(boolean hasFinished){
        this.hasFinished = hasFinished;
    }

    public void updatePlayerHand(Card cardForPlayer) {
        playerHand.insertCardToHand(cardForPlayer);                 //dodajemy kartę do ręki gracza
        if (!(playerHand.getHandStatus() == HandStatus.Normal)) {  //sprawdzamy czy jescze moze grać
            hasFinished = true;
        }
    }

    public int getPlayerNumberOfWins() {
        return playerNumberOfWins;
    }

    public void setPlayerNumberOfWins(int playerNumberOfWins) {
        this.playerNumberOfWins = playerNumberOfWins;
    }
    /*protected void updatePlayerScore( cardValue) {
        playerHand.insertCardToHand(cardValue);
    }*/
}
