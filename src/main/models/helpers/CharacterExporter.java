package src.main.models.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CharacterExporter {
    
    public static void exportCharacters(ArrayList<Object> charactersToExport) {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("yyyy-dd-MM-HHmmss");
        String formattedDate = myDateFormat.format(current);
        if (charactersToExport.size() > 0) {
            try {
                FileWriter myWriter = new FileWriter("Characters-" + formattedDate + ".txt");
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
        } else {
            System.out.println("There are no characters to export");
        }

        
    }
}
