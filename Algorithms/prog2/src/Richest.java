//George Polyak
//Prog2
//CSCD320

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Richest {

    public static void main(String[] args) throws IOException {

        Scanner file = new Scanner(new File(args[0]));
        MinHeap heap = new MinHeap(10000);
        for (int i = 0; i < 10000; i++) {
            heap.insert(Integer.parseInt(file.nextLine()));
        }
        int temp;
        while (file.hasNextLine()) {

            temp = Integer.parseInt(file.nextLine());
            if(temp > heap.heapMin()) {
                heap.replaceMin(temp);
            }

        }
        heap.heapSort();
        PrintWriter printer = new PrintWriter("richest_output.txt", "UTF-8");
        for (int i : heap.getHeapArray()) {
            printer.println(i);
        }

    }

}
