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
    private int luck;

    private String occupation;

    public CoCCharacter(String name, Sex sex, int age) {
        super(name, sex, age);
        this.strength = generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5);
        this.constitution = generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5);
        this.size = generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5;
        this.dexterity = generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5);
        this.appearance = generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5);
        this.education = generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5;
        this.intelligence = generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5;
        this.power = generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5;
        this.luck = generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5);
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
            "POW: " + this.power + "\n" +
            "LUCK: " + this.luck + "\n";
        return temp;
    }


}
