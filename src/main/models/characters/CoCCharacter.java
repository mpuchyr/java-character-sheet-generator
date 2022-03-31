package src.main.models.characters;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import src.main.models.character_backgrounds.CoCCharacterBackground;
import src.main.models.helpers.*;

import src.main.models.occupations.CoCOccupations;

public class CoCCharacter extends GenericCharacter {
    private final String GAME = "Call of Cthulhu";
    private final String MALE_NAME_FILE = "src/main/models/data/CoCMaleNames.txt";
    private final String FEMALE_NAME_FILE = "src/main/models/data/CoCFemaleNames.txt";
    private final String LAST_NAME_FILE = "src/main/models/data/CoCLastNames.txt";
    private final String OCCUPATION_FILE = "src/main/models/data/CoCoccupations.txt";
    private final String SKILLS_FILE = "src/main/models/data/CoCAllSkills.txt";


    public enum CharacterEra {
        NINETEENTWENTIES,
        MODERN
    }

    private String era;
    private int personalInterestPoints;
    private String background;
    private int skillLimit;

    public CoCCharacter (
            CharacterEra characterEra, String name, int age, Sex sex, String occupation, 
            boolean generateAllSkills, boolean generateBackground, Integer skillLimit) {
        super(name, sex, age);
        if (skillLimit <= 50) {
            this.skillLimit = 50;
        } else {
            this.skillLimit = skillLimit;
        }
        this.determineEra(characterEra);
        if (occupation == null || occupation.isBlank() || occupation == "none") {
            this.initializeCharacter(characterEra, generateOccupation(), generateAllSkills, generateBackground);
        } else {
            this.initializeCharacter(characterEra, occupation, generateAllSkills, generateBackground);
        }   
    }

    // Includes methods that every constructor uses, regardless of arguments given to constructor
    private void initializeCharacter(CharacterEra characterEra, String occupation, boolean generateAllSkills, boolean generateBackground) {
        this.characterInfo.put("Occupation", occupation);
        if (generateAllSkills) {
            this.generateOccupationSkills();
            this.generatePersonalInterestSkills();
        }
        if (generateBackground) {
            CoCCharacterBackground characterbackGround = new CoCCharacterBackground();
            this.background = characterbackGround.toString();
        } else {
            background = "";
        }
    }

    // Getters and Setters

    public String getGAME() {
        return this.GAME;
    }


    public String getMALE_NAME_FILE() {
        return this.MALE_NAME_FILE;
    }


    public String getFEMALE_NAME_FILE() {
        return this.FEMALE_NAME_FILE;
    }


    public String getLAST_NAME_FILE() {
        return this.LAST_NAME_FILE;
    }


    public String getOCCUPATION_FILE() {
        return this.OCCUPATION_FILE;
    }


    public String getSKILLS_FILE() {
        return this.SKILLS_FILE;
    }


    public String getEra() {
        return this.era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public int getPersonalInterestPoints() {
        return this.personalInterestPoints;
    }

    public void setPersonalInterestPoints(int personalInterestPoints) {
        this.personalInterestPoints = personalInterestPoints;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    // Removes skills from the characterskills array based on character's era
    protected void determineEra(CharacterEra characterEra) {
        switch (characterEra) {
            case NINETEENTWENTIES:
                this.era = "1920s";
                this.characterSkills.remove("Computer Use");
                this.characterSkills.remove("Electronics");
                break;
            case MODERN:
                this.era = "Modern";
                break;
        }
    }

    // Generates all the basic stats of a character
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

    // Reads a file of character skills and adds them to the characterstats array
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

    // Uses character's CON and SIZ to determine HP 
    @Override
    protected void generateHitPoints() {
        int hp = (this.characterStats.get("CON") + this.characterStats.get("SIZ")) / 10;
        this.characterStats.put("HP", hp);
    }

    // Uses character's POW stat to determine magic points
    protected void generateMagicPoints() {
        int mp = this.characterStats.get("POW") / 5;
        this.characterStats.put("MP", mp);
    }

    // Generates damage bonus and build based on SIZ and STR stats
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

    // Determines character's move stat based on multiple factors
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

    // Modifies character's stats based on the character's age
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


    // Makes a number of checks to determine if character's EDU stat increases
    private void eduIncrease(int numOfChecks) {
        for (int i = 0; i < numOfChecks; i++) {
            boolean eduCheckPass = this.statCheck(this.characterStats, "EDU");
            if (!eduCheckPass) {
                int eduMod = (int)(Math.random() * 10);
                this.characterStats.put("EDU", this.characterStats.get("EDU") + eduMod);
            }
        }
    }


    // Reduces a character's APP and randomly reduces STR, CON, and DEX stats
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

    // Checks if a character passes a check based on a specific character stat
    private boolean statCheck(ConcurrentHashMap<String, Integer> stat, String statToCheck) {
        int roll = (int)(Math.random() * 100) + 1;
        boolean pass = roll <= stat.get(statToCheck);
        return pass;
    }

    // Randomly chooses a character's occupation
    private String generateOccupation() {
        String occupation;
        if (this.era.equals("Modern")) {
            occupation = Helpers.readRandomLineFromFile(OCCUPATION_FILE);
            boolean valid = validOccupation(occupation);
            while (!valid) {
                occupation = Helpers.readRandomLineFromFile(OCCUPATION_FILE);
            }

        } else {
            occupation = Helpers.readRandomLineFromFile(OCCUPATION_FILE);
            boolean valid = validOccupation(occupation);
            while (occupation.equals("Hacker") || !valid) {
                occupation = Helpers.readRandomLineFromFile(OCCUPATION_FILE);
                valid = validOccupation(occupation);
            }
        }
        return occupation;
        
    }

    // Uses CoCOccupations to generate a character's occupation skills
    private void generateOccupationSkills() {
        CoCOccupations occupation = new CoCOccupations(this.characterInfo.get("Occupation"), this.characterStats, this.characterSkills);
        this.characterSkills = occupation.getCharacterSkills();
        
    }


    // Checks if an occupation is valid based on character's age
    private boolean validOccupation(String occupation) {
        boolean validOccupation = true;
        if (this.getAge() < 20) {
            if (occupation.equals("Antiquarian")) {
                validOccupation = false;
            } else if (occupation.equals("Clergy Member")) {
                validOccupation = false;
            } else if (occupation.equals("Doctor of Medicine")) {
                validOccupation = false;
            } else if (occupation.equals("Engineer")) {
                validOccupation = false;
            } else if (occupation.equals("Lawyer")) {
                validOccupation = false;
            } else if (occupation.equals("Military Officer")) {
                validOccupation = false;
            } else if (occupation.equals("Parapsychologist")) {
                validOccupation = false;
            } else if (occupation.equals("Police Detective")) {
                validOccupation = false;
            } else if (occupation.equals("Police Officer")) {
                validOccupation = false;
            } else if (occupation.equals("Private Investigator")) {
                validOccupation = false;
            } else if (occupation.equals("Professor")) {
                validOccupation = false;
            }
        }

        return validOccupation;
    }

    // Goes through and randomly determins a character's personal interest skills
    private void generatePersonalInterestSkills() {
        int total = this.personalInterestPoints;
        while (total > 0) {
            int mod = (int)(Math.random() * (total + 1));
            String skill = Helpers.getRandomCharacterSkill(this.characterSkills);
            int skillNum = this.characterSkills.get(skill);
            if (skillIsValid(skill)) {
                if (Helpers.checkForSpecificSkills(skill)) {
                    this.characterSkills.remove(skill);
                    String replacementSkill = Helpers.generateSkillSpecialization(skill);
                    this.characterSkills.put(replacementSkill, 1);
                    skillNum = this.characterSkills.get(replacementSkill);
                    skill = replacementSkill;
                }
                if (skillNum + mod < this.skillLimit) {
                    this.characterSkills.put(skill, skillNum + mod);
                    total -= mod;
                } else {
                    int deduction = this.skillLimit - this.characterSkills.get(skill);
                    this.characterSkills.put(skill, this.skillLimit);
                    total -= deduction;
                }
            }
        }
    }

    // Checks that a skill is not Cthulhu Mythos
    protected boolean skillIsValid(String skill) {
        if (skill.equals("Cthulhu Mythos")) {
            return false;
        }
        return true;
    }


    @Override 
    protected String gameOfCharacter() {
        return this.GAME;
    }

    @Override
    protected String generateName(String sex) {
        String temp = sex == "male" ? "John" : "Jane";
        if (sex.equals("male")) {
            temp = Helpers.readRandomLineFromFile(MALE_NAME_FILE);
        } else {
            temp = Helpers.readRandomLineFromFile(FEMALE_NAME_FILE);
        }
        temp += " " + Helpers.readRandomLineFromFile(LAST_NAME_FILE);
        return temp;
    }

    public String toString() {
        return super.toString() + "\n" +
            "-- Character Background --\n" +
            this.background;
    }

}
