package src.main.models.characters;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";
    private final String MALE_NAME_FILE = "src/main/models/data/CoCMaleNames.txt";
    private final String FEMALE_NAME_FILE = "src/main/models/data/CoCFemaleNames.txt";
    private final String LAST_NAME_FILE = "src/main/models/data/CoCLastNames.txt";
    private final String OCCUPATION_FILE = "src/main/models/data/CoCoccupations.txt";
    private final String SKILLS_FILE = "src/main/models/data/CoCAllSkills.txt";
    private final String ART_CRAFT_SPEC_FILE = "/src/main/models/CoCArtCraftSpecializations.txt";
    private final String SCIENCE_SPEC_FILE = "/src/main/models/CoCScienceSpecializations.txt";
    private final String SURVIVAL_SPEC_FILE = "/src/main/models/CoCSurvivalSpecializations.txt";
    private final String FIGHTING_SKILL_SPEC_FILE = "/src/main/models/CoCFightingSpecializations.txt";

    public enum CharacterEra {
        NINETEENTWENTIES,
        MODERN
    }

    private String era;
    private int personalInterestPoints;

    public CoCCharacter(CharacterEra characterEra) {
        super();
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, int age) {
        super(age);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, Sex sex) {
        super(sex);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, String name, Sex sex) {
        super(name, sex);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, int age, Sex sex) {
        super(age, sex);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, String name, Sex sex, int age) {
        super(name, sex, age);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", generateOccupation());
        this.generatePersonalInterestSkills();
    }

    public CoCCharacter(CharacterEra characterEra, String name, Sex sex, int age, String occupation) {
        super(name, sex, age);
        this.determineEra(characterEra);
        this.characterInfo.put("Occupation", occupation);
        this.generatePersonalInterestSkills();
    }

    protected void determineEra(CharacterEra characterEra) {
        switch (characterEra) {
            case NINETEENTWENTIES:
                this.era = "1920s";
                this.characterSkills.remove("Computer Use");
                break;
            case MODERN:
                this.era = "Modern";
                break;
        }
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
        this.ageModifier(this.getAge());
        this.generateHitPoints();
        this.personalInterestPoints = this.characterStats.get("INT") * 2;
        this.characterStats.put("SAN", this.characterStats.get("INT"));
        this.characterStats.put("LUCK", generateStatWithModifier(6, 3, Modifier.MULTIPLY, 5));
        this.generateMagicPoints();
        this.generateDamageBonusAndBuild();
        generateMove();
        
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

    @Override
    protected void generateHitPoints() {
        int hp = (this.characterStats.get("CON") + this.characterStats.get("SIZ")) / 10;
        this.characterStats.put("HP", hp);
    }

    protected void generateMagicPoints() {
        int mp = this.characterStats.get("POW") / 5;
        this.characterStats.put("MP", mp);
    }

    protected void generateDamageBonusAndBuild() {
        int total = this.characterStats.get("STR") + this.characterStats.get("SIZ");
        String bonus;
        int build;
        if (total >= 2 && total <= 64) {
            bonus = "-2";
            build = -2;
        } else if (total >= 65 && total <= 84) {
            bonus = "-1";
            build = -1;
        } else if (total >= 85 && total <= 124) {
            bonus = "None";
            build = 0;
        } else if (total >= 125 && total <= 164) {
            bonus = "+1D4";
            build = 1;
        } else if (total >= 165 && total <= 204) {
            bonus = "+1D6";
            build = 2;
        } else {
            bonus = "+2D6";
            build = 3;
        }
        this.characterInfo.put("Damage Bonus", bonus);
        this.characterStats.put("Build", build);
    }

    private void generateMove() {
        int move;
        int str = this.characterStats.get("STR");
        int dex = this.characterStats.get("DEX");
        int siz = this.characterStats.get("SIZ");
        if (dex < siz && str < siz) {
            move = 7;
        } if ((str > siz || dex > siz) || (str == dex && str == siz)) {
            move = 8;
        } else if (str > siz && dex > siz) {
            move = 9;
        } else {
            move = 7;
        }

        if (this.getAge() >= 40 && this.getAge() <= 49) {
            move -= 1;
        } else if (this.getAge() >= 50 && this.getAge() <= 59) {
            move -= 2;
        } else if (this.getAge() >= 60 && this.getAge() <= 69) {
            move -= 3;
        } else if (this.getAge() >= 70 && this.getAge() <= 79) {
            move -= 4;
        } else if (this.getAge() >= 80) {
            move -= 5;
        }

        this.characterStats.put("MOV", move);
    }

    private void ageModifier(int age) {
        if (age >= 15 && age <= 19) {
            int total = 5;
            int strMod = (int)(Math.random() * total + 1);
            int sizMod = total - strMod;
            this.characterStats.put("STR", this.characterStats.get("STR") - strMod);
            this.characterStats.put("SIZ", this.characterStats.get("SIZ") - sizMod);
            this.characterStats.put("EDU", this.characterStats.get("EDU") - 5);
        } else if (age >= 20 && age <= 39) {
            eduIncrease(1);
        } else if (age >= 40 && age <= 49) {
            eduIncrease(2);
            statReduction(5, 5);
        } else if (age >= 50 && age <= 59) {
            eduIncrease(3);
            statReduction(10, 10);
        } else if (age >= 60 && age <= 69) {
            eduIncrease(4);
            statReduction(20, 15);
        } else if (age >= 70 && age <= 79) {
            eduIncrease(4);
            statReduction(40, 20);
        } else {
            eduIncrease(4);
            statReduction(80, 25);
        }
    }

    private void eduIncrease(int numOfChecks) {
        for (int i = 0; i < numOfChecks; i++) {
            boolean eduCheckPass = this.statCheck(this.characterStats, "EDU");
            if (!eduCheckPass) {
                int eduMod = (int)(Math.random() * 10);
                this.characterStats.put("EDU", this.characterStats.get("EDU") + eduMod);
            }
        }
    }

    private void statReduction(int statReduction, int appReduction) {
        this.characterStats.put("APP", this.characterStats.get("APP") - appReduction);
        int total = statReduction;
        String[] statsToReduce = {"STR", "CON", "DEX"};
        while (total > 0) {
            for (int i = 0; i < statsToReduce.length; i++) {
                String stat = statsToReduce[i];
                if (total == 0) {
                    break;
                }
                int mod = (int)(Math.random() * (total + 1));
                if (mod <= this.characterStats.get(stat)) {
                    total -= mod;
                    this.characterStats.put(stat, this.characterStats.get(stat) - mod);
                } else {
                    total -= this.characterStats.get(stat);
                    this.characterStats.put(stat, 0);
                }
            }
        }
        
    }

    private boolean statCheck(HashMap<String, Integer> stat, String statToCheck) {
        int roll = (int)(Math.random() * 100) + 1;
        boolean pass = roll <= stat.get(statToCheck);
        return pass;
    }

    private String generateOccupation() {
        if (this.era.equals("Modern")) {
            return readRandomLineFromFile(OCCUPATION_FILE);
        } else {
            String occupation = readRandomLineFromFile(OCCUPATION_FILE);
            while (occupation.equals("Hacker")) {
                occupation = readRandomLineFromFile(OCCUPATION_FILE);
            }
            return occupation;
        }
        
    }

    private String getRandomCharacterSkill() {
        Set<String> keySet = this.characterSkills.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randId = (int)(Math.random() * size);

        return keyList.get(randId);
    }

    private void generatePersonalInterestSkills() {
        int total = this.personalInterestPoints;
        while (total > 0) {
            int mod = (int)(Math.random() * (total + 1));
            String skill = this.getRandomCharacterSkill();
            int skillNum = this.characterSkills.get(skill);
            if (!(skill.equals("Cthulhu Mythos"))) {
                if (skillNum + mod < 90) {
                    this.characterSkills.put(skill, skillNum + mod);
                    total -= mod;
                } else {
                    int deduction = 90 - this.characterSkills.get(skill);
                    this.characterSkills.put(skill, 90);
                    total -= deduction;
                }
            }
        }
    }

    @Override 
    protected String gameOfCharacter() {
        return this.GAME;
    }

    @Override
    protected String generateName(String sex) {
        String temp = sex == "male" ? "John" : "Jane";
        if (sex.equals("male")) {
            temp = readRandomLineFromFile(MALE_NAME_FILE);
        } else {
            temp = readRandomLineFromFile(FEMALE_NAME_FILE);
        }
        temp += " " + readRandomLineFromFile(LAST_NAME_FILE);
        return temp;
    }

}
