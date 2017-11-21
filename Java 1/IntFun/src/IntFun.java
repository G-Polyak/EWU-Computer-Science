//George Polyak
//Assignment 3 - Menu Driven Application
//Extra Credit attempted

import java.util.*;

public class IntFun
{

    public static void main(String[] args)
    {

        int number = 0;
        int choice = 0;
        boolean b = true;

        Scanner kb = new Scanner(System.in);

        System.out.println("Welcome to Integer Fun");
        System.out.print("Please enter a non-negative integer -->");
        number = kb.nextInt();
        kb.nextLine();

        while(number < 0)
        {

            System.out.print("\nThat is not a non-negative integer\nPlease enter a non-negative integer -->");
            number = kb.nextInt();
            kb.nextLine();

        }

        displayMenu();
        choice = kb.nextInt();
        kb.nextLine();

        while(choice < 1 || choice > 5)
        {

            System.out.println("That is an invalid menu choice\nPlease try again");
            displayMenu();
            choice = kb.nextInt();
            kb.nextLine();

        }

        while(b)
        {

            if (choice == 1)
            {

                System.out.print("Please enter a non-negative integer -->");
                number = kb.nextInt();
                kb.nextLine();

                while(number < 0)
                {

                    System.out.print("\nThat is not a non-negative integer\nPlease enter a non-negative integer -->");
                    number = kb.nextInt();
                    kb.nextLine();

                }

                displayMenu();
                choice = kb.nextInt();
                kb.nextLine();

            } else if (choice == 2)
            {

                option2(number);

                displayMenu();
                choice = kb.nextInt();
                kb.nextLine();


            } else if (choice == 3)
            {

                option3(number);

                displayMenu();
                choice = kb.nextInt();
                kb.nextLine();

            } else if (choice == 4)
            {

                option4(number);

                displayMenu();
                choice = kb.nextInt();
                kb.nextLine();

            } else
            {

                System.out.println("Goodbye");
                b = false;

            }

        }

    }

    private static void option4(int num)
    {

        int sum = 0;

        while (num != 0)
        {

            sum += num % 10;
            num /= 10;

        }

        System.out.println("Your results are:\n" + sum);

    }

    private static void option3(int num)
    {

        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= num; i++)
        {

            int counter = 0;

            for(int i2 = i; i2 >= 1; i2--)
            {

                if(i % i2 == 0)
                {

                    counter = counter + 1;

                }

            }

            if (counter == 2)
            {

                primes.add(i);

            }
        }

        primes.remove(0);
        System.out.println("Your results are:\n" + primes);

    }

    private static void option2(int num)
    {

        int zeroCount = 0;
        int evenCount = 0;
        int oddCount = 0;

        while (num > 0)
        {
            if ((num%10)==0)
            {
                zeroCount++;
            }
            else if (num%2==0)
            {
                evenCount++;
            }
            else
            {
                oddCount++;
            }
            num /= 10;
        }

        System.out.println("Your results are:\nOdd - " + oddCount + "\nEven - " + evenCount + "\nZero(s) - " + zeroCount);

    }

    private static void displayMenu()
    {

        System.out.println("\nPlease select from the following menu choices");
        System.out.println("\n1. Enter a new number");
        System.out.println("2. Print the number of odd, even, and zero digits in the integer");
        System.out.println("3. Print the prime numbers between 2 and the integer");
        System.out.println("4. Print the sum of the digits of the integer");
        System.out.println("5. Quit the program\n");
        System.out.print("Choice -->");

    }


}