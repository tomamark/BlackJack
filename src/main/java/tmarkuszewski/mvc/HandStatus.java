package tmarkuszewski.mvc;

import java.util.Comparator;

/*
* Typ okreslający statusy kart gracza*/
public enum HandStatus implements Comparator<HandStatus> {
    Normal(1),                  //wynik <21
    BlackJack(2),               //      =21 (oko)
    Wink(3),                    //      =22 (perskie oko)
    Busted(-1);                 //      >21 (fura)
    private final int priority;

    HandStatus(int priority) {
        this.priority = priority;
    }

    /*
    * metoda nadpisana interfejsu Comparator
    * do porownywania wartości kart w ręku
    * */
    @Override
    public int compare(HandStatus hs1, HandStatus hs2) {
        return (hs1.priority - hs2.priority);
    }
}
