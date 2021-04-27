package tmarkuszewski.model;
/*
* Klasa opisująca kartę do gry
* */
public class Card {
private CardColor cardColor;        //kolor karty
private CardType cardType;          //"typ" karty (2,3,....,J,D,K,A)

    public Card(CardColor cardColor, CardType cardType) {
        this.cardColor = cardColor;
        this.cardType = cardType;
    }

    /*
    * pobiera wartość liczbową karty*/
    public int getValue() {
        return cardType.getCardValue();
    }

    /*
    * pobiera "rysunek" karty*/
    @Override
    public String toString() {
        return "[" + cardType.toString()+cardColor.toString()+ "]";
    }
}
