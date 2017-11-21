//George Polyak
//Prog3
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_RoundRobin {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        int serviceLength = Integer.parseInt(args[1]);
        CSLinkedList list = new CSLinkedList();

        while (file.hasNextLine()) {
            String s = file.nextLine();
            String temp[] = s.split(",");
            list.addByID(new CSLinkedList.Node(Integer.parseInt(temp[0]),
                    Integer.parseInt(temp[1])));
        }

        while(!list.isEmpty()) {
            list.goToStart();
            roundRobin(list, serviceLength);
        }

    }

    private static void roundRobin(CSLinkedList list, int serviceLength) {

        for(int i = 0; i < list.getSize(); i++) {

            list.cursor.serviceTime -= serviceLength;
            if(list.cursor.serviceTime <= 0) {
                System.out.println(list.removeAtCursor().id);
            }

        }

    }

}
