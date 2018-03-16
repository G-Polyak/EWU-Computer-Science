import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("officialInput.txt"));
        String history = "";
        int numPins;
        int maxPoints;
        float projectedPoints;
        while (!history.contains("#")) {

            history = in.nextLine();
            if (in.hasNextLine()) {

                numPins = in.nextInt();
                in.nextLine();
                maxPoints = findMaxPoints(numPins);
                projectedPoints = makeProjection(history, maxPoints);
                if (projectedPoints > maxPoints)
                    projectedPoints = maxPoints;
                System.out.printf("%d %d %.2f%%\n", maxPoints, (int) projectedPoints, 100 * (projectedPoints / maxPoints));

            }

        }

    }

    private static float makeProjection(String history, int maxPoints) {

        float score = 0;
        history = history.replaceAll(" ", "");
        char bowls[] = history.toCharArray();
        for (int i = 0; i < bowls.length; i++) {

            char c = bowls[i];
            switch (c) {

                case 'X':
                    score += 10;
                    if (i + 1 < bowls.length) {     //Add the next 2 bowls as well
                        score += getNumValue(bowls[i + 1]);
                        if (i + 2 < bowls.length) {
                            score += getNumValue(bowls[i + 2]);
                        }
                    }
                    break;

                case '/':
                    score += (10 - Character.getNumericValue(bowls[i - 1]));
                    if (i + 1 < bowls.length) {     //Add the next bowl as well
                        score += getNumValue(bowls[i + 1]);
                    }
                    break;

                default:
                    score += Character.getNumericValue(c);
                    break;

            }

        }
        return maxPoints * (score / 300); //this determines the actual projection

    }

    private static int getNumValue(char c) { //helper method

        switch (c) {

            case 'X':
                return 10;
            case '/':
                return 10;
            default:
                return Character.getNumericValue(c);

        }

    }

    private static int findMaxPoints(int numPins) { //numPins must be > 19

        int numTens = numPins / 10;
        int remaining = numPins % 10;
        return 30 * (numTens - 2) + (20 + remaining) + (10 + remaining) + remaining;
        //55 is 10 10 10 10 10 5 to start
        //55 is 30 30 30 25 15 5 after accounting for strikes

    }


}
