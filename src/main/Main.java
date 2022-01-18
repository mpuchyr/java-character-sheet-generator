package src.main;

import src.main.models.characters.CoCCharacter;
import src.main.models.characters.GenericCharacter;
import src.main.models.characters.CoCCharacter.CharacterEra;
import src.main.models.characters.GenericCharacter.Sex;

public class Main {
    public static void main(String[] args) {
        CoCCharacter person = new CoCCharacter(CharacterEra.NINETEENTWENTIES);
        System.out.println(person);
    }
}