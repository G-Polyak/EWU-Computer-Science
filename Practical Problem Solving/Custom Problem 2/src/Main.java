import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));
        char[][] game = new char[3][3];
        int xCount = 0;
        int oCount = 0;
        while (sc.hasNextLine()) {

            for (int i = 0; i < 3; i++) {
                game[i] = sc.nextLine().toCharArray();
            }
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            switch (findWinner(game)) {

                case 'X':
                    System.out.println("X Wins");
                    xCount++;
                    break;

                case 'O':
                    System.out.println("O Wins");
                    oCount++;
                    break;

                case 'S':
                    System.out.println("Scratch");
                    break;

            }

        }
        String s;
        if (xCount > oCount) {
            s = "X is the KING";
        } else if (oCount > xCount) {
            s = "O is the KING";
        } else {
            s = "It was a tie. This was all for nothing.";
        }
        System.out.println(s);

    }

    private static char findWinner(char[][] game) {

        //top row
        if ((game[0][0] == game[0][1]) && (game[0][1] == game[0][2]))
            return game[0][0];

        //middle row
        if ((game[1][0] == game[1][1]) && (game[1][1] == game[1][2]))
            return game[1][0];

        //bottom row
        if ((game[2][0] == game[2][1]) && (game[2][1] == game[2][2]))
            return game[2][0];

        //left column
        if ((game[0][0] == game[1][0]) && (game[1][0] == game[2][0]))
            return game[0][0];

        //middle column
        if ((game[0][1] == game[1][1]) && (game[1][1] == game[2][1]))
            return game[0][1];

        //right column
        if ((game[0][2] == game[1][2]) && (game[1][2] == game[2][2]))
            return game[0][2];

        //diagonal
        if ((game[0][0] == game[1][1]) && (game[1][1] == game[2][2]))
            return game[0][0];

        //other diagonal
        if ((game[0][2] == game[1][1]) && (game[1][1] == game[2][0]))
            return game[0][2];

        return 'S';

    }

}


