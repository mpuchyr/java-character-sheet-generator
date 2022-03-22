package src.main.models.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import src.main.models.characters.CoCCharacter;

public class CharacterExporter {
    
    public static void exportCharacters(ArrayList<Object> charactersToExport) {
        LocalDate currentDate = LocalDate.now();
        try {
            FileWriter myWriter = new FileWriter("Characters-" + currentDate + ".txt");
            for (int i = 0; i < charactersToExport.size(); i++) {
                myWriter.write(charactersToExport.get(i).toString());
                myWriter.write("\n\n\n\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to file");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        
    }
}
