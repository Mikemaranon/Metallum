import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Dungeon {

	private static final int A_ATTACK = 1;
	private static final int B_ATTACK = 2;
	private static final int A_DEFFEND = 3;

    private Player player = new Player();
    private Player DUNGEON = new Player();
    private Scanner Sc = new Scanner(System.in);
    private boolean gamemodeFinished = true;
    private boolean roomVictory = false;
    private boolean win = true;
    private int room = 1, turn = 1;
    private int ps = player.getSelection();

    public void playDungeon() {

        if (!gamemodeFinished) {
            System.out.println("This gamemode is not available yet :(");
            System.out.println("Please try again when the next version is released");
        }
        else {
            System.out.println("===================================");
            System.out.println("      WELLCOME TO THE DUNGEON      ");
            System.out.println("===================================");
            System.out.println("");
            System.out.print("Player, insert your name: ");
            player.setName(Sc.nextLine());
            System.out.println("");
            System.out.println("========================================================================================");
            System.out.println("   In this mode you will select a 3 character team and 3 auxiliary characters.");
            System.out.println("   You will start fighting with the first 3 chosen and when one of them dies, that");
            System.out.println("   warrior will be substitued by another one, that new warrior will inherit his level.");
            System.out.println("   You will be allowed to repeat warriors up to two times...");
            System.out.println("   But you can repeat them on the same team, only in different teams.");
            System.out.println("   When your team gets reduced to 2 characters and there are no more auxiliary ones...");
            System.out.println("   your game will be over.");
            System.out.println("========================================================================================");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
            player.chooseTeam();
            
            printIntro(player);

            do {
                roomVictory = false;
                turn = 1;
                DUNGEON.setDeadsToZero();
                DUNGEON.setRandomTeam();
                for (int i = 0; i < 3; i++) {
                    DUNGEON.getTeamCharacter(i).setLevel(1*room);
                    DUNGEON.getTeamCharacter(i).setStrength(10*room);
                    DUNGEON.getTeamCharacter(i).setHealth(100*room);
                }  

                // SE IMPRIME LA HABITACIÓN
                printRoom(room);
                
                for (int i = 3; i > 0; i--) {
                    System.out.print(i + "... ");
                    sysSleep();
                }
                System.out.println("An enemy squad has appeared!");
                System.out.println("");
                sysSleep(); sysSleep();

                do {
                    clearScreen();
                    printRoom(room);
                    System.out.println("       TURN " + turn );
                    System.out.println("");

                    DUNGEON.printTeam("corrupted");
                    try {
                        sleep( 1000 );
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                    DUNGEON.setRandomCharacter(0);
                    //System.out.println("Debug: setRandomCharacter executed");
                    DUNGEON.setRandomAction(0);
                    //System.out.println("Debug: setRandomAction executed");
                    System.out.println("");
                    System.out.println("          VS        ");
                    System.out.println("");
                    //con esta funcion tambien se imprime el equipo
                    player.chooseWarrior();
                    player.chooseAction();
                    clearScreen();

                    DUNGEON.getPrintAction();
                    player.getPrintAction();
                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    executeAction(player, DUNGEON);
                    executeAction(DUNGEON, player);
                    
                    player.incrementEnergy();
                    DUNGEON.incrementEnergy();
                    
                    //DUNGEON.getTeamCharacter(DUNGEON.getSelection()).setHealth(-2000);
                    //prueba para que cada turno se vayan muriendo y asi probar la eficacia mas rapido

                    if (player.getDeads() >= 6) {
                        roomVictory = false;
                        win = false;
                    }
                    if (player.getTeamCharacter(ps).getHealth() <= 0) {

                        player.changeTeam(ps);
                        player.getTeamCharacter(ps).setLevel(room*1);
                        player.getTeamCharacter(ps).setStrength(room*10);
                        player.getTeamCharacter(ps).setHealth(room*100);
                    }
                    //clearScreen(); 
                    DUNGEON.checkDefeat();
                    if (DUNGEON.getDeads() == 3) {
                        roomVictory = true;
                    }
                    turn++;
                } while (roomVictory == false);
                room++;
                System.out.println("Congrats! you have passed this room!");
                for (int i = 0; i < 3; i++) {
                    player.getTeamCharacter(i).setLevel(1);
                    player.getTeamCharacter(i).setStrength(10);
                    player.getTeamCharacter(i).setHealth(100);
                }
                System.out.println("Press intro to enter next room: ");
                Sc.nextLine();
                clearScreen();
                for (int t = 3; t > 0; t--) {
                    System.out.println("Entering in : " + t);
                    try {
                        sleep( 1000 );
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
            } while (win);
            System.out.println("You have been defeated :(");
            System.out.println("You have endured " + (room - 1) + " rooms...");
        }
    }

    private void executeAction(Player active, Player passive) {

		Character w1 = active.getTeamCharacter(active.getSelection());
		Character w2 = passive.getTeamCharacter(passive.getSelection());

		if (active.getAction() == A_ATTACK){
			// If the other warrior is defending recieves special damage
			if(passive.getAction() == A_DEFFEND){
				w2.getDefended(w1.getStrength());
			} else if (passive.getAction() == A_ATTACK ||
                       passive.getAction() == B_ATTACK) {
				w2.getHarmed(w1.getStrength());
			} 
            
            /*else if (passive.getAction() == B_ATTACK){
				w2.getHarmed2(w1.getStrength(), w1.getPower());
			}*/
		}
		if (active.getAction() == B_ATTACK){
			// If the other warrior is defending recieves special damage
			if(passive.getAction() == A_DEFFEND){
				w2.getDefended2(w1.getStrength(), w1.getPower());
			} else if (passive.getAction() == A_ATTACK ||
                       passive.getAction() == B_ATTACK) {
				w2.getHarmed2(w1.getStrength(), w1.getPower());
			} 
            
            /* else if (passive.getAction() == B_ATTACK){
				w2.getHarmed2(w1.getStrength(), w1.getPower());
			} */
		}
    }
    public void printIntro(Player player) {
        clearScreen();
        System.out.println(player.getName() + ", the legendary warrior, has received a request..."); sysSleep();
            System.out.println("A Dungeon needs to be cleansed from corrupted monsters... "); sysSleep();
            System.out.println("Will the chosen one have the strength to do it... "); sysSleep();
            System.out.println("");
            System.out.println("With the help of... "); sysSleep(); 
            System.out.println(player.getTeamCharacter(0).getName() + ", the " + 
                             player.getTeamCharacter(0).getTypeName() + "... "); 
            sysSleep(); sysSleep();
            System.out.println(player.getTeamCharacter(1).getName() + ", the " +
                             player.getTeamCharacter(1).getTypeName() + "... "); 
            sysSleep(); sysSleep(); 
            System.out.println("And " + player.getTeamCharacter(2).getName() + ", the " +
                            player.getTeamCharacter(2).getTypeName() + "... "); 
            sysSleep(); sysSleep(); 
            
            System.out.println("");
            System.out.println("Only the fate will know...");
            sysSleep(); sysSleep(); 
            for (int i = 3; i > 0; i--) {
                    System.out.print(i + "... ");
                    sysSleep();
            }
        clearScreen();
    }
    public void printRoom(int room) {
        System.out.println("===================================");
        System.out.println(  "         ROOM " + room + "     ");
        System.out.println("===================================");
        System.out.println("");
    }
    
    public static void clearScreen() {  
        // Es necesario introducir un delay para que dé tiempo a ver lo que pasa en la pantalla,
        // antes de borrarla. En caso contrario, no se puede ver nada de lo que pasa.
        try {
            sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
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