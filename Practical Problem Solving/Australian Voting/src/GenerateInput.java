import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerateInput {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter out = new PrintWriter(new File("src/input.txt"));
        Random r = new Random();
        //int numNames = r.nextInt(20) + 1;
        int numNames = 20;
        out.println(numNames);
        for (int i = 0; i < numNames; i++) {
            out.println(randName(r));
        }
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 1; i <= numNames; i++) {
            line.add(i);
        }
        //for (int i = 0; i < r.nextInt(1000) + 1; i++) {
        for (int i = 0; i < 1000; i++) {

            Collections.shuffle(line);
            for (int j : line) {
                out.print(j + " ");
            }
            out.println();

        }
        out.close();

    }

    private static String randName(Random r) {

        //int randLength = r.nextInt(80) + 1;
        int randLength = 1;
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < randLength; i++) {

            char c = (char)(r.nextInt(26) + 'a');
            name.append(c);

        }
        return name.toString();

    }

}
