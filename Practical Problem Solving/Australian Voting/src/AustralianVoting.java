/*
George Polyak
"Australian Voting" Problem
The input file tests for the min and max number of candidates
as well as the min and max number of chars in a candidate name.
It also tests for the min and max number of ballots, along with
typical input.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class AustralianVoting {

    private static Scanner input;

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length >= 1) {
            input = new Scanner(new File(args[0]));
        } else {
            input = new Scanner(new File("input.txt"));
        }
        int cases = Integer.parseInt(input.nextLine());
        input.nextLine();
        for (int i = 0; i < cases; i++) {

            int numCands = Integer.parseInt(input.nextLine());
            String[] names = setNames(numCands);
            ArrayList<LinkedList<Integer>> ballots = setBallots();
            findWinners(numCands, names, ballots);
            if (i + 1 < cases) {
                System.out.println();
            }

        }

    }

    private static ArrayList<LinkedList<Integer>> setBallots() {

        ArrayList<LinkedList<Integer>> ballots = new ArrayList<>();
        while (true) {

            try {

                String voteLine = input.nextLine();
                if (voteLine.equals("")) {
                    break;
                }

                LinkedList<Integer> ballot = new LinkedList<>();
                for (String vote : voteLine.split(" ")) {
                    ballot.add(Integer.parseInt(vote));
                }
                ballots.add(ballot);

            } catch (Exception e) {
                break;
            }

        }
        return ballots;

    }

    private static String[] setNames(int numCands) {

        String[] names = new String[numCands];
        for (int i = 0; i < numCands; i++) {
            names[i] = input.nextLine();
        }
        return names;

    }

    private static void findWinners(int numCands, String[] names, ArrayList<LinkedList<Integer>> ballots) {

        int wins = (int) Math.ceil(ballots.size() / 2.0);
        int winner = -1;
        boolean eliminations[] = new boolean[numCands];
        for (int i = 0; i < numCands; i++) {
            eliminations[i] = false;
        }

        while (winner == -1) {

            int votes[] = new int[numCands];
            for (LinkedList<Integer> ballot : ballots) {

                int vote = ballot.peek() - 1;
                if (!eliminations[vote]) {
                    votes[vote]++;
                }

            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < numCands; i++) {

                if (votes[i] > max) {

                    max = votes[i];
                    if (votes[i] > wins) {
                        winner = i;
                    }

                } else if (votes[i] == max) {
                    winner = -1;
                }

                if (votes[i] < min && !eliminations[i]) {
                    min = votes[i];
                }

            }

            if (min == max) {
                break;
            }

            for (int i = 0; i < numCands; i++) {

                if (votes[i] == min) {

                    eliminations[i] = true;
                    for (LinkedList ballot : ballots) {
                        ballot.remove((Object) (i + 1));
                    }

                }

            }

        }

        if (winner != -1) {
            System.out.println(names[winner]);
        } else {

            for (int i = 0; i < numCands; i++) {

                if (!eliminations[i]) {
                    System.out.println(names[i]);
                }

            }

        }

    }

}