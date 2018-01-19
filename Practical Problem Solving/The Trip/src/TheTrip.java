/*
George Polyak
"The Trip" Problem
The test file tests typical input as well as min and max students and
min and max purchases.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TheTrip {

    public static void main(String[] args) throws FileNotFoundException {

        String fileName = null;
        if (args.length >= 1) {
            fileName = args[0];
        }
        if (fileName == null) {
            fileName = "input.txt";
        }
        Scanner fileScanner = new Scanner(new File(fileName));
        int size = 0;
        int count = 0;
        double[] array = {};
        double total;
        double posChange;
        double negChange;
        double avg;
        double diff;
        double equalize;
        String line = fileScanner.nextLine();
        while (!line.equals("0")) {

            if (line.contains(".")) {

                array[count] = Double.parseDouble(line);
                count++;
                if (count == size) {

                    total = 0;
                    posChange = 0;
                    negChange = 0;
                    for (double n : array) {
                        total += n;
                    }
                    avg = total / size;
                    for (double n : array) {

                        diff = ((n - avg) * 100) / 100;
                        if (diff < 0) {
                            negChange += diff;
                        } else {
                            posChange += diff;
                        }

                    }
                    equalize = (-negChange > posChange) ? -negChange : posChange;
                    System.out.printf("$%.2f\n", equalize);

                }

            } else {

                count = 0;
                size = Integer.parseInt(line);
                array = new double[size];

            }
            line = fileScanner.nextLine();

        }

    }

}
