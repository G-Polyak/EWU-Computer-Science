/*
George Polyak
"WERTYU" Problem
UVa Online Judge tested the solution
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        String keyLayout = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
        while(kb.hasNext()){

            String s = kb.nextLine();
            for(int i = 0; i < s.length(); i++){

                if(s.charAt(i) == ' ') {
                    System.out.print(" ");
                } else {
                    System.out.print(keyLayout.charAt(keyLayout.indexOf(s.charAt(i)) - 1));
                }

            }
            System.out.println();

        }

    }

}