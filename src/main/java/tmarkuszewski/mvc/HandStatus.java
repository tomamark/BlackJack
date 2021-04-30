package tmarkuszewski.mvc;
/*
* Typ okreslający statusy kart gracza*/
public enum HandStatus {
    Normal(1),                  //wynik <21
    BlackJack(2),               //      =21 (oko)
    Wink(3),                    //      =22 (perskie oko)
    Busted(-1);                 //      >21 (fura)
    private int priority;

    HandStatus(int priority) {
        this.priority = priority;
    }
}
