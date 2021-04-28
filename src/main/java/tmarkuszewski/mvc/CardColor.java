package tmarkuszewski.mvc;
/*
* Typ wyliczeniowy def. kolory kart w talii
* */

enum CardColor {
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
