package src.main.models.character_backgrounds;

import src.main.models.helpers.Helpers;

public class CoCCharacterBackground {
    private String ideology;
    private String significantPerson;
    private String reasonForSignificantPerson;
    private String meaningfulLocation;
    private String treasuredPossession;
    private String trait;

    private final String[] IDEOLOGIES = new String[] {"There is a higher power that you worship and pray to", "Mankind can do fine without religion",
        "Science has all the answers", "A belief in fate", "Member of a society or secret society", "There is evil in society that should be rooted out",
        "The occult", "Politics", "Money is power, and I'm going to get all I can", "Campaigner"
    };

    private final String[] SIGNIFICANT_PEOPLE = new String [] { "Parent", "Grandparent", "Sibling", "Child", "Partner", 
        "Person who taught you your highest occupational skill", "Childhood friend", "A famous person", "An investigator in the game",
        "An NPC in the game"
    };

    private final String[] REASON_FOR_SIGNIFICANCE = new String [] {
        "You are indebted to them", "They taught you something", "They give your life meaning", "You've wronged them and seek reconciliation",
        "Shared experience", "You seek to prove yourself to them", "You idolize them", "A feeling of regret", "They have crossed you and you seek revenge"
    };

    private final String [] MEANINGFUL_LOCATIONS = new String [] {
        "Your seat of learning", "Your hometown", "The place you met your first love", "A place for quiet contemplation", "A place for socializing",
        "A place connected with your ideology/belief", "The grave of a significant person", "Your family home", "The place you were happiest in your life",
        "Your workplace"
    };

    private final String[] TREASURED_POSSESSIONS = new String[] {
        "An item connected with your highest skill", "An essential item for your occupation", "A memento from your childhood", "A memento of a departed person",
        "Something given to you by your Significant Person", "Your collection", "Something you found but don't know what it is", "A sporting item",
        "A weapon", "A pet"
    };

    private final String[] TRAITS = new String[] {
        "Generous", "Good with animals", "Dreamer", "Hedonist", "Gambler and a risk-taker", "Good cook", "Ladies' man/seductress", "Loyal",
        "A good reputation", "Ambitious"
    };


    public CoCCharacterBackground() {
        this.ideology = this.generateCharacteristic(this.IDEOLOGIES);
        this.significantPerson = this.generateCharacteristic(this.SIGNIFICANT_PEOPLE);
        this.reasonForSignificantPerson = this.generateCharacteristic(this.REASON_FOR_SIGNIFICANCE);
        this.meaningfulLocation = this.generateCharacteristic(this.MEANINGFUL_LOCATIONS);
        this.treasuredPossession = this.generateCharacteristic(this.TREASURED_POSSESSIONS);
        this.trait = this.generateCharacteristic(this.TRAITS);

    }

    private String generateCharacteristic(String[] characteristicList) {
        int num = Helpers.generateRandomNum(characteristicList.length - 1);
        return characteristicList[num];
    }

    // Getters and Setters

    public String getIdeology() {
        return this.ideology;
    }

    public void setIdeology(String ideology) {
        this.ideology = ideology;
    }

    public String getSignificantPerson() {
        return this.significantPerson;
    }

    public void setSignificantPerson(String significantPerson) {
        this.significantPerson = significantPerson;
    }

    public String getReasonForSignificantPerson() {
        return this.reasonForSignificantPerson;
    }

    public void setReasonForSignificantPerson(String reasonForSignificantPerson) {
        this.reasonForSignificantPerson = reasonForSignificantPerson;
    }

    public String getMeaningfulLocation() {
        return this.meaningfulLocation;
    }

    public void setMeaningfulLocation(String meaningfulLocation) {
        this.meaningfulLocation = meaningfulLocation;
    }

    public String getTreasuredPossession() {
        return this.treasuredPossession;
    }

    public void setTreasuredPossession(String treasuredPossession) {
        this.treasuredPossession = treasuredPossession;
    }

    public String getTrait() {
        return this.trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String[] getIDEOLOGIES() {
        return this.IDEOLOGIES;
    }


    public String[] getSIGNIFICANT_PEOPLE() {
        return this.SIGNIFICANT_PEOPLE;
    }


    public String[] getREASON_FOR_SIGNIFICANCE() {
        return this.REASON_FOR_SIGNIFICANCE;
    }


    public String[] getMEANINGFUL_LOCATIONS() {
        return this.MEANINGFUL_LOCATIONS;
    }


    public String[] getTREASURED_POSSESSIONS() {
        return this.TREASURED_POSSESSIONS;
    }


    public String[] getTRAITS() {
        return this.TRAITS;
    }



    public String toString() {
        return 
            "Ideology/Beliefs: " + this.ideology + "\n" +
            "Significant Person: " + this.significantPerson + "\n" +
            "Why that person is significant: " + this.reasonForSignificantPerson + "\n" +
            "Meaningful Location: " + this.meaningfulLocation + "\n" +
            "Treasured Possession: " + this.treasuredPossession + "\n" +
            "Trait: " + this.trait + "\n";
    }
}
