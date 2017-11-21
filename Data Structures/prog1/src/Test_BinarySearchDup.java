//George Polyak
//Prog1
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test_BinarySearchDup {

    public static void main(String args[]) throws FileNotFoundException {

        Scanner data = new Scanner(new File(args[0]));
        int x = Integer.parseInt(args[1]);
        ArrayList<Integer> nums = new ArrayList<>();

        while(data.hasNextLine()) {
            nums.add(Integer.parseInt(data.nextLine()));
        }
        int[] a = nums.stream().mapToInt(Integer::intValue).toArray();

        System.out.println( "[" + findLeftMost(a, x) + "," + findRightMost(a, x) + "]");

    }

    private static int findLeftMost(int[] a, int x) {

        int i = -1;
        int first = 0;
        int last = a.length - 1;
        int mid = last / 2;

        while (first <= last) {

            if (a[mid] == x) {

                if (mid == 0) {
                    i = mid;
                    break;
                } else if(a[mid-1] < a[mid]){
                    i = mid;
                    break;
                } else {
                    last = mid - 1;
                }

            } else if (a[mid] < x) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;

        }
        return i;

    }

    private static int findRightMost(int[] a, int x) {

        int i = -1;
        int first = 0;
        int last = a.length - 1;
        int mid = last / 2;

        while (first <= last) {

            if (a[mid] == x) {

                if (mid == a.length - 1) {
                    i = mid;
                    break;
                } else if(a[mid] < a[mid+1]){
                    i = mid;
                    break;
                } else {
                    first = mid + 1;
                }

            } else if (a[mid] > x) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
            mid = (first + last) / 2;

        }
        return i;

    }

}
