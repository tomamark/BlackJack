package tmarkuszewski.mvc;

import tmarkuszewski.mvc.Card;

import java.util.LinkedList;
import java.util.List;
/*
* Karty w ręku gracza i ich status
* */
class Hand {
    private List<Card> handCards;    //lista kart ;
    private int handScore;          //wartość kart w zestawie;
    private HandStatus handStatus;


    Hand() {
        handCards = new LinkedList<>();
        handCards.clear();
        handScore = 0;
        handStatus = HandStatus.Normal;
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

        if (handScore > 21 && card.getValue()==11) {  //Jeśli przekroczylismy 21, ale mamy asa
            handScore -=10;                           // to jego wartość jest równa 1
        }
        /*
        * Od razu aktualizujemy status */
        if (handScore<21){                          // jeszcze OK
            handStatus = HandStatus.Normal;
        }
        if (handScore == 21){                       // oczko
            handStatus = HandStatus.BlackJack;
        }
        if (handScore>21){                          //fura
            handStatus = HandStatus.Busted;
            if (handScore == 22 && handCards.size() == 2){ //jesli 22 i mamy 2 asy to perskie oczko
                handStatus = HandStatus.Wink;
            }
        }

    }

    protected int getHandScore() {

        return handScore;
    }
    protected HandStatus getHandStatus(){
        return handStatus;
    }
}

