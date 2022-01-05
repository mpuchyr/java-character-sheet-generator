package src.main.models.characters;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";

    private int strength;
    private int constitution;
    private int size;
    private int dexterity;
    private int appearance;
    private int education;
    private int intelligence;
    private int power; 

    public CoCCharacter(String name, Sex sex, int age) {
        super(name, sex, age);
        this.strength = generateStat(99);
        this.constitution = generateStat(99);
        this.size = generateStat(99);
        this.dexterity = generateStat(99);
        this.appearance = generateStat(99);
        this.education = generateStat(99);
        this.intelligence = generateStat(99);
        this.power = generateStat(99);
    }

    @Override 
    protected String gameOfCharacter() {
        return this.GAME;
    }

    @Override
    public String toString() {
        String temp = super.toString();
        temp += "STR: " + this.strength + "\n" +
            "CON: " + this.constitution + "\n" +
            "SIZ: " + this.size + "\n" +
            "DEX: " + this.dexterity + "\n" +
            "APP: " + this.appearance + "\n" +
            "EDU: " + this.education + "\n" +
            "INT: " + this.intelligence + "\n" +
            "POW: " + this.power + "\n";
        return temp;
    }


}
