package src.main.models.characters;

import java.util.HashMap;

public abstract class GenericCharacter {
   private String name;
   private String sex;
   private int age;

   public HashMap<String, Integer> characterStats;
   public HashMap<String, String> characterInfo;

   public enum Sex {
       MALE,
       FEMALE
   }

   public enum Modifier {
       ADD,
       SUBTRACT,
       DIVIDE,
       MULTIPLY
   }

   public GenericCharacter() {
        this.age = generateAge();
        this.sex = sexAsString(generateSex());
        this.name = generateName();
        this.characterStats = new HashMap<String, Integer>();
        this.characterInfo = new HashMap<String, String>();
   }

   public GenericCharacter(Sex sex) {
       this.sex = sexAsString(sex);
       this.age = generateAge();
       this.name = generateName();
       this.characterStats = new HashMap<String, Integer>();
       this.characterInfo = new HashMap<String, String>();
   }
   
   public GenericCharacter (String name, Sex sex, int age) {
        this.name = name;
        this.sex = sexAsString(sex);
        this.age = age;
        this.characterStats = new HashMap<String, Integer>();
        this.characterInfo = new HashMap<String, String>();
   }

   public GenericCharacter (GenericCharacter source) {
       this.name = source.name;
       this.sex = source.sex;
       this.age = source.age;
   }

// Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected Sex generateSex() {
        int randomNum = (int)(Math.random() * 2) + 1;
        if (randomNum == 1) {
            return Sex.MALE;
        } else {
            return Sex.FEMALE;
        }
    }

    protected String sexAsString(Sex sex) {
        String temp = "";
        switch(sex) {
            case MALE:
                temp = "male";
                break;
            case FEMALE: 
                temp = "female";
                break;
        }
        return temp;
    }

    protected String generateName() {
        String name = "";
        if (this.sex.equals("male")) {
            name = "John";
        } else {
            name = "Jenny";
        }
        return name;
     }

    protected int generateAge() {
        int age = 0;
        while (age < 15 || age > 75) {
            age = (int)(Math.random() * 75) + 1;
        }
        return age;
    }

    protected int generateStat(int die, int times) {
        int temp = 0;
        for (int i = 0; i < times; i++) {
            temp += (int)(Math.random() * die) + 1;
        }
        return temp;
    }

    protected int generateStatWithModifier(int die, int times, Modifier modifierType, int modifier) {
        int temp = generateStat(die, times);

        switch (modifierType) {
            case ADD:
                temp += modifier;
                break;
            case SUBTRACT:
                temp -= modifier;
                break;
            case MULTIPLY:
                temp *= modifier;
                break;
            case DIVIDE:
                temp /= modifier;
                break;
        }
        return temp;
    }


// Abstract Methods
    protected abstract String gameOfCharacter();

    protected abstract void generateAllStats();



// toString
    public String toString() {
        return this.gameOfCharacter() + "\n" 
            + "Name: " + this.name + "\n"
            + "Age: " + this.age + "\n"
            + "Sex: " + this.sex + "\n"
            + "\t -- Character Stats -- \n";
    }
}
