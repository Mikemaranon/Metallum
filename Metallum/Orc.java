public class Orc extends Character{ 

    public Orc () {
        super(600, 3/2, 1500, 0, "Orc");
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
        return "a strong and resistant being, where he goes, war accompanies him";
    }
}