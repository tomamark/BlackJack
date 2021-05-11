package tmarkuszewski.mvc;
/*
* Do testowania strategii
* */
public class Test {
    public static void main (String[] args){
        Player[] top = new Player[5];
        Player p = new Player("comp");
        top[0] = p;
        for (int i = 1; i < 5 ; i++) {
            p = new Player("P"+i);
            top[i] = p;
        }

        Card cA = new Card(CardColor.Clubs,CardType.A);
        Card cJ = new Card(CardColor.Clubs,CardType.J);
        Card cA2 = new Card(CardColor.Diamonds,CardType.A);
        Card c2 = new Card(CardColor.Clubs, CardType.N2);
        Card c7 = new Card(CardColor.Clubs, CardType.N7);
        Card c10 = new Card(CardColor.Clubs, CardType.N10);

        top[1].updatePlayerHand(cA2);
        top[1].updatePlayerHand(cA);
        top[1].updatePlayerHand(c7);

        //top[2].updatePlayerHand(cA);
        top[2].updatePlayerHand(c10);
        top[2].updatePlayerHand(c7);

        top[3].updatePlayerHand(cJ);
        top[3].updatePlayerHand(cA);
       // top[3].updatePlayerHand(cJ);

        top[4].updatePlayerHand(cJ);
        top[4].updatePlayerHand(c7);
        top[4].updatePlayerHand(c7);

        Strategy s = new Strategy(top);
        System.out.println(s.toString());
    }
}
