import java.util.Scanner;
import java.util.Random;
import static java.lang.Thread.sleep;

public class ClassicMultiplayer {

	private static final int A_ATTACK = 1;
	private static final int B_ATTACK = 2;
	private static final int A_DEFFEND = 3;

	private Player p1 = new Player();
	private Player p2 = new Player();
	private Scanner Sc = new Scanner(System.in);
	private int turn = 1;
	private final Random Rd = new Random(System.nanoTime());
	private final String titles[] = new String[] {
		"The bloodthirster", "The great", "The Burd copy of Ryan Gosling", 
		"The god-killer", "The terrorific", "The splendid", "The fearless",
		"The overweight (im dixelyc)", "The... The... hum... idk man", 
		"The Authistic", "The precious (Slay)", "Zote's apprentice", "The real RYAN GOSLIN"};

	public void playClassicMultiplayer() {

		clearScreen();

		System.out.println("====================================");
		System.out.println("    WELLCOME TO MULTIPLAYER MODE ");
		System.out.println("====================================");
	
		System.out.print("Player 1, insert you name: ");
		p1.setName(Sc.nextLine());
		p1.chooseTeam();

		clearScreen();

		System.out.print("Player 2, insert you name: ");
		p2.setName(Sc.nextLine());
		p2.chooseTeam();

		clearScreen();
		printIntro(p1, p2, titles, Rd);

		System.out.println("");
		System.out.println("Lets begin the battle!!!");
		sysSleep();

		do {
			clearScreen();
			System.out.println("        TURN " + turn + " ");
			System.out.println("");
			p1.chooseWarrior();
			p1.chooseAction();

			p2.chooseWarrior();
			p2.chooseAction();

			clearScreen();
			
			p1.getPrintAction();
			p2.getPrintAction();
			sysSleep();
			
			// Execute action from player 1 to player 2 and the other way around
			executeAction(p1, p2);
			executeAction(p2, p1);
			
			p1.incrementEnergy();
			p2.incrementEnergy();
			clearScreen();

			if (p1.checkDefeat()) {
				System.out.println(p1.getName() + " has been humiliated...");
				System.out.println("All his warriors are dead...");
				System.out.println(p2.getName() + " has won the great metallum battle!!!");
			}
			if (p2.checkDefeat()) {
				System.out.println(p2.getName() + " has been humiliated...");
				System.out.println("All his warriors are dead...");
				System.out.println(p1.getName() + " has won the great metallum battle!!!");
			}
			// Move to next turn
			turn++;
		} while (!p1.isDefeated() && !p2.isDefeated());
		Sc.close();
	}

	private static void executeAction(Player active, Player passive) {

		Character w1 = active.getTeamCharacter(active.getSelection());
		Character w2 = passive.getTeamCharacter(passive.getSelection());

		if (active.getAction() == A_ATTACK){
			// If the other warrior is defending receives special damage
			if(passive.getAction() == A_DEFFEND){
				w2.getDefended(w1.getStrength());
			} else if (passive.getAction() == A_ATTACK){
				w2.getHarmed(w1.getStrength());
			} else if (passive.getAction() == B_ATTACK){
				w2.getHarmed2(w1.getStrength(), w1.getPower());
			}
		}
		if (active.getAction() == B_ATTACK){
			// If the other warrior is defending receives special damage
			if(passive.getAction() == A_DEFFEND){
				w2.getDefended(w1.getStrength());
			} else if (passive.getAction() == A_ATTACK){
				w2.getHarmed(w1.getStrength());
			} else if (passive.getAction() == B_ATTACK){
				w2.getHarmed2(w1.getStrength(), w1.getPower());
			}
		}
	}

	public static void sysSleep() {
        try {
            sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } 
    }
	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}  

	public void printIntro(Player p1, Player p2, String t[], Random rd) {
		final int i = rd.nextInt(11);
		final int j = rd.nextInt(11);
		System.out.println(p1.getName() + " " + t[i] + " Is entering the arena...");
		sysSleep();
		System.out.println("With the help of...");sysSleep(); sysSleep();
		printTeamPresent(p1);
		System.out.println("To fight...");sysSleep(); sysSleep();
		System.out.println(p2.getName() + " " + t[j] + "...");
		System.out.println("With the help of...");sysSleep(); sysSleep();
		printTeamPresent(p2);
	}
	public void printTeamPresent(Player p) {
		System.out.println(p.getTeamCharacter(0).getName() + ", the " + 
                             p.getTeamCharacter(0).getTypeName() + "... "); 
            sysSleep(); sysSleep();
            System.out.println(p.getTeamCharacter(1).getName() + ", the " +
                             p.getTeamCharacter(1).getTypeName() + "... "); 
            sysSleep(); sysSleep(); 
            System.out.println("And " + p.getTeamCharacter(2).getName() + ", the " +
                            p.getTeamCharacter(2).getTypeName() + "... "); 
            sysSleep(); sysSleep(); 
	}
}