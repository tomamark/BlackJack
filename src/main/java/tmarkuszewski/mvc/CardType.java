package tmarkuszewski.mvc;

enum CardType {
    N2 (2,"2"),     //Dwójka
    N3 (3,"3"),
    N4 (4,"4"),
    N5 (5,"5"),
    N6 (6,"6"),
    N7 (7,"7"),
    N8 (8,"8"),
    N9 (9,"9") ,
    N10 (10,"10"),
    J (10,"J"),
    D (10,"D"),
    K (10,"K"),
    A (11,"A");      //As

    private int cardValue;
    private String cardSymbol;

    CardType(int cardValue, String cardSymbol) {
        this.cardValue = cardValue;
        this.cardSymbol = cardSymbol;
    }

    protected int getCardValue() {
        return cardValue;
    }

    public String toString() {
        return cardSymbol;
    }
}

