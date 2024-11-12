public class Wizard extends Character {
    
    public Wizard () {
        super(500, 2, 900, 50, "Wizard");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Yennefer";
    }
    @Override
    public String getDescription() {
        return "a powerful being who dominates all magic ... but physically weak";
    }
}