package src.main.models.characters;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";
    private final String MALE_NAME_FILE = "src/main/models/data/CoCMaleNames.txt";
    private final String FEMALE_NAME_FILE = "src/main/models/data/CoCFemaleNames.txt";
    private final String LAST_NAME_FILE = "src/main/models/data/CoCLastNames.txt";
    private final String OCCUPATION_FILE = "src/main/models/data/CoCoccupations.txt";
    private final String[] BASIC_SKILLS = {
        "Accounting", "Anthropology", "Appraise", "Archaeology", "Art/Craft", "Charm", "Climb", "Credit Rating", "Cthulhu Mythos",
        "Disguise", "Dodge", "Drive Auto", "Elec. Repair", "Fast Talk", "Fighting (Brawl)", "Firearms (Handgun)", "Firearms (Rifle/Shotgun)",
        "First Aid", "History", "Intimidate", "Jump", "Language (Other)", "Language (Own)", "Law", "Library Use", "Listen", "Locksmith",
        "Mech. Repair", "Medicine", "Natural World", "Navigate", "Occult", "Op. Hv. Machine", "Persuade", "Pilot", "Psychology", "Psychoanalysis",
        "Ride", "Science", "Sleight of Hand", "Spot Hidden", "Stealth", "Survival", "Swim", "Throw", "Track"
    };
    private final Integer[] BASIC_SKILLS_STATS = {
        5, 1, 5, 1, 5, 15, 20, 0, 0, 5, 0, 20, 10, 5, 25, 20, 25, 30, 5, 15, 
        20, 1, 0, 5, 20, 20, 1, 10, 1, 10, 10, 5, 1, 10, 1, 10, 1, 5, 1, 10, 
        25, 20, 10, 20, 20, 10
    };


    public CoCCharacter() {
        super();
        this.characterInfo.put("Occupation", generateOccupation());
        generateAllSkills();
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
        for (int i = 0; i < BASIC_SKILLS.length; i++) {
            this.characterSkills.put(BASIC_SKILLS[i], BASIC_SKILLS_STATS[i]);
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
