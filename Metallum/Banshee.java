public class Banshee extends Character {

    public Banshee () {
        super(400, 3, 1100, 30, "Bashee");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Shiva";
    }
    @Override
    public String getDescription() {
        return "If one of them crosses your path, your end is near";
    }
}
