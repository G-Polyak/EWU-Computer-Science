import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("r.in"));
        PrintWriter out = new PrintWriter("r.out");
        String alphabet;
        String[] words;
        String line = "";
        while (!line.contains("#")) {

            line = in.nextLine();
            if (in.hasNextLine()) {

                alphabet = in.nextLine();
                words = line.split(" ");
                words = sort(words, alphabet);
                for (String word : words) {
                    out.print(word + " ");
                }
                out.println();

            }

        }
        out.close();

    }

    private static String[] sort(String arr[], String alphabet) {

        Arrays.sort(arr, (a, b) -> {

            int aIndex = 0;
            int bIndex = 0;
            for (int i = 0; i < Math.min(a.length(), b.length()) && aIndex == bIndex; i++) {

                aIndex = alphabet.indexOf(a.charAt(i));
                bIndex = alphabet.indexOf(b.charAt(i));

            }
            if (aIndex == bIndex && a.length() != b.length()) {
                return a.length() - b.length();
            }
            return aIndex - bIndex;

        });
        return arr;

    }

}
