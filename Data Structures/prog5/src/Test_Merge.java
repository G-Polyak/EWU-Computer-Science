//George Polyak
//Prog5
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test_Merge {

    public static void main(String[] args) throws FileNotFoundException {

        FIFO_Array[] setOfFiles = new FIFO_Array[args.length];
        FIFO_LinkedList list = new FIFO_LinkedList();
        Integer[] heads = new Integer[args.length];
        Scanner file;
        FIFO_Array temp;
        ArrayList<Integer>[] sorter = new ArrayList[args.length];
        for (int i = 0; i < args.length; i++) {

            temp = new FIFO_Array();
            sorter[i] = new ArrayList<>();
            file = new Scanner(new File(args[i]));
            while (file.hasNextLine()) {
                sorter[i].add(file.nextInt());
            }
            Collections.sort(sorter[i]);
            int tempSize = sorter[i].size();
            for (int j = 0; j < 10; j++) {

                if (tempSize > j) {

                    temp.enqueue(sorter[i].get(0));
                    sorter[i].remove(0);

                }

            }
            setOfFiles[i] = temp;

        }

        while (!allEmpty(setOfFiles)) {

            for (int i = 0; i < heads.length; i++) {
                heads[i] = setOfFiles[i].getTop();
            }
            int holder = 0;
            for (int i = 1; i < heads.length; i++) {

                if (heads[holder] != null && heads[i] != null && heads[holder] > heads[i]) {
                    holder = i;
                }
                if (heads[holder] == null && heads[i] != null) {
                    holder = i;
                }

            }
            list.enqueue(setOfFiles[holder].dequeue());
            if (!sorter[holder].isEmpty()) {

                setOfFiles[holder].enqueue(sorter[holder].get(0));
                sorter[holder].remove(0);

            }

        }
        while (!list.isEmpty()) {
            System.out.println(list.dequeue());
        }

    }

    private static boolean allEmpty(FIFO_Array[] setOfFiles) {

        int counter = 0;
        for (FIFO_Array item : setOfFiles) {

            if (item.size() == 0) {
                counter++;
            }

        }
        return counter == setOfFiles.length;

    }

}
