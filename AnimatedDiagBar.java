import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Animate DiagBar with file-supplied data.
 */
public class AnimatedDiagBar {

    /**
     * Main function
     * @param args The wanted parameters for the animation
     * @throws FileNotFoundException when specified file does not exist
     * @throws InterruptedException
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        if (args.length < 3) {
            System.out.println("Vous devez donner 3 arguments !");
            return;
        }

        int nBars = Integer.parseInt(args[1]);
        boolean sorted = true;

        if (args[2].equals("nosort")) {
            sorted = false;
        }

        StdDraw.setCanvasSize(1280, 800);
        StdDraw.enableDoubleBuffering();

        File inputFile = new File(args[0]);
        Scanner input = new Scanner(inputFile);

        String title = input.nextLine();
        String xAxis = input.nextLine();
        String source = input.nextLine();

        DiagBar diag = new DiagBar(title, xAxis, source, nBars, sorted);
        input.nextLine(); // Skip empty line

        try {
            while (input.hasNext()) {
                int lineCount = Integer.parseInt(input.nextLine());

                diag.reset();
                for (int j = 0; j < lineCount; j++) {
                    String[] barLine = input.nextLine().split(",", 5);
                    Bar bar = new Bar(barLine[1], (int) Float.parseFloat(barLine[3]), barLine[4]);
                    diag.add(bar);

                    if (j == 0) {
                        diag.setCaption(barLine[0]);
                    }
                }

                diag.draw();
                input.nextLine(); // Skip empty line
                Thread.sleep(30);
            }
        } catch (NoSuchElementException e) {
            System.out.println("End of file");
        }

    }
}
