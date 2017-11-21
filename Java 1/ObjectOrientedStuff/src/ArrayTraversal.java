//George Polyak
//Assignment 4 - Array Traversal

import java.util.*;

public class ArrayTraversal {

    public static void main(String args[]) {
        ArrayList<Integer> numbers = new ArrayList<>();
        int choice = 0;
        Scanner kb = new Scanner(System.in);
        getInitialArray(numbers, kb);
        while (choice != 6) {
            choice = getMenuChoice(kb);
            performChoice(choice, numbers, kb);
        }
        System.out.println("Goodbye");
    }

    public static void getInitialArray(ArrayList<Integer> numbers, Scanner kb) {
        System.out.print("Please enter a set of non-negative integers with a space between each integer\n-->");

        boolean retry = true;
        while (retry) {
            try {
                String set = kb.nextLine();
                String[] subset = set.split(" ");
                for (String s : subset) {
                    if(Integer.parseInt(s) < 0)
                    {
                        throw new NumberFormatException();
                    }
                    numbers.add(Integer.parseInt(s));
                }
                retry = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
                retry = true;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select from the following menu choices");
        System.out.println("\n1. Add a number to the array");
        System.out.println("2. Display the mean");
        System.out.println("3. Display the median");
        System.out.println("4. Print the array to the screen");
        System.out.println("5. Print the array in reverse order");
        System.out.println("6. Quit the program\n");
        System.out.print("Choice -->");
    }

    public static int getMenuChoice(Scanner kb) {
        int choice;
        do {
            displayMenu();
            while(!kb.hasNextInt()){
                kb.next();
                System.out.println("That is an invalid menu choice\nPlease try again");
            }
            choice = kb.nextInt();
            kb.nextLine();
            if (choice < 1 || choice > 6)
                System.out.println("That is an invalid menu choice\nPlease try again");
        } while (choice < 1 || choice > 6);
        return choice;
    }

    public static void performChoice(int choice, ArrayList<Integer> numbers, Scanner kb) {
        switch (choice) {
            case 1:
                System.out.print("\nPlease enter the number you wish to add to the array\n-->");
                numbers.add(kb.nextInt());
                break;
            case 2:
                option2(numbers);
                break;
            case 3:
                option3(numbers);
                break;
            case 4:
                option4(numbers);
                break;
            case 5:
                option5(numbers);
        }
    }


    private static void option2(ArrayList<Integer> nums) {
        int mean;
        int sum = 0;
        for (int i : nums) {
            sum = sum + i;
        }
        mean = sum / nums.size();
        System.out.println("\nThe mean is: " + mean);
    }

    private static void option3(ArrayList<Integer> nums) {
        int median = 0;
        SortSearchUtil.selectionSort(nums);
        if (nums.size() % 2 == 0) {
            median = (nums.get((nums.size() / 2) - 1) + nums.get((nums.size() / 2))) / 2;
        } else {
            median = nums.get(nums.size() / 2);
        }
        System.out.println("\nThe median is: " + median);
    }

    private static void option4(ArrayList<Integer> nums) {

        System.out.print("\nThe current array is: ");
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();

    }

    private static void option5(ArrayList<Integer> nums) {
        SortSearchUtil.selectionSort(nums);
        Collections.reverse(nums);
        System.out.print("\nThe array in reverse order is: ");
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}