public class Witcher extends Character{

    public Witcher () {
        super(800, 2, 500, 100, "Witcher");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
    @Override
    public String getGod() {
        return "Geralt";
    }
    @Override
    public String getDescription() {
        return "Toss a coin to your wicher... oh valley of plenty...            ";
    }
}