/*
George Polyak
"Hartals" Problem
UVa Online Judge tested the solution
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {

        //Scanner kb = new Scanner(new FileInputStream(args[0]));
        Scanner kb = new Scanner(System.in);
        int testCases = Integer.parseInt(kb.nextLine());

        for (int i = 0; i < testCases; i++) {

            int days = Integer.parseInt(kb.nextLine());
            int parties = Integer.parseInt(kb.nextLine());
            int[] hartals = new int[parties];
            for (int j = 0; j < parties; j++) {
                hartals[j] = Integer.parseInt(kb.nextLine());
            }
            System.out.println(check(days,parties,hartals));

        }

    }

    private static int check(int days, int parties, int[] hartals) {

        int daysLost = 0;
        for (int i = 1; i <= days; i++) {

            if (i % 7 == 6 || i % 7 == 0) continue;
            for (int j = 0; j < parties; j++) {

                if (i % hartals[j] == 0) {
                    daysLost++;
                    break;
                }

            }

        }

        return daysLost;
    }

}

