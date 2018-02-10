/*
George Polyak
Given coordinates of the top-left and bottom-right points of 2 rectangles,
this program reports the top-left and bottom-right points of the rectangle
created from the cross section of the other 2 rectangles, if they overlap.
The test file tests all min and max combinations of coordinates as well as
cases where the points for the 2 rectangles where off by 1 in either direction
as well as a couple "normal" examples.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WindowManager {

    public static void main(String[] args) throws FileNotFoundException  {

        //Scanner kb = new Scanner(System.in);
        Scanner kb = new Scanner(new File("input.txt"));
        while (true) {

            int[] rect1 = getCoords("first", kb);
            System.out.println();
            int[] rect2 = getCoords("second", kb);
            reportOverlap(rect1, rect2);
            System.out.print("\nEnter 'y' to try another set of rectangles, any other character to quit: ");
            if (!kb.nextLine().toLowerCase().equals("y")) {
                break;
            }

        }
        System.out.print("\nGoodbye!");

    }

    private static void reportOverlap(int[] rect1, int[] rect2) {

        int firstUpLeftX = rect1[0]; //these were so I wouldn't get confused as much
        int firstUpLeftY = rect1[1];
        int firstLowRightX = rect1[2];
        int firstLowRightY = rect1[3];
        int secondUpLeftX = rect2[0];
        int secondUpLeftY = rect2[1];
        int secondLowRightX = rect2[2];
        int secondLowRightY = rect2[3];

        if (firstUpLeftX >= secondLowRightX || firstUpLeftY >= secondLowRightY) {
            System.out.print("\nThese rectangles do not overlap!");
        } else if (firstLowRightX < secondUpLeftX || firstLowRightY < secondUpLeftY) {
            System.out.print("\nThese rectangles do not overlap!");
        } else {

            int[] cross = {Math.max(firstUpLeftX, secondUpLeftX), Math.min(firstUpLeftY, secondUpLeftY),
                    Math.min(firstLowRightX, secondLowRightX), Math.max(firstLowRightY, secondLowRightY)};
            printNums(rect1, rect2, cross);

        }

    }

    private static int[] getCoords(String numRect, Scanner kb) throws FileNotFoundException {

        int[] rect = new int[4];
        System.out.printf("\nEnter upper left X coordinate of %s rectangle: ", numRect);
        rect[0] = kb.nextInt();
        kb.nextLine();
        System.out.printf("\nEnter upper left Y coordinate of %s rectangle: ", numRect);
        rect[1] = kb.nextInt();
        kb.nextLine();
        System.out.printf("\nEnter lower right X coordinate of %s rectangle: ", numRect);
        rect[2] = kb.nextInt();
        kb.nextLine();
        System.out.printf("\nEnter lower right Y coordinate of %s rectangle: ", numRect);
        rect[3] = kb.nextInt();
        kb.nextLine();
        printRect(rect);
        return rect;

    }

    private static void printRect(int[] rect) {
        System.out.printf("\n(%d, %d), (%d,%d)\n", rect[0], rect[1], rect[2], rect[3]);
    }

    private static void printNums(int[] rect1, int[] rect2, int[] cross) {

        System.out.print("\nThe rectangles with corner coordinates: ");
        printRect(rect1);
        System.out.print("and");
        printRect(rect2);
        System.out.print("overlap at: ");
        printRect(cross);

    }

}
