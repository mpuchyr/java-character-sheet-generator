package src.main.models.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Helpers {



    public static String getRandomCharacterSkill(ConcurrentHashMap<String, Integer> characterSkills) {
        Set<String> keySet = characterSkills.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        
        int size = keyList.size();
        int randId = (int)(Math.random() * size);

        return keyList.get(randId);
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
