//George Polyak
//Prog1
//CSCD320

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test_OS_Finding {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        int i = Integer.parseInt(args[1]);
        Integer A[];
        ArrayList<Integer> list = new ArrayList<>();
        while(file.hasNextLine()) {
            list.add(Integer.parseInt(file.nextLine()));
        }
        if(i <= list.size()) {

            A = list.toArray(new Integer[list.size()]);
            Integer ret = RandomizedSelect(A, 0, A.length - 1, i);
            System.out.println(ret);

        } else {
            System.out.println("null");
        }

    }

    private static int RandomizedSelect(Integer[] A, int p, int r, int i)
    {

        if(p==r) {
            return A[p];
        }
        int q = randomized_partition(A, p, r);
        int k = q - p + 1;
        if(i==k) {
            return A[q];
        } else if (i<k) {
            return RandomizedSelect(A, p, q - 1, i);
        } else {
            return RandomizedSelect(A, q + 1, r, i - k);
        }

    }

    private static int partition(Integer[] A, int left, int right) {

        int pivot = A[right];
        int index = left;
        for(int i = left; i <= right-1; i++) {

            if (A[i] <= pivot) {

                swap(A, index, i);
                index++;
            }

        }
        swap(A, index, right);
        return index;

    }

    private static int randomized_partition(Integer[] A, int left, int right) {

        Random r = new Random();
        int i = r.nextInt(right-left) + left;
        swap(A, i, right);
        return partition(A, left, right);

    }

    private static void swap(Integer[] A, int i, int j) {

        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;

    }

}
