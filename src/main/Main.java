package src.main;

import src.main.models.characters.CoCCharacter;
import src.main.models.characters.GenericCharacter;
import src.main.models.characters.GenericCharacter.Sex;

public class Main {
    public static void main(String[] args) {
        CoCCharacter john = new CoCCharacter("John", Sex.MALE, 25);
        System.out.println(john);
    }
}