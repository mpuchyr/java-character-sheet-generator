package src.main.models.characters;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";
    private final String MALE_NAME_FILE = "src/main/models/data/CoCMaleNames.txt";
    private final String FEMALE_NAME_FILE = "src/main/models/data/CoCFemaleNames.txt";
    private final String LAST_NAME_FILE = "src/main/models/data/CoCLastNames.txt";
    private final String OCCUPATION_FILE = "src/main/models/data/CoCoccupations.txt";
    private final String SKILLS_FILE = "src/main/models/data/CoCAllSkills.txt";


    public CoCCharacter() {
        super();
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(Sex sex) {
        super(sex);
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(String name, Sex sex) {
        super(name, sex);
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(String name, Sex sex, int age) {
        super(name, sex, age);
        this.characterInfo.put("Occupation", generateOccupation());
    }

    public CoCCharacter(String name, Sex sex, int age, String occupation) {
        super(name, sex, age);
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

    @Override
    protected void generateAllSkills() {
        try {
            FileInputStream fis = new FileInputStream(SKILLS_FILE);
            Scanner scan = new Scanner(fis);
            while(scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                this.characterSkills.put(line[0], Integer.parseInt(line[1]));
            }
            characterSkills.put("Dodge", this.characterStats.get("DEX") / 2);
            characterSkills.put("Language (Own)", this.characterStats.get("EDU"));
            scan.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private String generateOccupation() {
        return readRandomLineFromFile(OCCUPATION_FILE);
    }

    @Override 
    protected String gameOfCharacter() {
        return this.GAME;
    }

    @Override
    protected String generateName(String sex) {
        String temp = "N/A";
        if (sex.equals("male")) {
            temp = readRandomLineFromFile(MALE_NAME_FILE);
        } else {
            temp = readRandomLineFromFile(FEMALE_NAME_FILE);
        }
        temp += " " + readRandomLineFromFile(LAST_NAME_FILE);
        return temp;
    }

    // @Override
    // protected String convertCharacterSkillsToString() {
    //     String temp = "";
    //     for (String key : characterSkills.keySet()) {
    //         temp += key + " " + characterSkills.get(key) + "\n";
    //     }
    //     return temp;
    // }


    // @Override
    // public String toString() {
    //     String temp = super.toString();
    //     temp += "STR: " + this.characterStats.get("STR") + "\n";
    //         // "CON: " + this.characterStats.get("STR") + "\n" +
    //         // "SIZ: " + this.characterStats.get("SIZ") + "\n" +
    //         // "DEX: " + this.characterStats.get("DEX") + "\n" +
    //         // "APP: " + this.characterStats.get("APP") + "\n" +
    //         // "EDU: " + this.characterStats.get("EDU") + "\n" +
    //         // "INT: " + this.characterStats.get("INT") + "\n" +
    //         // "POW: " + this.characterStats.get("POW") + "\n" +
    //         // "LUCK: " + this.characterStats.get("LUCK") + "\n" +
    //         // "Occupation: " + this.characterInfo.get("Occupation") + "\n";
    //     return temp;
    // }


}
