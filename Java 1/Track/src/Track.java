//George Polyak
//Assignment 1 - Track Times

import java.util.*;

public class Track {
    public static void main(String[] args) {

        Scanner kb1 = new Scanner(System.in);

        double mps;
        double fps;
        double kph;
        double mph;
        double minMile;

        int mins;
        double secs;

        double sec100yards;

        System.out.print("Please enter the winning time of the race: ");
        double winTime = kb1.nextDouble();
        kb1.nextLine();

        mps = getMps(winTime);
        fps = getFps(winTime);
        kph = getKph(winTime);
        mph = getMph(winTime);

        minMile = 60 * (1 / mph);
        mins = (int) minMile;
        secs = 60 * (minMile % mins);
        sec100yards = 300 / fps;

        System.out.println("The person was traveling at a rate of: ");
        System.out.printf("%10.2f meters per second, \n", mps);
        System.out.printf("%10.2f feet per second, \n", fps);
        System.out.printf("%10.2f kilometers per hour, \n", kph);
        System.out.printf("%10.2f miles per hour \n", mph);
        System.out.print("It would take " + mins + " minutes and ");
        System.out.printf("%.2f", secs);
        System.out.println(" seconds for the person to run one mile.");
        System.out.print("It would take ");
        System.out.printf("%.2f", sec100yards);
        System.out.println(" seconds for the person to run 100 yards.");


    }

    private static double getMps(double winTime) {

        return 100 / winTime;

    }

    private static double getFps(double winTime) {

        return (100 / winTime) * 3.28084;

    }

    private static double getKph(double winTime) {

        return (100 / winTime) * 3.6;

    }

    private static double getMph(double winTime) {

        return (100 / winTime) * 2.23694;

    }

}