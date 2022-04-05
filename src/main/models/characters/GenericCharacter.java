package src.main.models.characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GenericCharacter {
   private String name;
   private String sex;
   private int age;

    ConcurrentHashMap<String, Integer> characterStats;
    ConcurrentHashMap<String, String> characterInfo;
    ConcurrentHashMap<String, Integer> characterSkills;

   public enum Sex {
       MALE,
       FEMALE,
       ANY
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
        this.name = generateName(this.sex);
        this.initializeHashMaps();
        this.generateAllStats();
        this.generateAllSkills();
   }

   public GenericCharacter(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        this.age = age;
        this.sex = sexAsString(generateSex());
        this.name = generateName(this.sex);
        this.initializeHashMaps();
        this.generateAllStats();
        this.generateAllSkills();
   }

   public GenericCharacter(Sex sex) {
        this.sex = sexAsString(sex);
        this.age = generateAge();
        this.name = generateName(this.sex);
        this.initializeHashMaps();
        this.generateAllStats();
        this.generateAllSkills();
   }

   public GenericCharacter(int age, Sex sex) {
       if (age <= 0) {
           throw new IllegalArgumentException("Age must be greater than 0");
       }
        this.sex = sexAsString(sex);
        this.age = age;
        this.name = generateName(this.sex);
        this.initializeHashMaps();
        this.generateAllStats();
        this.generateAllSkills();
   }

   public GenericCharacter(String name, Sex sex) {
       if (name == null || name.isBlank()) {
           throw new IllegalArgumentException("Name cannot be blank/null");
       }
        this.name = name;
       this.sex = sexAsString(sex);
       this.age = generateAge();
       this.initializeHashMaps();
       this.generateAllStats();
       this.generateAllSkills();
   }
   
   public GenericCharacter (String name, Sex sex, Integer age) {
        if (sex.equals(Sex.ANY)) {
            this.sex = sexAsString(this.generateSex());
        } else {
            this.sex = sexAsString(sex);
        }
        if (name == null || name.isBlank()) {
            this.name = this.generateName(this.sex);
        } else {
            this.name = name;
        }
        if (age <= 0 || age == null) {
            this.age = this.generateAge();
        } else {
            this.age = age;
        }

        this.initializeHashMaps();
        this.generateAllStats();
        this.generateAllSkills();
   }

   public GenericCharacter (GenericCharacter source) {
       this.name = source.name;
       this.sex = source.sex;
       this.age = source.age;
       this.characterStats = source.characterStats;
       this.characterInfo = source.characterInfo;
       this.characterSkills = source.characterSkills;
   }

   private void initializeHashMaps() {
        this.characterStats = new ConcurrentHashMap<String, Integer>();
        this.characterInfo = new ConcurrentHashMap<String, String>();
        this.characterSkills = new ConcurrentHashMap<String, Integer>();
   }

// Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank/null");
        }
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        if (sex == null || sex.isBlank()) {
            throw new IllegalArgumentException("Sex cannot be blank/null");
        }
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age cannot be less than 0");
        }
        this.age = age;
    }


// Characteristic Generators
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
            case ANY:
                temp = sexAsString(generateSex());
                break;
        }
        return temp;
    }

    protected int generateAge() {
        int age = 0;
        while (age < 15 || age > 90) {
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

    protected String convertCharacteristicsToString(ConcurrentHashMap<String, Integer> info) {
        String temp = "";
        ArrayList<String> sortedKeys = new ArrayList<String>(info.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            temp += key + " " + info.get(key) + "\n";
        }
        return temp;
    }

    protected String convertCharacterInfoToString(ConcurrentHashMap<String, String> info) {
        String temp = "";
        ArrayList<String> sortedKeys = new ArrayList<String>(info.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            temp += key + ": " + info.get(key) + "\n";
        }
        return temp;
    }

    protected int rollDie(int die) {
        return (int)(Math.random() * die) + 1;
    }

// Abstract Methods
    protected abstract String gameOfCharacter();

    protected abstract String generateName(String sex);

    protected abstract void generateAllStats();

    protected abstract void generateAllSkills();

    protected abstract void generateHitPoints();




// toString
    public String toString() {
        return this.gameOfCharacter() + "\n" 
            + "Name: " + this.name + "\n"
            + "Age: " + this.age + "\n"
            + "Sex: " + this.sex + "\n"
            + "\t -- Character Information -- \n"
            + convertCharacterInfoToString(this.characterInfo)
            + "\t -- Character Stats -- \n"
            + convertCharacteristicsToString(this.characterStats)
            + "\t -- Character Skills -- \n"
            + convertCharacteristicsToString(this.characterSkills);
    }
}
