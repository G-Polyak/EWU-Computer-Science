import java.util.*;

public class Interface {

    public static Scanner kb = new Scanner(System.in);

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static String readString() {

        kb.nextLine();
        return kb.nextLine();

    }

    public static char readChar() {

        while (!kb.hasNextLine()) {
            kb.nextLine();
            print("Try again: ");
        }
        return kb.next().charAt(0);

    }

    public static boolean readBoolean() {

        while (!(kb.nextLine().equalsIgnoreCase("true") || kb.nextLine().equalsIgnoreCase("false"))) {
            kb.next();
            print("Try again: ");
        }
        return kb.nextLine().equalsIgnoreCase("true");

    }

    public static int readInt() {

        while (!kb.hasNextInt()) {
            kb.next();
            print("Try again: ");
        }
        return kb.nextInt();

    }

    public static double readDouble() {

        while (!kb.hasNextDouble()) {
            kb.next();
            print("Try again: ");
        }
        return kb.nextDouble();

    }

}
