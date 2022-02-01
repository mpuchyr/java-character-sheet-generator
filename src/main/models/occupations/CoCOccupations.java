package src.main.models.occupations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CoCOccupations {
    private String name;
    private String[] occupationSkills;
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
                break;
            case "Artist":
                this.creditMin = 9;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"POW", "DEX"}, characterStats) * 2;
                break;
            case "Athlete":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"STR", "DEX"}, characterStats) * 2;
                break;
            case "Author":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Clergy Member":
                this.creditMin = 9;
                this.creditMax = 60;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Criminal":
                this.creditMin = 5;
                this.creditMax = 65;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Dilettante":
                this.creditMin = 50;
                this.creditMax = 99;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Doctor of Medicine":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Drifter":
                this.creditMin = 0;
                this.creditMax = 5;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "DEX", "STR"}, characterStats) * 2;
                break;
            case "Engineer":
                this.creditMin = 30;
                this.creditMax = 60;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Entertainer":
                this.creditMin = 9;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("APP") * 2;
                break;
            case "Farmer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Hacker":
                this.creditMin = 10;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Journalist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Lawyer":
                this.creditMin = 30;
                this.creditMax = 80;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Librarian":
                this.creditMin = 9;
                this.creditMax = 35;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Military Officer":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Missionary":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Musician":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "POW"}, characterStats) * 2;
                break;
            case "Parapsychologist":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Pilot":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 2 + characterStats.get("DEX") * 2;
                break;
            case "Police Detective":
                this.creditMin = 20;
                this.creditMax = 50;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Police Officer":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Private Investigator":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Professor":
                this.creditMin = 20;
                this.creditMax = 70;
                this.skillPoints = characterStats.get("EDU") * 4;
                break;
            case "Soldier":
                this.creditMin = 9;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Tribe Member":
                this.creditMin = 0;
                this.creditMax = 15;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"DEX", "STR"}, characterStats) * 2;
                break;
            case "Zealot":
                this.creditMin = 0;
                this.creditMax = 30;
                this.skillPoints = characterStats.get("EDU") * 2 + this.checkForHighestSkill(new String[]{"APP", "POW"}, characterStats) * 2;
                break;
            default:
                this.skillPoints = characterStats.get("EDU") * 4;
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

    public int getSkillPoints() {
        return this.skillPoints;
    }


}
