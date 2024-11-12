public class Berserk extends Character {

    public Berserk () {
        super(300, 3 , 1800, 70, "Berserk");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Guts";
    } 
    @Override
    public String getDescription() {
        return "A savage warrior hungry for blood                               ";
    }
}
