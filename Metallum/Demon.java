public class Demon extends Character {
    
    public Demon () {
        super(300, 2, 1400, 80, "Demon");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Kahl Makyr";
    }
    @Override
    public String getDescription() {
        return "against any perversion that may exist ... we will send only him";
    }
}