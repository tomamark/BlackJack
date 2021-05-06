package tmarkuszewski.mvc;


import java.util.LinkedList;
import java.util.List;
/*
* Karty w ręku gracza i ich status
* */
class Hand {
    private final List<Card> handCards;    //lista kart ;
    private int handScore;          //wartość kart w zestawie;
    private HandStatus handStatus;

    /*
    * Konstruktor
    * */
    Hand() {
        handCards = new LinkedList<>();
        handCards.clear();
        handScore = 0;
        handStatus = HandStatus.Normal;
    }

    /*
    * Zwraca łańcuch z symbolami kart w ręku
    * */
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


    /*
    * Dodaje karty do "ręki"
    * */
    void insertCardToHand(Card card){
        handCards.add(card);
        handScore += card.getValue();

        if (handScore > 21 && card.getValue()==11) {  //Jeśli przekroczylismy 21, ale dostalismy asa
            if (!(handCards.size() == 2)) {             // (sprawdzamy czy to nie 2 asy)
                handScore -= 10;                         // to jego wartość jest równa 1
            }
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

