package tmarkuszewski.model;

import java.util.Collections;
import java.util.Stack;

/*
* Klasa opisująca talię kart
* */
public class Deck {
  Stack<Card> playingCards;
  int numberOfCardsLeft;

    public Deck() {
        playingCards = new Stack<>();
        generateDeck();

    }


    /*
    Generuje talię kart (każdy kolor z każdą figurą)
    i dodaje do stosu. Potem tasuje karty
    * */
    private void generateDeck(){
        numberOfCardsLeft=0;
        Card card;
        for (CardColor cardColor:CardColor.values()) {
            for (CardType cardType: CardType.values()) {
              card = new Card(cardColor,cardType);
              playingCards.add(card);
              numberOfCardsLeft++;
            }
        }
        Collections.shuffle(playingCards);
    }
    /*
    * Pobiera kartę ze stosu
    * */
    public Card getCardFromDeck(){
        if (numberOfCardsLeft>0){
            numberOfCardsLeft--;
            return playingCards.pop();
        }
        else {
            return null;
        }
    }
}
