package src.main.models.occupations;

import java.util.ArrayList;

public class CoCOccupations {
    private String name;
    private ArrayList<String> occupationSkills;
    private int creditMin;
    private int creditMax;


    public CoCOccupations(String name) {
        this.name = name;
        switch (name) {
            case "Antiquarian":
                this.creditMin = 30;
                this.creditMax = 70;
                break;
            case "Artist":
                this.creditMin = 9;
                this.creditMax = 50;
                break;
            case "Athlete":
                this.creditMin = 9;
                this.creditMax = 70;
                break;
            case "Author":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Clergy Member":
                this.creditMin = 9;
                this.creditMax = 60;
                break;
            case "Criminal":
                this.creditMin = 5;
                this.creditMax = 65;
                break;
            case "Dilettante":
                this.creditMin = 50;
                this.creditMax = 99;
                break;
            case "Doctor of Medicine":
                this.creditMin = 30;
                this.creditMax = 80;
                break;
            case "Drifter":
                this.creditMin = 0;
                this.creditMax = 5;
                break;
            case "Engineer":
                this.creditMin = 30;
                this.creditMax = 60;
                break;
            case "Entertainer":
                this.creditMin = 9;
                this.creditMax = 70;
                break;
            case "Farmer":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Hacker":
                this.creditMin = 10;
                this.creditMax = 70;
                break;
            case "Journalist":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Lawyer":
                this.creditMin = 30;
                this.creditMax = 80;
                break;
            case "Librarian":
                this.creditMin = 9;
                this.creditMax = 35;
                break;
            case "Military Officer":
                this.creditMin = 20;
                this.creditMax = 70;
                break;
            case "Missionary":
                this.creditMin = 0;
                this.creditMax = 30;
                break;
            case "Musician":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Parapsychologist":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Pilot":
                this.creditMin = 20;
                this.creditMax = 70;
                break;
            case "Police Detective":
                this.creditMin = 20;
                this.creditMax = 50;
                break;
            case "Police Officer":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Private Investigator":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Professor":
                this.creditMin = 20;
                this.creditMax = 70;
                break;
            case "Soldier":
                this.creditMin = 9;
                this.creditMax = 30;
                break;
            case "Tribe Member":
                this.creditMin = 0;
                this.creditMax = 15;
                break;
            case "Zealot":
                this.creditMin = 0;
                this.creditMax = 30;
                break;
        }
    }


}
