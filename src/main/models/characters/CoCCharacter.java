package src.main.models.characters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";
    private final String OCCUPATION_FILE = "src/main/models/data/CoCoccupations.txt";


    public CoCCharacter() {
        super();
        generateAllStats();
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(Sex sex) {
        super(sex);
        generateAllStats();
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(String name, Sex sex, int age) {
        super(name, sex, age);
        generateAllStats();
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(String name, Sex sex, int age, String occupation) {
        super(name, sex, age);

        generateAllStats();

        this.characterInfo.put("Occupation", occupation);
    }

    @Override
    protected void generateAllStats() {
        this.characterStats.put("STR", generateStatWithModifier(6,3, Modifier.MULTIPLY, 5));
        this.characterStats.put("CON", generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5));
        this.characterStats.put("SIZ", generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5);
        this.characterStats.put("DEX", generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5));
        this.characterStats.put("APP", generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5));
        this.characterStats.put("EDU", generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5);
        this.characterStats.put("INT", generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5);
        this.characterStats.put("POW", generateStatWithModifier(6, 2, Modifier.ADD, 6) * 5);
        this.characterStats.put("LUCK", generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5));
    }

    private String generateOccupation() {
        String temp = "N/A";
        Path path = Paths.get(OCCUPATION_FILE);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
            int randomLine = (int)(Math.random() * lines) + 1;
            temp = Files.readAllLines(path).get(randomLine);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }


    @Override 
    protected String gameOfCharacter() {
        return this.GAME;
    }


    @Override
    public String toString() {
        String temp = super.toString();
        temp += "STR: " + this.characterStats.get("STR") + "\n" +
            "CON: " + this.characterStats.get("STR") + "\n" +
            "SIZ: " + this.characterStats.get("SIZ") + "\n" +
            "DEX: " + this.characterStats.get("DEX") + "\n" +
            "APP: " + this.characterStats.get("APP") + "\n" +
            "EDU: " + this.characterStats.get("EDU") + "\n" +
            "INT: " + this.characterStats.get("INT") + "\n" +
            "POW: " + this.characterStats.get("POW") + "\n" +
            "LUCK: " + this.characterStats.get("LUCK") + "\n" +
            "Occupation: " + this.characterInfo.get("Occupation");
        return temp;
    }


}
