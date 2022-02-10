package src.main.models.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Helpers {
    private static final String ART_CRAFT_SPEC_FILE = "src/main/models/data/CoCArtCraftSpecializations.txt";
    private static final String SCIENCE_SPEC_FILE = "src/main/models/data/CoCScienceSpecializations.txt";
    private static final String SURVIVAL_SPEC_FILE = "src/main/models/data/CoCSurvivalSpecializations.txt";
    private static final String LANGUAGE_SPEC_FILE = "src/main/models/data/CoCLanguageSpecializations.txt";
    private static final String PILOT_SPEC_FILE = "src/main/models/data/CoCPilotSpecializations.txt";

    public static boolean checkForSpecificSkills(String skill) {
        if (skill.equals("Art/Craft")) {
            return true;
        } else if (skill.equals("Language (Other)")) {
            return true;
        } else if (skill.equals("Pilot")) {
            return true;
        } else if (skill.equals("Science")) {
            return true;
        } else if (skill.equals("Survival")) {
            return true;
        }
        return false;
    }

    public static String getRandomCharacterSkill(ConcurrentHashMap<String, Integer> characterSkills) {
        Set<String> keySet = characterSkills.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randId = (int)(Math.random() * size);

        return keyList.get(randId);
    }

    public static String generateSkillSpecialization(String skill) {
        String temp = skill + " ";
        if (skill.equals("Art/Craft")) {
            return temp += "(" + readRandomLineFromFile(ART_CRAFT_SPEC_FILE) + ")";
        } else if (skill.equals("Language (Other)")) {
            return temp += "(" + readRandomLineFromFile(LANGUAGE_SPEC_FILE) + ")";
        } else if (skill.equals("Pilot")) {
            return temp += "(" + readRandomLineFromFile(PILOT_SPEC_FILE) + ")";
        } else if (skill.equals("Science")) {
            return temp += "(" + readRandomLineFromFile(SCIENCE_SPEC_FILE) + ")";
        } else if (skill.equals("Survival")) {
            return temp += "(" + readRandomLineFromFile(SURVIVAL_SPEC_FILE) + ")";
        }
        return temp;
    }


    public static String readRandomLineFromFile(String filename) {
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
}
