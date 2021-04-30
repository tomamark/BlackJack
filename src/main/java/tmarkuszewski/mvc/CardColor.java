package tmarkuszewski.mvc;
/*
* Typ wyliczeniowy def. kolory kart w talii
* */

enum CardColor {
    Hearts ("\u2661"),                     //kier
    Diamonds("\u2662"),                   //karo
    Spades("\u2664"),                     //pik
    Clubs("\u2663");                      //trefl

    private String cardColorSymbol;

    CardColor(String cardColorSymbol) {
        this.cardColorSymbol = cardColorSymbol;
    }

    public String toString(){
        return cardColorSymbol;
    }
}
