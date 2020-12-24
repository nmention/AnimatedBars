import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Proprocess a csv file to a format readable by AnimatedDiagBar. Output to standart output
 */
public class PreprocessData {

    /**
     * Main function
     * @param args The wanted parameters for the processing
     * @throws FileNotFoundException when file does not exists
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 5) {
            System.out.println("Not enough arguments");
            return;
        }

        HashMap<String, ArrayList<String>> dateMap = new HashMap<>();

        File input = new File(args[0]);
        Scanner scanner = new Scanner(input);

        int idCaptions = Integer.parseInt(args[1]);
        int idNames = Integer.parseInt(args[2]);
        int idValues = Integer.parseInt(args[3]);
        int idCats = Integer.parseInt(args[4]);

        System.out.println("Generic Diagram Name");
        System.out.println("X Axis Legend");
        System.out.println("Data Source");
        System.out.println();

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("[;,]");
            String parsedLine = line[idCaptions] + "," + line[idNames] + ",," +  line[idValues] + "," + line[idCats];
            if (!dateMap.containsKey(line[idCaptions])) {
                dateMap.put(line[idCaptions], new ArrayList<>());
            }
            dateMap.get(line[idCaptions]).add(parsedLine);
        }

        String[] keys = dateMap.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        for (String key : keys) {
            ArrayList<String> entry = dateMap.get(key);
            System.out.println(entry.size());
            for (String line : entry) {
                System.out.println(line);
            }
            System.out.println();
        }
    }
}
