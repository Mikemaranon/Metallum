public class Werewolf extends Character{

    public Werewolf () {
        super(400, 3, 1300, 10, "Werewolf");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "";
    }
    @Override
    public String getDescription() {
        return "during the day, a man ... in the moonlight a fierce beast       ";
    }
}