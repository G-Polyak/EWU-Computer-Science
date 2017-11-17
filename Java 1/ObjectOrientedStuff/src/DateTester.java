//George Polyak
//Assignment 8 - Final Composition and File Handling
//3-14-2016

import java.io.*;
import java.util.*;

public class DateTester {

    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in);
        Scanner fileScanner;
        String filename;

        while (true) {
            System.out.print("Please enter the filename: ");
            try {
                filename = kb.nextLine();
                fileScanner = FileUtil.openInputFile(filename);
                break;
            } catch (Exception e) {
                System.out.println("Invalid filename");
            }
        }


        int count = 0;
        while (fileScanner.hasNextLine()) {
            count++;
        }
        fileScanner.close();

        Date[] dates = new Date[count];
        fileScanner = new Scanner(filename);
        count = 0;
        while (fileScanner.hasNextLine()) {
            String[] s = fileScanner.nextLine().split("/");
            dates[count] = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            count++;
        }
        fileScanner.close();

        SortSearchUtil.selectionSort(dates);

        int choice = 0;
        while (choice != 5) {
            choice = getMenuChoice(kb);
            performChoice(choice, dates, kb);
        }
        System.out.println("Goodbye");


    }

    private static void displayMenu() {
        System.out.println("\nPlease select from the following menu choices");
        System.out.println("\n1. Print the array of Dates to the screen or a file");
        System.out.println("2. Search for a Date");
        System.out.println("3. Add a Date to the array");
        System.out.println("4. Delete a Date from the array");
        System.out.println("5. Quit the program\n");
        System.out.print("Choice -->");
    }

    public static int getMenuChoice(Scanner kb) {
        int choice;
        do {
            displayMenu();
            while (!kb.hasNextInt()) {
                kb.next();
                System.out.println("That is an invalid menu choice\nPlease try again");
            }
            choice = kb.nextInt();
            kb.nextLine();
            if (choice < 1 || choice > 7)
                System.out.println("That is an invalid menu choice\nPlease try again");
        } while (choice < 1 || choice > 5);
        return choice;
    }

    public static void performChoice(int choice, Date[] dates, Scanner kb) throws IOException {
        switch (choice) {
            case 1:
                option1(dates, kb);
                break;
            case 2:
                option2(dates, kb);
                break;
            case 3:
                dates = option3(dates, kb);
                break;
            case 4:
                dates = option4(dates, kb);
                break;
        }
    }

    private static void option1(Date[] dates, Scanner kb) throws IOException {

        PrintWriter fout;
        String filename;
        System.out.println("Would you like to print to the screen or to a file?");
        System.out.print("Enter here -->");
        String s = kb.nextLine();
        if (s.toLowerCase().contains("screen")) {
            for (int i = 0; i < dates.length; i++) {
                System.out.println(dates[i]);
            }
        }
        if (s.toLowerCase().contains("file")) {

            while (true) {
                System.out.print("Please enter the filename: ");
                try {
                    filename = kb.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid filename");
                }
            }
            fout = new PrintWriter(new File(filename));
            for (int i = 0; i < dates.length; i++) {
                fout.println(dates[i]);
            }
            fout.close();

        }

    }

    private static void option2(Date[] dates, Scanner kb) {

        System.out.println("Please enter the date you wish to search for");
        System.out.print("Enter here -->");
        String s = kb.nextLine();
        int i = SortSearchUtil.linearSearch(dates, s);
        System.out.println("The index of the array that your date was found is: " + i);

    }

    private static Date[] option3(Date[] dates, Scanner kb) {

        Date newDate = null;
        System.out.println("Please enter the date you wish to add to the array");
        System.out.print("Enter here -->");
        String[] s = kb.nextLine().split("/");
        if (Date.isValid(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]))) {
            newDate = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        }
        Date[] newDates = new Date[dates.length+1];
        for(int i = 0; i < dates.length; i++) {
            newDates[i] = dates[i];
        }
        newDates[dates.length+1] = newDate;
        SortSearchUtil.selectionSort(newDates);
        return newDates;

    }

    private static Date[] option4(Date[] dates, Scanner kb) {

        Date tempDate = null;
        System.out.println("Please enter the date you wish to remove to the array");
        while (true) {
            System.out.print("Enter here -->");
            String[] s = kb.nextLine().split("/");
            if (Date.isValid(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]))) {
                tempDate = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            }
            int a = SortSearchUtil.linearSearch(dates, tempDate);
            if(a < 0) {
                System.out.println("That date is not in the array");
            } else {
                break;
            }
        }

        Date[] newDates = new Date[dates.length-1];
        for(int i = 0; i < dates.length-1; i++) {
            newDates[i] = dates[i];
        }
        return newDates;

    }

}