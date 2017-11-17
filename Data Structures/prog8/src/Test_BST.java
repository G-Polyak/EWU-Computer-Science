//George Polyak
//Prog8
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_BST {

    private static Scanner kb = new Scanner(System.in);
    private static BinarySearchTree BST = new BinarySearchTree();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        while (file.hasNextLine()) {
            BST.insert(Integer.parseInt(file.nextLine()));
        }

        menu();
        String choice = kb.nextLine();
        while (!choice.equals("x")) {

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
                case "5":
                    option5();
                    break;
                case "6":
                    option6();
                    break;
                case "7":
                    option7();
                    break;
                case "8":
                    option8();
                    break;
                case "9":
                    option9();
                    break;
                case "a":
                    optionA();
                    break;
                case "b":
                    optionB();
                    break;
                default:
                    System.out.print("Invalid choice, try again");
                    break;

            }
            menu();
            choice = kb.nextLine();

        }

    }

    private static void optionB() {

        int key = getKey();
        BinarySearchTree.BST_Node node = BST.search(key);
        boolean b = node == null;
        BinarySearchTree.BST_Node pred = null;
        if (!b) {
            pred = BST.findPredecessor(node);
        }
        if (b) {
            System.out.println("\nNo such key.");
        } else if (pred == null) {
            System.out.println("\nThe given key exists but has no predecessor.");
        } else {
            System.out.println(pred);
        }

    }

    private static void optionA() {

        int key = getKey();
        BinarySearchTree.BST_Node node = BST.search(key);
        boolean b = node == null;
        BinarySearchTree.BST_Node succ = null;
        if (!b) {
            succ = BST.findSuccessor(node);
        }
        if (b) {
            System.out.println("\nNo such key.");
        } else if (succ == null) {
            System.out.println("\nThe given key exists but has no successor.");
        } else {
            System.out.println(succ);
        }

    }

    private static void option9() {

        if (BST.size > 0) {
            System.out.println("\n" + BST.max(BST.root));
        } else {
            System.out.println("\nThe tree is currently empty.");
        }

    }

    private static void option8() {

        if (BST.size > 0) {
            System.out.println("\n" + BST.min(BST.root));
        } else {
            System.out.println("\nThe tree is currently empty.");
        }

    }

    private static void option7() {
        System.out.println();
        BST.levelOrderTraversal(BST.root);
    }

    private static void option6() {
        System.out.println();
        BST.postOrderTraversal(BST.root);
    }

    private static void option5() {
        System.out.println();
        BST.preOrderTraversal(BST.root);
    }

    private static void option4() {
        System.out.println();
        BST.inOrderTraversal(BST.root);
    }

    private static void option3() {

        int key = getKey();
        if (BST.delete(key) == null) {
            System.out.println("\nNo such key.");
        } else {
            System.out.println("\nThe given key has been deleted successfully.");
        }

    }

    private static void option2() {

        int key = getKey();
        if (BST.insert(key) == null) {
            System.out.println("\nThe given key already exists.");
        } else {
            System.out.println("\nThe given key has been inserted successfully.");
        }

    }

    private static void option1() {

        int key = getKey();
        if (BST.search(key) == null) {
            System.out.println("\nThe given key does not exist.");
        } else {
            System.out.println("\nThe given key exists.");
        }

    }

    private static void menu() {

        System.out.print("\nChoose one of the following options.\n" +
                "====================================\n" +
                "1) Search for a key\n" +
                "2) Insert a new key\n" +
                "3) Delete an existing key\n" +
                "4) Inorder traversal of the BST\n" +
                "5) Preorder traversal of the BST\n" +
                "6) Postorder traversal of the BST\n" +
                "7) Level-order traversal of the BST\n" +
                "8) Find the smallest key\n" +
                "9) Find the largest key\n" +
                "a) Find the successor of a given key\n" +
                "b) Find the predecessor of a given key\n" +
                "x) Quit\n" +
                "\nYour choice:");

    }

    private static int getKey() {

        System.out.print("\nInput the key:");
        int key = kb.nextInt();
        kb.nextLine();
        return key;

    }

}
