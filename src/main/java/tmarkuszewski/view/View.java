package tmarkuszewski.view;

import java.io.IOException;

public class View {
    public static void render(){
        clearScreen();

    }
    /*
    * Metoda czyszcząca konsolę
    * pożyczona ze strony StackOverFlow*/
    private static void clearScreen(){
        System.out.println();
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (InterruptedException | IOException ex) {}
    }
}
