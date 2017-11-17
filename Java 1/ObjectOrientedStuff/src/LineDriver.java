//George Polyak
//Assignment 7 - Lines and Points
//3-2-2016

import java.util.*;

public class LineDriver {

    public static void main(String[] args) {

        int input = 0;
        Scanner kb = new Scanner(System.in);
        Line lineOne = new Line();
        Line lineTwo = new Line();

        while (input != 6) {

            System.out.println("1. Enter coordinates, width and color for first line");
            System.out.println("2. Enter coordinates, width and color for second line");
            System.out.println("3. Compare the two lines");
            System.out.println("4. Display coordinates, width, length and color for first line");
            System.out.println("5. Display coordinates, width, length and color for second line");
            System.out.println("6. Quit");
            System.out.print("Enter choice: ");
            input = readInt(kb);

            while (input < 1 || input > 6) {

                System.out.println("Please enter valid choice");
                System.out.print("Enter choice: ");
                input = readInt(kb);

            }

            switch (input) {

                case 1:

                    System.out.println("Enter information for first line");
                    lineOne = createNewLine(kb);

                    break;

                case 2:

                    System.out.println("Enter information for second line");
                    lineTwo = createNewLine(kb);

                    break;

                case 3:

                    if (lineOne.equals(lineTwo)) {
                        System.out.println("Lines are the same");
                    } else {
                        System.out.println("Lines are not the same");
                    }

                    break;

                case 4:

                    System.out.println(lineOne.toString());

                    break;

                case 5:

                    System.out.println(lineTwo.toString());

                    break;

            }

        }

        System.out.println("Goodbye");

    }

    public static int readInt(final Scanner kb) {

        while (!kb.hasNextInt()) {
            kb.next();
        }

        int out = kb.nextInt();
        return out;

    }

    public static Line createNewLine(final Scanner kb) {

        int x1 = -1, y1 = -1, y2 = -1, x2 = -1, width = -1;
        String color = "";

        while (!Line.validateLine(x1, y1)) {

            System.out.print("Enter x1:");
            x1 = readInt(kb);

            System.out.print("Enter y1:");
            y1 = readInt(kb);

            if (!Line.validateLine(x1, y1)) {
                System.out.println("Invalid point");
            }

        }

        while (!Line.validateLine(x2, y2)) {

            System.out.print("Enter x2:");
            x2 = readInt(kb);

            System.out.print("Enter y2:");
            y2 = readInt(kb);

            if (!Line.validateLine(x2, y2)) {
                System.out.println("Invalid point");
            }

        }

        while (width < 1) {

            System.out.print("Enter line width:");
            width = readInt(kb);

            if (width < 1) {
                System.out.println("invalid width");
            }

        }

        while (color.equalsIgnoreCase("")) {

            System.out.print("Enter color: ");
            kb.nextLine();
            color = kb.nextLine();

        }

        return new Line(x1, y1, x2, y2, width, color);

    }

}