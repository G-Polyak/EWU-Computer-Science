//George Polyak
//Prog7
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_HashChain {

    private static Scanner kb = new Scanner(System.in);
    private static ChainHashMap hashMap;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        String a[];
        String s;
        hashMap = new ChainHashMap(5);
        while(file.hasNextLine()) {

            s = file.nextLine();
            a = s.split(",");
            hashMap.put(Integer.parseInt(a[0]), a[1]);

        }

        menu();
        String choice = kb.nextLine();
        while(!choice.equals("5")) {

            switch (choice) {

                case "1":
                    option1();
                    break;
                case "2":
                    option2();
                    break;
                case "3":
                    option3();
                    break;
                case "4":
                    option4();
                    break;
                default:
                    System.out.print("Invalid choice, try again");
                    break;

            }
            menu();
            choice = kb.nextLine();

        }



    }

    private static void option4() {
        System.out.println(hashMap);
    }

    private static void option3() {

        System.out.print("\nInput the student id:");
        int id = kb.nextInt();
        kb.nextLine();
        String s = hashMap.get(id);
        if(s != null) {
            System.out.println("\nStudent id:" + id + ". Student name:" + s + ".");
        } else {
            System.out.println("\nNo such a student.");
        }

    }

    private static void option2() {

        System.out.print("\nInput the student id:");
        int id = kb.nextInt();
        kb.nextLine();
        String temp = hashMap.remove(id);
        if(temp != null) {
            System.out.println("\nThe student has been deleted successfully.");
        } else {
            System.out.println("\nNo such a student.");
        }

    }

    private static void option1() {

        System.out.print("\nInput the student id:");
        int id = kb.nextInt();
        kb.nextLine();
        System.out.print("\nInput the student name:");
        String name = kb.nextLine();
        String temp = hashMap.get(id);
        hashMap.put(id, name);
        if(temp != null) {
            System.out.println("\nThe student was exiting and the record has been updated.");
        } else {
            System.out.println("\nThe new student has been added successfully.");
        }

    }

    private static void menu() {

        System.out.print("\nChoose one of the following options.\n" +
                "====================================\n" +
                "1) insert/update a new student record\n" +
                "2) delete a student record\n" +
                "3) search for a student record\n" +
                "4) print all the student records\n" +
                "5) quit\n" +
                "\nYour choice:");

    }

}
