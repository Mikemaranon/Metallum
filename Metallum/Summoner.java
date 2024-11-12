public class Summoner extends Character {

    public Summoner () {
        super(500, 2, 700, 55, "Summoner");
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
        return "Summons undead beings and leads an army that is not afraid of death";
    }
}