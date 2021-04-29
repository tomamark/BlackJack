package tmarkuszewski.mvc;

import tmarkuszewski.mvc.Card;

import java.util.LinkedList;
import java.util.List;

class Hand {
    private List<Card> handCards;    //lista kart ;
    private int handScore;          //wartość kart w zestawie;

    Hand() {
        handCards = new LinkedList<>();
        handCards.clear();
        handScore = 0;
    }

    protected String getHandCards() {
        String line = "";
        if (!handCards.isEmpty()){
            for (Card card: handCards) {
                line+=card.toString();
                line+=" ";
            }
        }

        return (line);
    }

    void insertCardToHand(Card card){
        handCards.add(card);
        handScore += card.getValue();
    }

    protected int getHandScore() {

        return handScore;
    }
}

