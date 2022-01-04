package src.main.models.characters;

public class Character {
    private String name;
    private String occupation;
    private int age;
    private String sex;
    private int strength;
    private int constitution;
    private int size;
    private int dexterity;
    private int appearance;
    private int intelligence;
    private int power;
    private int move;
    private int sanity;
    private int magicPoints;
    private int luck;

    public Character(String name, String occupation, int age, String sex, int strength, int constitution,
        int size, int dexterity, int appearance, int intelligence, int power, int move, int sanity, int magicPoints, int luck) {
            this.name = name;
            this.occupation = occupation;
            this.age = age;
            this.sex = sex;
            this.strength = strength;
            this.constitution = constitution;
            this.size = size;
            this.dexterity = dexterity;
            this.appearance = appearance;
            this.intelligence = intelligence;
            this.power = power;
            this.move = move;
            this.sanity = sanity;
            this.magicPoints = magicPoints;
            this.luck = luck;
    }

    public Character(Character source) {
        this.name = source.name;
        this.occupation = source.occupation;
        this.age = source.age;
        this.sex = source.sex;
        this.strength = source.strength;
        this.constitution = source.constitution;
        this.size = source.size;
        this.dexterity = source.dexterity;
        this.appearance = source.appearance;
        this.intelligence = source.intelligence;
        this.power = source.power;
        this.move = source.move;
        this.sanity = source.sanity;
        this.magicPoints = source.magicPoints;
        this.luck = source.luck;
    }

// Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return this.constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAppearance() {
        return this.appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMove() {
        return this.move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getSanity() {
        return this.sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    public int getMagicPoints() {
        return this.magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public int getLuck() {
        return this.luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public String toString() {
        return "Name: " + this.name + "\n" +
            "Sex: " + this.sex + "\n" +
            "Age: " + this.age + "\n" +
            "Occupation: " + this.occupation + "\n" +
            "\t -- Character Stats -- \t\n" +
            "STR: " + this.strength + "\n" +
            "CON: " + this.constitution + "\n" +
            "SIZ: " + this.size + "\n" +
            "DEX: " + this.dexterity + "\n" +
            "INT: " + this.intelligence + "\n" +
            "POW: " + this.power + "\n" +
            "MOV: " + this.move + "\n" +
            "SAN: " + this.sanity + "\n" +
            "MP: " + this.magicPoints + "\n" +
            "LUCK: " + this.luck + "\n";
    }


}
