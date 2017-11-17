//George Polyak
//Assignment 5 - Pascal's Triangle
//Extra Credit Attempted

import java.util.*;

public class PascalTester {

    public static void main(String[] args) {

        int[][] triangle = buildTriangle();
        computeTriangle(triangle);
        printTriangle(triangle);

    }

    private static int[][] buildTriangle() {

        int[][] tri = new int[16][];
        for (int r = 0; r < tri.length; r++) {
            tri[r] = new int[r + 3];
        }
        return tri;

    }

    private static void computeTriangle(int[][] tri) {

        tri[0][1] = 1;
        for (int r = 1; r < tri.length; r++) {

            for (int c = 0; c < tri[r].length; c++) {

                if (c == 0 || c == tri[r].length - 1) {
                    tri[r][c] = 0;
                } else {
                    tri[r][c] = tri[r - 1][c - 1] + tri[r - 1][c];
                }

            }

        }

    }

    private static void printTriangle(int[][] tri) {

        for (int i = 0; i < 16; i++) {

            for (int k = 16; k > i; k--) {

                System.out.format("%-" + 4 + "s", " ");
            }
            for (int j = 1; j < tri[i].length - 1; j++) {

                System.out.format("%-" + (4 + 4) + "s", tri[i][j]);
            }
            System.out.println();
        }

    }

}