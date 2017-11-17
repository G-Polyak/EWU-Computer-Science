//George Polyak
//Prog2
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test_BestTrading {

    public static void main(String args[]) throws FileNotFoundException {

        Scanner data = new Scanner(new File(args[0]));
        ArrayList<Integer> nums = new ArrayList<>();

        while(data.hasNextLine()) {
            nums.add(Integer.parseInt(data.nextLine()));
        }
        int[] a = nums.stream().mapToInt(Integer::intValue).toArray();
        Trade best = bestTrade(a, 0, a.length-1);
        System.out.println(best);

    }

    private static Trade bestTrade(int[] a, int low, int high) {

        if(low == high) {
            return new Trade(low, high, 0);
        }
        int mid = (low + high) / 2;

        Trade left = bestTrade(a, low, mid);
        Trade right = bestTrade(a, mid+1, high);
        Trade cross = cross(a, low, high);

        if(left.profit > right.profit && left.profit > cross.profit) {
            return left;
        }
        if(right.profit > left.profit && right.profit > cross.profit) {
            return right;
        }
        return cross;

    }

    private static Trade cross(int[] a, int low, int high) {

        int mid = (low + high) / 2;
        int min = low;
        int max = high;

        for(int i = low; i <= mid; i++) {

            if(a[i] < a[min]) {
                min = i;
            }

        }
        for(int i = mid+1; i <= high; i++) {

            if(a[i] > a[max]) {
                max = i;
            }

        }
        return new Trade(min, max, a[max] - a[min]);

    }

}
