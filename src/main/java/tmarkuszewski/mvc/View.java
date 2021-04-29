package tmarkuszewski.mvc;

public class View {
    /*
    definicje kolorów
    * */
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    private static final String[] playerColor = {YELLOW,CYAN,GREEN,RED,BLUE};

    /*
    * Metoda render jest metodą przeciążaną.
    * 1. Bez argumentów wyświetla ekran startowy
    * 2. Jeśli argument jest typu String wyświetla pytanie o imie gracza
    * 3. Jeśli argument jest typu array(Player) wyświetla głowny ekran gry
    * */
    static void render(){
        clearScreen();
        System.out.println("Welcome to Black Jack game by TM");
        System.out.print("Enter number of players: 1-4 [1] ");
    }

    static void render (String playerName){
        System.out.print("Enter name of "+playerName+" ["+playerName+"]: ");
    }

    static void render (Player[] tableOfPlayers){
        String line = PURPLE+"Wins: ";
        Player player;
        for (int i = 0; i < tableOfPlayers.length; i++) {
            player = tableOfPlayers[i];
            line += getColoredPlayerName (player,i);
            line += player.getPlayerScore()+" | ";

        }
        System.out.println(line);
        for (int i = 0; i < tableOfPlayers.length; i++) {
            player = tableOfPlayers[i];
            line = getColoredPlayerName(player,i);
            System.out.println(line);
            int score = player.getPlayerScore();
            line = "Score: "+score;
            System.out.println(line);
            line = "Cards: ";
            if (score>0){
                line += getPlayerCards(player);
            }
            System.out.println(line);
        }

    }

   /* public static void render(int[] scoreboard, Player[] tableOfPlayers) {
        String line = PURPLE+"Wins: ";
        for (int i = 0; i < scoreboard.length; i++) {
            line += getColoredPlayerName (tableOfPlayers,i);
            line += scoreboard[i]+" | ";

        }
        System.out.println(line);

    }*/

    private static String getColoredPlayerName(Player player, int playerNumber) {

        return (playerColor[playerNumber]+player.getPlayerName()+"\t");
    }

    private static String getPlayerCards(Player player) {
        return (player.getPlayerHand());

    }
    /*
    * Metoda czyszcząca konsolę
    * pożyczona ze strony StackOverFlow*/
    /*private static void clearScreen(){
        System.out.println();
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (InterruptedException | IOException ex) {}
    }*/

    static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
