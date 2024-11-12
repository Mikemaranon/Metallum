import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Metallum {
    public static void main(String[] args) throws IOException {
 
        int option;
        Scanner Sc = new Scanner(System.in);
        ClassicMultiplayer playClassicMultiplayer = new ClassicMultiplayer();
        Dungeon playDungeon = new Dungeon();

        sysSleep();

        do {
                // Es necesario introducir un delay para que dé tiempo a ver lo que pasa en la pantalla,
                // antes de borrarla. En caso contrario, no se puede ver nada de lo que pasa.
                sysSleep();
                // clearScreen();
                
                System.out.println("* WELLCOME TO METALLUM *");
                System.out.println("");
                System.out.println("=======================");
                System.out.println("|   1. Multiplayer    |");
                System.out.println("|   2. Dungeon        |");
                System.out.println("|   3. Quit game      |");
                System.out.println("=======================");
                System.out.print("Select an option: ");
                try {
                        option = Sc.nextInt();
                        System.out.println("");
                        System.out.println("========================");
                        System.out.println("   Option selected: " + option);
                        System.out.println("========================");

                } catch (NoSuchElementException e) {
                    System.out.println("Introduced input is incorrect; please introduce a number");
                    Sc.next(); // Para leer la opción errónea y descartarla
                    option = 0;
                } finally { }

                for (int i = 3; i > 0; i--) {
                    System.out.print(i + "... ");
                    sysSleep();
                } clearScreen();
                
                switch (option) {
                    // case 0: System.out.println("option vale 0"); break;
                    case 1: playClassicMultiplayer.playClassicMultiplayer();
                            break;
                    case 2: playDungeon.playDungeon();
                            break;
                    case 3: System.out.println("Thanks for playing! c ya next time");
                            break;
                    default: System.out.println(option + " is not an option, please try again...");
                            break;
                }
            } while (option != 3);
            Sc.close();
    }
    public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
    }
    public static void sysSleep() {
        try {
            sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } 
    }
}   