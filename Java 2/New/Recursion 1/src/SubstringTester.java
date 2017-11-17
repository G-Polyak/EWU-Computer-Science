//George Polyak
//CSCD 211-02

import java.util.Scanner;

public class SubstringTester {

    public static void main(String args[]) {

       /* Scanner kb = new Scanner(System.in);
        String s;
        while(true) {

            System.out.print("Enter a string: ");
            s = kb.nextLine();
            forward(s);
            System.out.println("");
            System.out.println("Quit?");
            s = kb.nextLine();
            if(s.toLowerCase().startsWith("y")) {
                break;
            }*/



    }

    private static void backward(String phrase) {

        if (phrase.length() == 1) {
            System.out.println(phrase);
        }else{

            System.out.println(phrase);
            backward(phrase.substring(0, phrase.length()-1));

        }

    }

    private static void forward(String phrase) {

        if(phrase.length() > 0) {

            backward(phrase);
            forward(phrase.substring(1));

        }

    }

}
