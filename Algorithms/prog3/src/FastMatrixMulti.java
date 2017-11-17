//George Polyak
//Prog3
//CSCD320

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FastMatrixMulti {

    private static int[][] par;
    private static int[][] tc;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        ArrayList<Integer> list = new ArrayList<>();
        while (file.hasNextLine()) {
            list.add(Integer.parseInt(file.nextLine()));
        }
        int[] matrix = list.stream().mapToInt(i -> i).toArray();
        matrixChainOrder(matrix);
        printOptimalParens(0, matrix.length - 2);
        System.out.println();
        System.out.println(tc[0][matrix.length - 2]); //this time cost is calculated
                                                      //incorrectly I think

    }

    private static void matrixChainOrder(int[] matrix) {

        int n = matrix.length - 1;
        tc = new int[n][n];
        par = new int[n][n];
        int j;
        for (int i = 0; i < n; i++) {
            tc[i][i] = 0;
        }
        for (int h = 2; h <= n; h++) {

            for (int i = 0; i < n - h + 1; i++) {

                j = i + h - 1;
                tc[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {

                    int q;
                    if (i > 0) {
                        q = tc[i][k] + tc[k + 1][j] + matrix[i - 1] * matrix[k] * matrix[j];
                    } else { //Wasn't sure how to handle the 1st case where i=0...
                        q = tc[i][k] + tc[k + 1][j] + matrix[k] * matrix[j];
                    }
                    if (q < tc[i][j]) {

                        tc[i][j] = q;
                        par[i][j] = k;

                    }

                }

            }

        }

    }

    private static void printOptimalParens(int i, int j) {

        if (i == j) {
            int x = i + 1;
            System.out.print("A" + x);
        } else {

            System.out.print("(");
            printOptimalParens(i, par[i][j]);
            printOptimalParens(par[i][j] + 1, j);
            System.out.print(")");

        }

    }

}
