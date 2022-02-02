package src.main.models.occupations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CoCOccupations {
    private String name;
    private String[][] occupationSkills;
    private int creditMin;
    private int creditMax;
    private int skillPoints;


    public CoCOccupations(String name, ConcurrentHashMap<String, Integer> characterStats) {
        this.name = name;
        switch (name) {
            case "Antiquarian":
                this.creditMin = 30;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Appraise"}, {"Art/Craft"}, {"History"}, {"Library Use"}, {"Language (Other)"}, {"Spot Hidden"}, {"Any"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}
                };
                break;
            case "Artist":
                this.creditMin = 9;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"POW", "DEX"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"History", "Natural World"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Language (Other)"}, {"Psychology"}, {"Spot Hidden"}, {"Any", "Any"}
                };
                break;
            case "Athlete":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"STR", "DEX"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Jump"}, {"Fighting (Brawl)"}, {"Ride"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Swim"}, {"Throw"}, {"Any"}
                };
                break;
            case "Author":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Literature)"}, {"History"}, {"Library Use"}, {"Natural World", "Occult"}, {"Language (Other)"}, {"Language (Own)"}, {"Psychology"}, {"Any"}
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
                    {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Spot Hidden"}, {"Stealth"}, {""}
                };
                break;
            case "Dilettante":
                this.creditMin = 50;
                this.creditMax = 99;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"Firearms"}, {"Language (Other)"}, {"Ride"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any", "Any", "Any"}
                };
                break;
            case "Doctor of Medicine":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"First Aid"}, {"Language (Other) - Latin"}, {"Medicine"}, {"Psychology"}, {"Science - Biology"}, {"Science - Pharmacy"}, {"Any, Any"} 
                };
                break;
            case "Drifter":
                this.creditMin = 0;
                this.creditMax = 5;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Jump"}, {"Listen"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Stealth"}, {"Any", "Any"}
                };
                break;
            case "Engineer":
                this.creditMin = 30;
                this.creditMax = 60;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][] {
                    {"Art/Craft (Technical Drawing)"}, {"Electrical Repair"}, {"Library Use"}, {"Mechanical Repair"}, {"Op. Hv. Machine"}, {"Science - Engineering"}, {"Science - Physics"}, {"Any"}
                };
                break;
            case "Entertainer":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("APP") * 2;
                this.occupationSkills = new String[][] {
                    {"Art/Craft (Acting)"}, {"Disguise"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Listen"}, {"Psychology"}, {"Any", "Any"}
                };
                break;
            case "Farmer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Farming)"}, {"Drive Auto"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Mechanical Repair"}, {"Natural World"}, {"Op. Hv. Machine"}, {"Track"}, {"Any"}
                };
                break;
            case "Hacker":
                this.creditMin = 10;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Computer Use"}, {"Electrical Repair"}, {"Electronics"}, {"Library Use"}, {"Spot Hidden"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any"}
                };
                break;
            case "Journalist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Photography)"}, {"History"}, {"Library Use"}, {"Language (Own)"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Any", "Any"}
                };
                break;
            case "Lawyer":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Law"}, {"Library Use"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Any", "Any"}
                };
                break;
            case "Librarian":
                this.creditMin = 9;
                this.creditMax = 35;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Library Use"}, {"Language (Other)"}, {"Language (Own)"}, {"Any", "Any", "Any", "Any"}
                };
                break;
            case "Military Officer":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Accounting"}, {"Firearms"}, {"Navigate"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"},
                    {"Psychology"}, {"Survival"}, {"Any"}
                };
                break;
            case "Missionary":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Art/Craft"}, {"First Aid"}, {"Mechanical Repair"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Any", "Any"}
                };
                break;
            case "Musician":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "POW"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (instrument)"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Listen"}, {"Psychology"}, {"Any", "Any", "Any", "Any"}
                };
                break;
            case "Parapsychologist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Anthropology"}, {"Art/Craft (Photography)"}, {"History"}, {"Library Use"}, {"Occult"}, {"Language (Other)"}, {"Psychology"}, {"Any"}
                };
                break;
            case "Pilot":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("DEX") * 2;
                this.occupationSkills = new String[][]{
                    {"Electrical Repair"}, {"Mechanical Repair"}, {"Op. Hv. Machine"}, {"Pilot (aircraft)"}, {"Science (Astronomy)"}, {"Any", "Any"}
                };
                break;
            case "Police Detective":
                this.creditMin = 20;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Acting)", "Disguise"}, {"Firearms"}, {"Law"}, {"Listen"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Spot Hidden"}, {"Any"}
                };
                break;
            case "Police Officer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Fighting (Brawl)"}, {"Firearms"}, {"First Aid"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Law"}, {"Psychology"}, {"Spot Hidden"}, {"Drive Auto", "Ride"}
                };
                break;
            case "Private Investigator":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Art/Craft (Photography)"}, {"Disguise"}, {"Law"}, {"Library Use"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"},
                    {"Spot Hidden"}, {"Computer Use", "Locksmith", "Firearms"}
                };
                break;
            case "Professor":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Library Use"}, {"Language (Other)"}, {"Language (Own)"}, {"Psychology"}, {"Any", "Any", "Any", "Any"}
                };
                break;
            case "Soldier":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb", "Swim"}, {"Dodge"}, {"Fighting"}, {"Firearms"}, {"First Aid", "Mechanical Repair", "Language (Other)"}, {"First Aid", "Mechanical Repair", "Language (Other)"}
                };
                break;
            case "Tribe Member":
                this.creditMin = 0;
                this.creditMax = 15;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"Climb"}, {"Fighting", "Throw"}, {"Natural World"}, {"Listen"}, {"Occult"}, {"Swim"}, {"Survival"} 
                };
                break;
            case "Zealot":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "POW"}, characterStats) * 2;
                this.occupationSkills = new String[][]{
                    {"History"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Charm", "Fast Talk", "Intimidate", "Persuade"}, {"Psychology"}, {"Stealth"}, {"Any", "Any", "Any"}
                };
                break;
            default:
                this.skillPoints = characterStats.get("EDU") * 4;
                this.occupationSkills = new String[][]{
                    {"Any", "Any", "Any", "Any", "Any", "Any", "Any", "Any"}
                };
                break;
        }
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


}
