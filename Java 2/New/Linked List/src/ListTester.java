//George Polyak
//CSCD 211-02
//Extra-Credit Attempted

import java.util.Scanner;

public class ListTester {

    private static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        while (true) {

            printMenu();
            int i = readChoice();
            switch (i) {

                case 1:
                    if (list.getSize() > 0) {
                        list.clear();
                    }
                    list = generateRandList();
                    break;
                case 2:
                    list.sort();
                    break;
                case 3:
                    System.out.print(list);
                    break;
                case 4:
                    LinkedList.printReverse(list);
                    break;
                case 5:
                    LinkedList evens = generateEvenList(list);
                    System.out.print(evens);
                    break;
                case 6:
                    int n = readNum(true);
                    LinkedList.printNthNodes(list, n);
                    break;
                case 7:
                    Integer value = readNum(false);
                    list.remove(value);
                    break;
                case 8:
                    list.clear();
                    break;

            }
            if (i == 9) {
                break;
            }

        }

    }

    private static void printMenu() {

        System.out.println("\n1. Create new LinkedList\n2. Sort List\n3. Print List\n" +
                "4. Print List in reverse\n5. Generate/Display even #s in List\n" +
                "6. Print every Nth # in List\n7. Delete entries containing N\n" +
                "8. Delete all entries in List\n9. Quit\n");

    }

    private static LinkedList generateRandList() {

        int size = 0;
        while (true) {

            try {

                System.out.print("Enter size: ");
                while (!kb.hasNextInt()) {
                    kb.next();
                    System.out.print("Incorrect format, try again\nEnter size: ");
                }
                size = kb.nextInt();
                kb.nextLine();
                if (size < 0 || size > 9999) {
                    throw new Exception();
                }
                break;

            } catch (Exception e) {
                System.out.println("Choice out of bounds, try again");
            }

        }

        LinkedList temp = new LinkedList();
        for (int i = 0; i < size; i++) {
            temp.add((int) (Math.random() * 100));
        }
        return temp;

    }

    private static LinkedList generateEvenList(LinkedList list) {

        LinkedList evens = new LinkedList();
        for (int i = 0; i < list.getSize(); i++) {

            if (list.get(i) % 2 == 0) {
                evens.add(list.get(i));
            }

        }
        return evens;

    }


    private static int readChoice() {

        int i = 0;
        while (true) {

            try {

                System.out.print("Enter choice: ");
                while (!kb.hasNextInt()) {
                    kb.next();
                    System.out.print("Incorrect format, try again\nEnter choice: ");
                }
                i = kb.nextInt();
                kb.nextLine();
                if (i < 0 || i > 9) {
                    throw new Exception();
                }
                break;

            } catch (Exception e) {
                System.out.println("Choice out of bounds, try again");
            }

        }
        return i;

    }

    private static int readNum(boolean sixORseven) {

        int i = 0;
        if (sixORseven) {

            while (true) {

                try {

                    System.out.print("Enter N: ");
                    while (!kb.hasNextInt()) {
                        kb.next();
                        System.out.print("Incorrect format, try again\nEnter N: ");
                    }
                    i = kb.nextInt();
                    kb.nextLine();
                    if (i < 0) {
                        throw new Exception();
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Choice out of bounds, try again");
                }

            }

        } else {

            System.out.print("Enter value to delete: ");
            while (!kb.hasNextInt()) {

                kb.next();
                System.out.print("Incorrect format, try again\n" +
                        "Enter value to delete: ");

            }
            i = kb.nextInt();
            kb.nextLine();

        }
        return i;

    }

}
