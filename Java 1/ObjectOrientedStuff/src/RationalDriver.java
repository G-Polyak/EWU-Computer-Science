//George Polyak
//Assignment 6 - Rational Objects
//2-17-2016

import java.util.*;

public class RationalDriver
{

    public static void main(String[] args)
    {

        Rational[] rats = new Rational[6];
        rats[0] = new Rational(2, 3);
        rats[1] = new Rational(2, 18);
        rats[2] = new Rational(3, 12);
        rats[3] = new Rational(9, 3);
        rats[4] = new Rational(2, 5);
        rats[5] = new Rational(22, 7);

        Scanner kb = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            choice = getMenuChoice(kb);
            performChoice(choice, rats, kb);
        }
        System.out.println("Goodbye");


    }

    private static void displayMenu() {
        System.out.println("\nPlease select from the following menu choices");
        System.out.println("\n1. Display the value of a Rational object");
        System.out.println("2. Change the value of a Rational object");
        System.out.println("3. Display the sum of two Rational objects");
        System.out.println("4. Display the difference of two Rational objects");
        System.out.println("5. Sort the array of Rational objects");
        System.out.println("6. Print the array of Rational objects");
        System.out.println("7. Quit the program\n");
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
            if (choice < 1 || choice > 7)
                System.out.println("That is an invalid menu choice\nPlease try again");
        } while (choice < 1 || choice > 7);
        return choice;
    }

    public static void performChoice(int choice, Rational[] rats, Scanner kb) {
        switch (choice) {
            case 1:
                while (true) {
                    System.out.print("\nPlease enter the index of the Rational object you wish to see\n-->");
                    try {
                        int index = kb.nextInt();
                        kb.nextLine();
                        System.out.println("The value of that Rational object is: " + rats[index]);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("That is an invalid index entry");
                    }
                }
                break;
            case 2:
                rats = option2(rats, kb);
                break;
            case 3:
                option3(rats, kb);
                break;
            case 4:
                option4(rats, kb);
                break;
            case 5:
                rats = option5(rats);
                break;
            case 6:
                option6(rats);
                break;
        }
    }


    private static Rational[] option2(Rational[] rats, Scanner kb) {


        int n, d;
        int index;

        while (true) {
            System.out.print("\nPlease enter the index of the Rational object you wish to change\n-->");
            try {
                index = kb.nextInt();
                kb.nextLine();
                System.out.println(rats[index]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid index entry");
            }
        }

        System.out.print("\nPlease enter the new numerator\n-->");
        n = kb.nextInt();
        kb.nextLine();
        System.out.print("\nPlease enter the new denominator\n-->");
        d = kb.nextInt();
        kb.nextLine();

        Rational newRat = new Rational(n, d);
        rats[index] = newRat;

        return rats;

    }

    private static void option3(Rational[] rats, Scanner kb) {

        int index1, index2;

        while (true) {
            System.out.print("\nPlease enter the index of the first Rational object you wish to add\n-->");
            try {
                index1 = kb.nextInt();
                kb.nextLine();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid index entry");
            }
        }
        while (true) {
            System.out.print("\nPlease enter the index of the second Rational object you wish to add\n-->");
            try {
                index2 = kb.nextInt();
                kb.nextLine();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid index entry");
            }
        }

        Rational newRat = Rational.add(rats[index1], rats[index2]);

        System.out.println("The sum of those two Rational objects is: " + newRat);

    }

    private static void option4(Rational[] rats, Scanner kb) {

        int index1, index2;

        while (true) {
            System.out.print("\nPlease enter the index of the Rational object you wish to subtract from\n-->");
            try {
                index1 = kb.nextInt();
                kb.nextLine();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid index entry");
            }
        }
        while (true) {
            System.out.print("\nPlease enter the index of the Rational object you wish to subtract\n-->");
            try {
                index2 = kb.nextInt();
                kb.nextLine();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("That is an invalid index entry");
            }
        }

        Rational newRat = Rational.sub(rats[index1], rats[index2]);

        System.out.println("The difference of those two Rational objects is: " + newRat);

    }

    private static Rational[] option5(Rational[] rats) {

        SortSearchUtil.selectionSort(rats);
        return rats;

    }

    private static void option6(Rational[] rats) {

        System.out.println("The current array of Rational objects is:");
        for( int i = 0; i < rats.length; i++)
        {
            System.out.print(rats[i] + " ");
        }

    }

}