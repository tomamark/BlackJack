package tmarkuszewski;

import tmarkuszewski.model.Card;
import tmarkuszewski.model.CardColor;
import tmarkuszewski.model.CardType;
import tmarkuszewski.model.Deck;

import java.util.Collection;

public class Test {
    public static void main (String[] args){
        Card c1 = new Card(CardColor.Hearts, CardType.D);
        System.out.println(c1.toString());
        Deck d1 = new Deck();

        c1 = d1.getCardFromDeck();
        //System.out.println(d1);
        while (c1 != null){
            System.out.println(c1.toString());
            c1 = d1.getCardFromDeck();
        }
    }
}
