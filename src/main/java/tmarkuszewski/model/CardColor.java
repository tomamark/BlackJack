package tmarkuszewski.model;
/*
* Typ wyliczeniowy def. kolory kart w talii
* */

public enum CardColor {
    Hearts ("H"),                     //kier
    Diamonds("D"),                   //karo
    Spades("S"),                     //pik
    Clubs("C");                      //trefl

    private String cardColorSymbol;

    CardColor(String cardColorSymbol) {
        this.cardColorSymbol = cardColorSymbol;
    }

    public String toString(){
        return cardColorSymbol;
    }
}
