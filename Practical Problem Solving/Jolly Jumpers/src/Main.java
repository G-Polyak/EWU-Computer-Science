/*
George Polyak
"Jolly Jumpers" Problem
UVa Online Judge tested the solution
 */

import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        while (kb.hasNextLine()) {

            String input = kb.nextLine();
            boolean isJolly = true;
            ArrayList<Integer> results = new ArrayList<>();
            int[] nums = readNums(input);
            assert nums != null;
            int result;
            for (int i = 1; i < nums.length - 1; i++) {

                result = Math.abs(nums[i] - nums[i + 1]);
                if (result == 0 || result >= nums[0] || results.contains(result)) {
                    isJolly = false;
                }
                results.add(result);

            }
            String s = isJolly ? "Jolly" : "Not jolly";
            System.out.println(s);

        }

    }

    private static int[] readNums(String input) {

        Scanner sc = new Scanner(input);
        int first = sc.nextInt();
        int[] nums = new int[first + 1];
        nums[0] = first;
        int i = 1;
        while (sc.hasNextInt()) {
            nums[i] = sc.nextInt();
            i++;
        }
        return nums;

    }

}
