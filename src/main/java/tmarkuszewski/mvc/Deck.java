package tmarkuszewski.mvc;


import java.util.Collections;
import java.util.Stack;

/*
* Klasa opisująca talię kart
* */
class Deck {
    Stack<Card> playingCards;   // Stos kart
    int numberOfCardsLeft;

        Deck() {
            playingCards = new Stack<>();
            generateDeck();

    }


    /*
    Generuje talię kart (miesza każdy kolor z każdą figurą)
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
        Collections.shuffle(playingCards); // Tasuje stos (kolekcję)
    }
    /*
    * Pobiera kartę ze stosu
    * */
    Card getCardFromDeck(){
        if (numberOfCardsLeft>0){
            numberOfCardsLeft--;
            return playingCards.pop();
        }
        else {
            return null;
        }
    }
}
