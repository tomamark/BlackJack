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

    void insertCardToHand(Card card){
        handCards.add(card);
        handScore += card.getValue();
    }
}

