//George Polyak
//Prog6
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_ListQuickSort {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        DLinkedList list = new DLinkedList();
        while (file.hasNextLine()) {
            list.add(Integer.parseInt(file.nextLine()));
        }
        list.sort();
        System.out.print(list);

    }

}
