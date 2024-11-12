public abstract class Character implements Cloneable {

    protected int strength;
    protected int power;
	protected int health;
    protected int defense;
    protected int energy = 0;
    protected String name;
    protected boolean play = false;
    protected int level = 1;
    protected String typeName;

    protected Character(int strength, int power, int health, int defense, String typeName) {
        this.strength = strength;
        this.power = power;
        this.health = health;
        this.defense = defense;
        this.typeName = typeName;
    }

    public String getName() {
        if (name == null) {
            return typeName;
        } else {
            return name;
        }
        
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHarmed(int harm) {
        health = health - harm;
        return health;
    }
    public int getHarmed2(int harm, int power) {
        health = health - harm*power;
        return health;
    }
    public int getDefended(int harm) {
        health = health - (100-defense)*harm; //100 - 50% de 40
        return health;
    }
    public int getDefended2(int harm, int power) {
        health = health - (100-defense)*harm*power; //100 - 50% de 40
        return health;
    }

    public void setEnergy() {
        if (!play && (energy < 3)) {
            energy++;
        }
    }
    public void setEnergytoZero() {
        energy = 0;
    }
    public int getEnergy() {
        return energy;
    }
    public void setPlayTrue() {
        play = true;
    }
    public void setPlayFalse() {
        play = false;
    }
    
	public int getStrength() {
        return strength;
    }
    public int getPower() {
        return power;
    }
    public int getHealth() {
        if (health <= 0) {
            return 0;
        } else {
            return health;
        }
    }
    public int getDefense() {
        return defense;
    }

    public void setStrength(int extra) {
        strength = strength + extra;
    }
    public void setHealth(int extra) {
        health = health + extra;
    }
    public void setLevel(int extra) {
        level = level + extra;
    }

    public abstract String getTypeName();
    public abstract String getGod();
    public abstract String getDescription();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}