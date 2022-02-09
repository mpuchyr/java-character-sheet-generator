package src.main.models.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helpers {

    
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
