//  George Polyak
//  Rental rates assignment
//  Extra Credit attempted
//  Pre-set main for testing (see DEBUG constant)

//  Required methods to be added:  calcAge(...), calcRateClass(...) and displayResult(...)
//  Also, insert code into main as indicated.

import java.util.*;

public class RentalRates {
    private static final boolean DEBUG = true;

    private static final String BEST_RATE = "Best rate - $40.00 per day or $200.00 per week.";
    private static final String RISK_RATE_1 = "Risk rate 1-$50.00 per day or $255.00 per week.";
    private static final String RISK_RATE_2 = "Risk rate 2-$57.00 per day or $285.00 per week.";
    private static final String RISK_RATE_3 = "Risk rate 3-$%4.2f per day or $%5.2f per week.";

    public static void main(String[] args) {
        int curMonth = 0;
        int curDay = 0;
        int curYear = 0;
        int birthMonth = 0;
        int birthDay = 0;
        int birthYear = 0;
        String gender = "";
        int age = 0;
        String rateResult;

        // Testing mode...
        if (DEBUG == true) {
            // Establish a 'current' date for testing...
            curMonth = 2;
            curDay = 1;
            curYear = 2016;

            System.out.println("First test case: Renter is not old enough to rent...");
            birthMonth = 2;
            birthDay = 2;
            birthYear = 1991;
            gender = "m";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nSecond test case: Renter is barely old enough (57/285)...");
            birthMonth = 2;
            birthDay = 1;
            birthYear = 1991;
            gender = "m";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nThird test case: Renter is 35 and male (40/200)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1981;
            gender = "m";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nFourth test case: Renter is 35 and female (40/200)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1981;
            gender = "f";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nFifth test case: Renter is 30 and male (57/285)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1986;
            gender = "m";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nSixth test case: Renter is 30 and female (40/200)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1986;
            gender = "f";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nSeventh test case: Renter is 76 and male (62/255)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1940;
            gender = "m";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);

            System.out.println("\nEighth test case: Renter is 76 and female (68/270)...");
            birthMonth = 1;
            birthDay = 1;
            birthYear = 1940;
            gender = "f";
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);
            rateResult = calcRateClass(age, gender);
            displayResults(gender, age, rateResult);
        } else {
            Scanner kb = new Scanner(System.in);
            System.out.println("Welcome to the car renter's rate finder.");

            // If you're not attempting the EC, get today's date from the user...
            //    Your code goes here...

            // If you are attempting the EC, use the Calendar class to get today's date...
            //    Your code goes here...
            Calendar now = Calendar.getInstance();
            curMonth = now.get(Calendar.MONTH) + 1;
            curDay = now.get(Calendar.DAY_OF_MONTH);
            curYear = now.get(Calendar.YEAR);
            System.out.println("The current date is: " + curMonth + "/" + curDay + "/" + curYear);

            // Get the gender...
            //    Your code goes here...
            boolean didWork = false;
            while (!didWork) {
                System.out.print("Please enter the renter's gender (m/f): ");
                gender = kb.nextLine().toLowerCase();
                if (gender.startsWith("m")) {
                    gender = "m";
                    didWork = true;
                } else if (gender.startsWith("f")) {
                    gender = "f";
                    didWork = true;
                }
            }

            // Get the date of birth...
            //    Your code goes here...
            boolean didWork2 = false;
            while (!didWork2) {
                System.out.print("Please enter the renter's date of birth (mm dd yyyy): ");
                try {
                    String input = kb.nextLine();
                    String[] values = input.split(" ");
                    birthMonth = Integer.parseInt(values[0]);
                    birthDay = Integer.parseInt(values[1]);
                    birthYear = Integer.parseInt(values[2]);
                    didWork2 = true;
                } catch (Exception e) {
                }
            }

            // Get age...
            age = calcAge(curMonth, curDay, curYear, birthMonth, birthDay, birthYear);

            // Get the rental rate...
            rateResult = calcRateClass(age, gender);

            // Display the results...
            displayResults(gender, age, rateResult);
        }
    }

    private static int calcAge(int cMonth, int cDay, int cYear, int bMonth, int bDay, int bYear) {

        int age = cYear - bYear;
        if (cMonth >= bMonth && cDay >= bDay) {
            return age;
        }
        return age - 1;

    }

    private static String calcRateClass(int age, String gndr) {

        if (gndr.equals("m")) {
            if (age >= 33 && age <= 65) {
                return BEST_RATE;
            } else if (age < 33 && age >= 25) {
                return RISK_RATE_2;
            } else if (age > 65) {
                return RISK_RATE_3;
            } else {
                return "Sorry, the renter is not 25 years of age or older.";
            }
        } else {
            if (age >= 30 && age <= 62) {
                return BEST_RATE;
            } else if (age < 30 && age >= 25) {
                return RISK_RATE_1;
            } else if (age > 62) {
                return RISK_RATE_3;
            } else {
                return "Sorry, the renter is not 25 years of age or older.";
            }
        }

    }

    private static void displayResults(String gndr, int age, String rate) {

        if (gndr.equals("m")) {
            gndr = "male";
        } else {
            gndr = "female";
        }

        System.out.println("\nThank you.");
        System.out.println("The " + gndr + " renter is " + age + " years old.");
        System.out.println("The rate class is: " + rate);

    }
}