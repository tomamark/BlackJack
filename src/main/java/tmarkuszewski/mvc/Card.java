package tmarkuszewski.mvc;

/*
* Klasa opisująca kartę do gry
* */
public class Card {
private final CardColor cardColor;        //kolor karty
private final CardType cardType;          //"typ" karty (2,3,....,J,D,K,A)

    Card(CardColor cardColor, CardType cardType) {
        this.cardColor = cardColor;
        this.cardType = cardType;
    }

    /*
    * pobiera wartość liczbową karty
    * */
    int getValue() {
        return cardType.getCardValue();
    }

    /*
    * pobiera "rysunek" karty
    * */
    @Override
    public String toString() {
        return "[" + cardType.toString()+cardColor.toString()+ "]";
    }
}
