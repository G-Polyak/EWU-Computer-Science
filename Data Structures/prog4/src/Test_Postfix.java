//George Polyak
//Prog4
//CSCD300

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test_Postfix {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> postfix = new ArrayList<String>();
        Scanner file = new Scanner(new File(args[0]));
        while(file.hasNextLine()) {
            postfix.add(file.nextLine());
        }
        calculate(postfix);

    }

    private static void calculate(ArrayList<String> postfix) {

        SLinkedList list = new SLinkedList();
        for(String s : postfix) {

            if(Character.isDigit(s.charAt(0))) {
                list.push(s);
            } else {

                int second = Integer.parseInt(list.pop());
                int first = Integer.parseInt(list.pop());
                int third;
                if(s.equals("+")) {
                    third = first + second;
                } else if(s.equals("-")) {
                    third = first - second;
                } else if(s.equals("*")) {
                    third = first * second;
                } else {
                    third = first / second;
                }
                list.push(Integer.toString(third));

            }

        }
        System.out.println(list.peek());

    }

}
