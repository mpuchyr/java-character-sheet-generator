package src.main.models.characters;

public abstract class GenericCharacter {
   private String name;
   private String sex;
   private int age;

   public enum Sex {
       MALE,
       FEMALE
   }
   
   public GenericCharacter (String name, Sex sex, int age) {
    this.name = name;
    switch (sex) {
        case MALE:
            this.sex = "male";
            break;
        case FEMALE:
            this.sex = "female";
            break;
    }
    this.age = age;
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

    protected int generateStat(int range) {
        return (int)(Math.random() * range) + 1;
    }

    protected int generateStatMin (int range, int min) {
        int stat = min - 1;
        while (stat < min) {
            stat = (int)(Math.random() * range) + 1;
        }
        return stat;
    }

    protected int generateStatMax (int range, int max) {
        int stat = max + 1;
        while (stat > max) {
            stat = (int)(Math.random() * range) + 1;
        }
        return stat;
    }

    protected int generatStatMinAndMax (int range, int min, int max) {
        int stat = min - 1;
        while ((stat > min) || (stat < max)) {
            stat = (int)(Math.random() * range) + 1;
        }
        return stat;
    }

// Abstract Methods
    protected abstract String gameOfCharacter();


// toString
    public String toString() {
        return this.gameOfCharacter() + "\n" 
            + "Name: " + this.name + "\n"
            + "Age: " + this.age + "\n"
            + "Sex: " + this.sex + "\n"
            + "\t -- Character Stats -- \n";
    }
}
