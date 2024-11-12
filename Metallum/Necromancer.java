public class Necromancer extends Character {

    public Necromancer () {
        super(600, 2, 800, 45, "Necromancer");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Shinok";
    }
    @Override
    public String getDescription() {
        return "the direct son of death, the dark arts he practices would destroy an entire civilisation";
    }
}