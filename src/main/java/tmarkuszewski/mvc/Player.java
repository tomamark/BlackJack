package tmarkuszewski.mvc;


/*
*
* */
public class Player {

    private final String playerName;          // nazwa gracza
    private int playerNumberOfWins;     //liczba wygranych partii
    private Hand playerHand;            //karty gracza
    private boolean hasFinished;

    /*
    * Konstruktor
    * */
    Player(String playerName) {
        this.playerName = playerName;
        playerNumberOfWins = 0;
        playerHand = new Hand();
        hasFinished = false;
    }

    String getPlayerName() {
        return playerName;
    }

    String getPlayerHand() {
        return playerHand.getHandCards();
    }

    String getPlayerStatus(){
        HandStatus status = playerHand.getHandStatus();
        String result = "";
        switch (status){
            case Normal -> {
                result = (hasFinished)? "Passed":"Still in play...";
            }
            case BlackJack -> {
                result =  "BlackJack !!!";
            }
            case Wink -> {
                result ="Wink!!!! Wooow!!!";
            }
            case Busted -> {
                result = "Oh man... you are busted! :-(";
            }
        }
        return result;
    }

    /*
    * Pobiera status gracza
    * */
    HandStatus getPlayerHandStatus(){
        return (playerHand.getHandStatus());
    }

    /*
    * Pobiera punkty gracza
    * */
    int getPlayerScore(){        //zwraca aktualny wynik gracza w partii

        return (playerHand.getHandScore());
    }

    /*
    * Czy gracz zakończył partię
    * */
    boolean getHasFinished() {
        return (hasFinished);
    }

    /*
    * Ustawia zakończenie partii przez gracza
    * */
    void setHasFinished(boolean hasFinished){
        this.hasFinished = hasFinished;
    }

    /*
    * Uaktualnia karty i status gracza
    * */
    void updatePlayerHand(Card cardForPlayer) {
        playerHand.insertCardToHand(cardForPlayer);                 //dodajemy kartę do ręki gracza
        if (!(playerHand.getHandStatus() == HandStatus.Normal)) {  //sprawdzamy czy jescze moze grać
            hasFinished = true;
        }
    }

    /*
    * Pobiera liczbe zwycięstw
    * */
    int getPlayerNumberOfWins() {
        return playerNumberOfWins;
    }

    /*
    * Zwiększa liczbę zwycięstw o 1
    * */
    void incrementPlayerNumberOfWins() {
        this.playerNumberOfWins++;
    }

    /*
    * Przywraca gracza do ustawień począt.
    * */
    void reset() {
        this.hasFinished = false;
        this.playerHand = new Hand();
    }

}
