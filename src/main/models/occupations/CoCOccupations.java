package src.main.models.occupations;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CoCOccupations {
    private final String ART_CRAFT_SPEC_FILE = "src/main/models/data/CoCArtCraftSpecializations.txt";
    private final String SCIENCE_SPEC_FILE = "src/main/models/data/CoCScienceSpecializations.txt";
    private final String SURVIVAL_SPEC_FILE = "src/main/models/data/CoCSurvivalSpecializations.txt";
    private final String LANGUAGE_SPEC_FILE = "src/main/models/data/CoCLanguageSpecializations.txt";
    private final String PILOT_SPEC_FILE = "src/main/models/data/CoCPilotSpecializations.txt";
    
    private String name;
    private String[][] occupationSkills;
    private ConcurrentHashMap<String, Integer> characterSkills;
    private int creditMin;
    private int creditMax;
    private int skillPoints;


    public CoCOccupations(String name, ConcurrentHashMap<String, Integer> characterStats, ConcurrentHashMap<String, Integer> characterSkills) {
        this.name = name;
        this.characterSkills = characterSkills;
        switch (name) {
            case "Antiquarian":
                this.creditMin = 30;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Appraise"}, {"Art/Craft"}, {"History"}, {"Library Use"}, {"Language (Other)"}, {"Spot Hidden"}, {"Any"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}
                };
                break;
            case "Artist":
                this.creditMin = 9;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"POW", "DEX"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"History", "Natural World"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Language (Other)"}, 
                    {"Psychology"}, {"Spot Hidden"}, {"Any"}, {"Any"}
                };
                break;
            case "Athlete":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"STR", "DEX"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Jump"}, {"Fighting (Brawl)"}, {"Ride"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Swim"}, {"Throw"}, 
                    {"Any"}
                };
                break;
            case "Author":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Literature)"}, {"History"}, {"Library Use"}, {"Natural World", "Occult"}, {"Language (Other)"}, 
                    {"Language (Own)"}, {"Psychology"}, {"Any"}
                };
                break;
            case "Clergy Member":
                this.creditMin = 9;
                this.creditMax = 60;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"History"}, {"Library Use"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Any"}
                };
                break;
            case "Criminal":
                this.creditMin = 5;
                this.creditMax = 65;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Spot Hidden"}, {"Stealth"}, {"Any"}
                };
                break;
            case "Dilettante":
                this.creditMin = 50;
                this.creditMax = 99;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"Firearms (Handgun)", "Firearms (Rifle/Shotgun)"}, {"Language (Other)"}, {"Ride"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
            case "Doctor of Medicine":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"First Aid"}, {"Language (Other) - Latin"}, {"Medicine"}, {"Psychology"}, {"Science (Biology)"}, 
                    {"Science (Pharmacy)"}, {"Any"}, {"Any"}
                };
                break;
            case "Drifter":
                this.creditMin = 0;
                this.creditMax = 5;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Jump"}, {"Listen"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Stealth"}, {"Any"}, {"Any"}
                };
                break;
            case "Engineer":
                this.creditMin = 30;
                this.creditMax = 60;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][] {
                    {"Art/Craft (Technical Drawing)"}, {"Electrical Repair"}, {"Library Use"}, {"Mechanical Repair"}, {"Op. Hv. Machine"}, 
                    {"Science - Engineering"}, {"Science - Physics"}, {"Any"}
                };
                break;
            case "Entertainer":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("APP") * 2;
                this.occupationSkills = new String[][] {
                    {"Art/Craft (Acting)"}, {"Disguise"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Listen"}, {"Psychology"}, {"Any"}, {"Any"}
                };
                break;
            case "Farmer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Farming)"}, {"Drive Auto"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Mechanical Repair"}, 
                    {"Natural World"}, {"Op. Hv. Machine"}, {"Track"}, {"Any"}
                };
                break;
            case "Hacker":
                this.creditMin = 10;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Computer Use"}, {"Electrical Repair"}, {"Electronics"}, {"Library Use"}, {"Spot Hidden"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any"}
                };
                break;
            case "Journalist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Photography)"}, {"History"}, {"Library Use"}, {"Language (Own)"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Any"}, {"Any"}
                };
                break;
            case "Lawyer":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Law"}, {"Library Use"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Any"}, {"Any"}
                };
                break;
            case "Librarian":
                this.creditMin = 9;
                this.creditMax = 35;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Library Use"}, {"Language (Other)"}, {"Language (Own)"}, {"Any"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
            case "Military Officer":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Firearms (Handgun), Firearms (Rifle/Shotgun)"}, {"Navigate"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"},
                    {"Psychology"}, {"Survival"}, {"Any"}
                };
                break;
            case "Missionary":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"First Aid"}, {"Mechanical Repair"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any"}, {"Any"}
                };
                break;
            case "Musician":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "POW"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (instrument)"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Listen"}, {"Psychology"}, 
                    {"Any"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
            case "Parapsychologist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Anthropology"}, {"Art/Craft (Photography)"}, {"History"}, {"Library Use"}, {"Occult"}, {"Language (Other)"}, 
                    {"Psychology"}, {"Any"}
                };
                break;
            case "Pilot":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("DEX") * 2;
                this.occupationSkills = new String[][]{
                    {"Electrical Repair"}, {"Mechanical Repair"}, {"Op. Hv. Machine"}, {"Pilot (Aircraft)"}, {"Science (Astronomy)"}, 
                    {"Any"}, {"Any"}
                };
                break;
            case "Police Detective":
                this.creditMin = 20;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Acting)", "Disguise"}, {"Firearms (Handgun)", "Firearms (Rifle/Shotgun)"}, {"Law"}, {"Listen"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Spot Hidden"}, {"Any"}
                };
                break;
            case "Police Officer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Fighting (Brawl)"}, {"Firearms (Handgun)", "Firearms (Rifle/Shotgun)"}, {"First Aid"}, 
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Law"}, {"Psychology"}, {"Spot Hidden"}, {"Drive Auto", "Ride"}
                };
                break;
            case "Private Investigator":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Photography)"}, {"Disguise"}, {"Law"}, {"Library Use"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"},
                    {"Spot Hidden"}, {"Computer Use", "Locksmith", "Firearms (Handgun)"}
                };
                break;
            case "Professor":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Library Use"}, {"Language (Other)"}, {"Language (Own)"}, {"Psychology"}, {"Any"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
            case "Soldier":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb", "Swim"}, {"Dodge"}, {"Fighting (Brawl)"}, {"Firearms (Handgun)", "Firearms (Rifle/Shotgun)", "Firearms (Flamethrower)", "Firearms (Machine Gun)", "Firearms (Submachine Gun)"}, 
                    {"First Aid", "Mechanical Repair", "Language (Other)"}, {"First Aid", "Mechanical Repair", "Language (Other)"}
                };
                break;
            case "Tribe Member":
                this.creditMin = 0;
                this.creditMax = 15;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Fighting (Brawl)", "Throw"}, {"Natural World"}, {"Listen"}, {"Occult"}, {"Swim"}, {"Survival"} 
                };
                break;
            case "Zealot":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "POW"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"History"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, 
                    {"Stealth"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
            default:
                this.skillPoints = characterStats.get("EDU") * 4;
                this.creditMin = 0;
                this.creditMax = 99;
                this.occupationSkills = new String[][]{
                    {"Any"}, {"Any"}, {"Any"}, {"Any"}, {"Any"}, {"Any"}, {"Any"}
                };
                break;
        }
        this.generateCreditRating();
        this.assignSkillPoints();
    }

    private int checkForHighestSkill(String[] skills, ConcurrentHashMap<String, Integer> characterStats) {
        int highest = characterStats.get(skills[0]);
        for (int i = 1; i < skills.length; i++) {
            if (characterStats.get(skills[i]) > highest) {
                highest = characterStats.get(skills[i]);
            } 
        }
        return highest;
    }

    public String getName() {
        return this.name;
    }

    public int getSkillPoints() {
        return this.skillPoints;
    }

    public int getCreditMin() {
        return this.creditMin;
    }

    public int getCreditMax() {
        return this.creditMax;
    }

    public String[][] getOccupationSkills() {
        return this.occupationSkills;
    }

    public ConcurrentHashMap<String, Integer> getCharacterSkills() {
        return this.characterSkills;
    }

    private void generateCreditRating() {
        Random r = new Random();
        int randomNum = r.nextInt((this.creditMax - this.creditMin) + 1) + this.creditMin;
        this.characterSkills.put("Credit Rating", randomNum);
        this.skillPoints = skillPoints -= randomNum;
    }

    private void assignSkillPoints() {
        int total = this.skillPoints;
        while (total > 0) {
            for (int i = 0; i < this.occupationSkills.length; i++) {
                if (total <= 0) break;
                if (this.occupationSkills[i][0].equals("Any")) {
                    String skill = getRandomCharacterSkill();
                    while (skill.equals("Cthulhu Mythos") || skill.equals("Credit Rating")) {
                        skill = getRandomCharacterSkill();
                    }
                    if (total <= 0) {
                        break;
                    }
                    skill = checkForSpecificSkills(skill) ? generateSkillSpecialization(skill) : skill;
                    String[] replacementSkill = {skill};
                    this.occupationSkills[i] = replacementSkill;
                    int pointsToAdd = total >= 50 ? generateRandomNum(50) : generateRandomNum(total);
                    total = addPointsToSkill(skill, pointsToAdd, total);

                } else if (this.occupationSkills[i].length > 1) {
                    String skill = this.occupationSkills[i][generateRandomNum(this.occupationSkills[i].length - 1)];
                    while (skill.equals(this.occupationSkills[i-1][0])) {
                        skill = this.occupationSkills[i][generateRandomNum(this.occupationSkills[i].length - 1)];
                    }
                    skill = checkForSpecificSkills(skill) ? generateSkillSpecialization(skill) : skill;
                    String[] skillArray = {skill};
                    this.occupationSkills[i] = skillArray;
                    int pointsToAdd = total >= 50 ? generateRandomNum(50) : generateRandomNum(total);
                    total = addPointsToSkill(skill, pointsToAdd, total);
                } else {
                    String skill = this.occupationSkills[i][0];
                    skill = checkForSpecificSkills(skill) ? generateSkillSpecialization(skill) : skill;
                    String replacementSkill = skill;
                    this.occupationSkills[i][0] = replacementSkill;
                    int pointsToAdd = total >= 50 ? generateRandomNum(50) : generateRandomNum(total);
                    total = addPointsToSkill(skill, pointsToAdd, total);
                }
                if (total > 0 && i == this.occupationSkills.length) {
                    i = 0;
                }
            }
        }
    }

    private int addPointsToSkill(String skill, int pointsToAdd, int totalPoints) {
        if (this.characterSkills.get(skill) == null) {
            this.characterSkills.put(skill, 1);
        }
        int skillPoints = this.characterSkills.get(skill);
        if (skillPoints + pointsToAdd <= 90) {
            this.characterSkills.put(skill, skillPoints + pointsToAdd);
            return totalPoints - pointsToAdd;
        } else if (skillPoints + pointsToAdd > 90)  {
            this.characterSkills.put(skill, 90);
            int deduction = 90 - this.characterSkills.get(skill);
            return totalPoints - deduction;
        }
        return totalPoints;

    }

    private int generateRandomNum(int range) {
        return (int)(Math.random() * range) + 1;
    }

    private String getRandomCharacterSkill() {
        Set<String> keySet = this.characterSkills.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randId = (int)(Math.random() * size);

        return keyList.get(randId);
    }

    protected boolean checkForSpecificSkills(String skill) {
        if (skill.equals("Art/Craft")) {
            return true;
        } else if (skill.equals("Language (Other)")) {
            return true;
        } else if (skill.equals("Pilot")) {
            return true;
        } else if (skill.equals("Science")) {
            return true;
        } else if (skill.equals("Survival")) {
            return true;
        }
        return false;
    }

    protected String generateSkillSpecialization(String skill) {
        String temp = skill + " ";
        if (skill.equals("Art/Craft")) {
            return temp += "(" + this.readRandomLineFromFile(ART_CRAFT_SPEC_FILE) + ")";
        } else if (skill.equals("Language (Other)")) {
            return temp += "(" + this.readRandomLineFromFile(LANGUAGE_SPEC_FILE) + ")";
        } else if (skill.equals("Pilot")) {
            return temp += "(" + this.readRandomLineFromFile(PILOT_SPEC_FILE) + ")";
        } else if (skill.equals("Science")) {
            return temp += "(" + this.readRandomLineFromFile(SCIENCE_SPEC_FILE) + ")";
        } else if (skill.equals("Survival")) {
            return temp += "(" + this.readRandomLineFromFile(SURVIVAL_SPEC_FILE) + ")";
        }
        return temp;
    }

    protected String readRandomLineFromFile(String filename) {
        String temp = "N/A";
        Path path = Paths.get(filename);
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


}
