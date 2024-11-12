public class Elf extends Character {

    public Elf () {
        super(350, 3, 1200, 35, "Elf");
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
        return "The best archer who has trodden our lands                       ";
    }
}