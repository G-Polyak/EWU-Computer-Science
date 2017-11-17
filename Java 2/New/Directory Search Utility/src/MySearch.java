//George Polyak
//CSCD 211-02
//No Extra-Credit Attempted

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MySearch {

    private static ArrayList<File> results = new ArrayList<File>();
    private static Scanner kb;

    public static void main(String args[]) {

        kb = new Scanner(System.in);
        System.out.println("Directory search by path, name, and extension.\n");
        System.out.print("Enter starting directory for the search (like c:\\temp): ");
        String directory = kb.nextLine().toLowerCase();
        System.out.print("Enter the file name (like myFile or enter for all): ");
        String name = kb.nextLine().toLowerCase();
        System.out.print("Enter the file extension (like txt or enter for all): ");
        String extension = kb.nextLine().toLowerCase();
        System.out.print("Enter content to search for (like cscd211 or enter for any): ");
        String content = kb.nextLine();

        try {
            search(new File(directory), name, extension, content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printResults();

    }

    private static void search(File file, String name, String ext, String content) {

        File list[] = file.listFiles();
        if (list != null) {

            for (File index : list) {

                if (index.isDirectory()) {
                    search(index, name, ext, content);
                }
                if (index.isFile()) {
                    addFile(index, name, ext, content);
                }

            }

        }

    }

    private static void addFile(File file, String name, String ext, String content) {

        String tempName = file.getName();
        int dot = tempName.indexOf(".");
        String searchName = tempName.substring(0, dot);
        String searchExt = tempName.substring(dot + 1, tempName.length());

        if (name.equals("") && ext.equals("") && content.equals("")) {
            results.add(file);
        } else if (name.equals(searchName) && ext.equals("") && content.equals("")) {
            results.add(file);
        } else if (name.equals("") && ext.equals(searchExt) && content.equals("")) {
            results.add(file);
        } else if (name.equals("") && ext.equals("") && !(content.equals(""))) {

            try {
                kb = new Scanner(file);
                if (kb.findWithinHorizon(content, 0) != null) {
                    results.add(file);
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

        } else if (name.equals(searchName) && ext.equals(searchExt)) {
            results.add(file);
        } else if (name.equals("") && ext.equals(searchExt) && !(content.equals(""))) {

            try {
                kb = new Scanner(file);
                if (kb.findWithinHorizon(content, 0) != null) {
                    results.add(file);
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

        } else if (name.equals(searchName) && ext.equals("") && !(content.equals(""))) {

            try {
                kb = new Scanner(file);
                if (kb.findWithinHorizon(content, 0) != null) {
                    results.add(file);
                }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

        }

    }

    private static void printResults() {

        System.out.println("\n");
        for (File file : results) {
            System.out.println(file.getPath());
        }
        System.out.println("\nResults - " + results.size() + " entries found");

    }

}
