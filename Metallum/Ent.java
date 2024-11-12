public class Ent extends Character {

    public Ent () {
        super(200, 2, 2200, 60, "Ent");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Sylvanus";
    }
    @Override
    public String getDescription() {
        return "It is an ent...                                                 ";
    }
}