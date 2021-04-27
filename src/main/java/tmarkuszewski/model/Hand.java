package tmarkuszewski.model;

import java.util.LinkedList;
import java.util.List;

public class Hand {
    private List<Card> handCards;    //lista kart ;
    private int handScore;          //wartość kart w zestawie;

    public Hand() {
        handCards = new LinkedList<>();
        handCards.clear();
        handScore = 0;
    }

    public void insertCardToHand (Card card){
        handCards.add(card);
        handScore += card.getValue();
    }
}

