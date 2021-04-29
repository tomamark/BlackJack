package tmarkuszewski.mvc;
/*
* Klasa przechowująca stan gry:
* - gracze,
* - talia kart
* - tabela
* - tabela wyników
*
* */
public class Game {
     boolean isGameFinished;
     private Player[] players;
     private int[] scoreboard;
     private Deck deck;

     Game (Player[] players){
         this.players = players;
         isGameFinished = false;
         scoreboard = new int[players.length];
         deck = new Deck();

     }

     /*void updateScoreboard (int cardValue, int playerNumber){
         players[playerNumber].updatePlayerScore(cardValue);
     }*/

}
