package src.main.models.characters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class GenericCharacter {
   private String name;
   private String sex;
   private int age;

   protected LinkedHashMap<String, Integer> characterStats;
   protected HashMap<String, String> characterInfo;
   protected LinkedHashMap<String, Integer> characterSkills;

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

   public GenericCharacter(String name, Sex sex) {
       this.name = name;
       this.sex = sexAsString(sex);
       this.age = generateAge();
       this.initializeHashMaps();
       this.generateAllStats();
       this.generateAllSkills();
   }
   
   public GenericCharacter (String name, Sex sex, int age) {
        this.name = name;
        this.sex = sexAsString(sex);
        this.age = age;
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
        this.characterStats = new LinkedHashMap<String, Integer>();
        this.characterInfo = new HashMap<String, String>();
        this.characterSkills = new LinkedHashMap<String, Integer>();
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

    protected String convertCharactersticsToString(HashMap<String, Integer> info) {
        String temp = "";
        for (String key : info.keySet()) {
            temp += key + " " + info.get(key) + "\n";
        }
        return temp;
    }

// Abstract Methods
    protected abstract String gameOfCharacter();

    protected abstract String generateName(String sex);

    protected abstract void generateAllStats();

    protected abstract void generateAllSkills();



// toString
    public String toString() {
        return this.gameOfCharacter() + "\n" 
            + "Name: " + this.name + "\n"
            + "Age: " + this.age + "\n"
            + "Sex: " + this.sex + "\n"
            + "\t -- Character Stats -- \n"
            + convertCharactersticsToString(this.characterStats)
            + "\t -- Character Skills -- \n"
            + convertCharactersticsToString(this.characterSkills);
    }
}
