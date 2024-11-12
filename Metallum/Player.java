import java.util.Random;
import java.util.Scanner;

public class Player {

    static final int A_ATTACK = 1;
	static final int B_ATTACK = 2;
	static final int A_DEFFEND = 3;

    private String name;
    private boolean victory = true;
    private int _ID;
    private int fights;
    private int wins;
    private int rounds;
    private boolean newPlayer = false;
    private final Character team[] = new Character[3];
    private int selection = 0, action = 0, deads = 0;
    private final Random Rd = new Random(System.nanoTime());
    private final Character characterTypes[] = new Character[] { 
        new Berserk(), new Werewolf(), new Wizard(), new Witcher(), 
        new Elf(), new Ent(), new Orc(), new Demon(), new Necromancer(), 
        new Summoner(), new Banshee(), new Spectrum() };

    public int getID() {
        return _ID;
    }
    public void setID(final int _ID) {
        this._ID = _ID;
    }

    public boolean getNewPlayer() {
        return newPlayer;
    }
    public void setNewPlayer(boolean newPlayer) {
        this.newPlayer = newPlayer;
    }

    public int getFights() {
        return fights;
    }
    public void setFights(final int fights) {
        this.fights = fights;
    }

    public int getWins() {
        return wins;
    }
    public void setWins(final int wins) {
        this.wins = wins;
    }

    public int getRounds() {
        return rounds;
    }
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }


    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public void chooseTeam() {

        final Scanner Sc = new Scanner(System.in);

        System.out.println("=======================================================================");

        for (int i = 0; i < characterTypes.length; i++) {
            final Character c = characterTypes[i];
            System.out.println("   " + (i + 1) + ". " + c.getTypeName() + "\t(hp:" + c.getHealth() + ", at: "
                    + c.getStrength() + ", def: " + c.getDefense() + "%)");
                    System.out.println("   " + c.getDescription() + "");
                    //System.out.println(" ");
            if (i != (characterTypes.length - 1)) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"); }
        }
        System.out.println("=======================================================================");

        for (int t = 0; t < team.length; t++) {
            do {
                System.out.print(name + ", choose your integrant " + (t + 1) + ": ");
                final int typeSelected = nextInt(Sc);
                try {
                    if(typeSelected > 0 && typeSelected <= characterTypes.length){
                        team[t] = (Character) characterTypes[typeSelected-1].clone();
                    }
                } catch (final CloneNotSupportedException e) {
                    System.err.println("The character " + characterTypes[t].getTypeName() + " is not cloneable");
                }

                if (team[t] == null) {
                    System.out.println("Warrior " + typeSelected + " does not exist...");
                    System.out.println("Try again with an existing one");
                }else{
                    // Check if the selected type was present in previous team integrants
                    for (int i = 0; i<t; i++) {
                        if(team[t].getTypeName() == team[i].getTypeName()){
                            System.out.println("You have already chosen this warrior!");
                            System.out.println("Choose another one");
                            team[t] = null;
                            break;
                        }
                    }
                }

                if(team[t] != null){
                    System.out.print("Baptize you warrior: ");
                    team[t].setName(Sc.nextLine());
                }
            } while (team[t] == null);
        }
    }
    public void changeTeam(final int t) {
        int option;
        final Scanner Sc = new Scanner(System.in);
        do {
            System.out.println("**********************************************************************");

            for (int i = 0; i < characterTypes.length; i++) {
                final Character c = characterTypes[i];
                System.out.println("*  " + (i + 1) + ". " + c.getTypeName() + "\t(hp:" + c.getHealth() + ", at: "
                        + c.getStrength() + ", def: " + c.getDefense() + "%)");
                        System.out.println("*  " + c.getDescription() + "");
                        System.out.println("*");
            }
            System.out.println("**********************************************************************");
            System.out.print("Which character you want to choose: ");
            option = nextInt(Sc);
            try {
                if(option > 0 && option <= characterTypes.length){
                    team[t] = (Character) characterTypes[option-1].clone();
                }
            } catch (final CloneNotSupportedException e) {
                System.err.println("The character " + characterTypes[t].getTypeName() + " is not cloneable");
            }
            if (option < 1 || option > 12) {
                System.out.println("Choose between 1 and 12!");
                team[t] = null;
            }
            for (int i = 0; i<t; i++) {
                if(team[t].getTypeName() == team[i].getTypeName()) {
                    System.out.println("You have already chosen this warrior!");
                    System.out.println("Choose another one");
                    team[t] = null;
                    option = 0;
                    break;
                }
            }
        } while (option < 1 || option > 12);
        deads++;
    }
    public void printTeam(String s) {
        System.out.println("======================================");
        for(int i = 0; i<team.length; i++) {
            if (team[i].getName() == null) {
                team[i].setName(team[i].getTypeName());
            }
            System.out.println("  " + (i+1) + ". (HP: " + team[i].getHealth() +") " + s + " " + team[i].getName());
        }
        System.out.println("======================================");
    }
    public void chooseWarrior() {
        final Scanner Sc = new Scanner(System.in);
        do {
            printTeam("");
            System.out.print(name + ", Choose a warrior to fight: ");
            selection = nextInt(Sc) - 1;
            if (selection < 0 || selection >= team.length) {
                System.out.println("You have to choose between 1, 2 or 3!");            
            }else if ((team[selection].getHealth() <= 0)) {
                System.out.println("Nice try! Dead bodies can't fight");
                System.out.println("Choose an alive warrior");
                selection = -1;
            }
        } while (selection < 0 || selection >= team.length);
        team[selection].setPlayTrue();
    }
    public void chooseAction() {

        final Scanner Sc = new Scanner(System.in);

        do {
            System.out.println( team[selection].getName() + " the " + 
                                team[selection].getTypeName());
            System.out.println("==========================");
			System.out.println("* 1. Attack");
            System.out.println(  "* 2. Special Attack (E: "+ team[selection].getEnergy() +")");
            System.out.println("* 3. Defend");
			System.out.println("==========================");
            System.out.print("What do you want to do?: ");
            action = nextInt(Sc);

            if ((action < 1)||(action >3)) {
                System.out.println("You have to choose between 1, 2 or 3!");  
            }
            if ((team[selection].getEnergy() < 3)&&(action == 2)) {
                System.out.println(team[selection].getName() + " doesn't have enought energy to perform a special attack!");
                action = 0;
            }
            if ((team[selection].getEnergy() == 3)&&(action == 2)) {
                team[selection].setEnergytoZero();
            }
        } while ((action < 1)||(action >3));
    }
    public void incrementEnergy() {
        for (int i = 0; i < team.length; i++) {
            team[i].setEnergy();
        }
        team[selection].setPlayFalse();
    }

    public int getSelection() {
        return selection;
    }
    public int getAction() {
        return action;
    }
    public Character getTeamCharacter(final int i) {
        return team[i];
    }
    public void getPrintAction() {
        if (action == A_ATTACK) {
            System.out.println("The " + team[selection].getTypeName() + " has attacked");
        } else if (action == B_ATTACK) {
            System.out.println("The " + team[selection].getTypeName() + " has done a charged attack");
        } else if (action == A_DEFFEND) {
            System.out.println("The " + team[selection].getTypeName() + " has defended");
        }
    }
    
    public void setRandomTeam() {     
        for (int t = 0; t < team.length; t++) {
            final int random = Rd.nextInt(11);
            final int typeSelected = random + 1;
            try {
                if(typeSelected > 0 && typeSelected <= characterTypes.length){
                    team[t] = (Character) characterTypes[typeSelected-1].clone();
                }
            } catch (final CloneNotSupportedException e) {
                System.err.println("The character " + characterTypes[t].getTypeName() + " is not cloneable");
            }
            for (int i = 0; i < t; i++) {
                do {
                    if(team[t].getTypeName() == team[i].getTypeName()){
                        team[t] = null;
                        try {
                            if(team[t] == null) {
                                team[t] = (Character) characterTypes[typeSelected].clone();
                            }
                        } catch (final CloneNotSupportedException e) {
                            System.err.println("The character " + characterTypes[t].getTypeName() + " is not cloneable");
                        }
                    } 
                } while (team[t] == null);
            }
        }
    }
    public void setRandomCharacter(int x) {
        do {
            final int random = Rd.nextInt(3);
            selection = random;
            if ((team[selection].getHealth() <= 0)) {
                selection = -1;
            } if (x==1) {
                System.out.println("Selection is: " + (random + 1));
            }
        } while (selection < 0 || selection >= team.length );
        team[selection].setPlayTrue();
    }
    public void setRandomAction(int x) {
        final int random = Rd.nextInt(3);
        do {
            action = random + 1;
            if ((team[selection].getEnergy() < 3)&&(action == 2)) {
                action = 1;
            }
            if ((team[selection].getEnergy() == 3)&&(action == 2)) {
                team[selection].setEnergytoZero();
            } if (x==1) {
                System.out.println("Action is: " + action); 
            }
        } while ((action < 1)||(action >3));
    }

    public int getDeads() {
        return deads;
    }
    public boolean checkDefeat() {
        int deads = 0;
        victory = false;
        for(int i = 0; i < team.length; i++) {
            if(team[i].getHealth() > 0){
                victory = true;
                deads = 0;
                break;
            } else {
                deads++;
            }
        } if(deads == 3) {
            this.deads = deads;
        }
        return !victory;
    }
    public void setDeadsToZero() {
        deads = 0;
    }
    public boolean isDefeated() {
        return !victory;
    }

    private static int nextInt(final Scanner Sc){
        int number = -1;
        try{
            number = Integer.parseInt(Sc.nextLine());
        } catch(final Exception e) {}
        return number;
    }
}