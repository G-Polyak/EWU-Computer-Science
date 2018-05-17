import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int maxWeight;
    private static Item[] items;
    private static Knapsack[][] bestKnapsack;

    public static void main(String[] args) throws IOException {

        BufferedReader file = new BufferedReader(new FileReader("backpack.in"));
        int backpacks = Integer.parseInt(file.readLine());
        int numItems;
        Scanner s;
        Knapsack sack;
        for (int i = 0; i < backpacks; i++) {

            maxWeight = Integer.parseInt(file.readLine());
            numItems = Integer.parseInt(file.readLine());
            items = new Item[numItems];
            sack = new Knapsack();
            for (int j = 0; j < numItems; j++) {

                s = new Scanner(file.readLine());
                items[j] = new Item(s.nextInt(), s.nextInt());

            }
            Collections.sort(Arrays.asList(items));
            sack = fillKnapsack(sack);
            System.out.println(sack);

        }

    }

    private static Knapsack fillKnapsack(Knapsack knapsack) {

        for (Item item : items) {

            if (knapsack.realWeight + item.weight > maxWeight) continue;
            knapsack.addItem(item);

        }
        if (knapsack.realWeight == maxWeight) return knapsack;
        bestKnapsack = new Knapsack[maxWeight + 1][items.length + 1];
        return findBestCombo(maxWeight, 0);

    }


    private static Knapsack findBestCombo(int w, int n) {

        if (n == items.length || w == 0) {

            bestKnapsack[w][n] = new Knapsack();
            return bestKnapsack[w][n];

        }
        if (w < items[n].weight) {
            return (bestKnapsack[w][n + 1] == null) ? findBestCombo(w, n + 1) : bestKnapsack[w][n + 1];
        }
        Knapsack sackA = (bestKnapsack[w - items[n].weight][n + 1] == null) ?
                new Knapsack(findBestCombo(w - items[n].weight, n + 1))
                : new Knapsack(bestKnapsack[w - items[n].weight][n + 1]);

        sackA.addItem(items[n]);
        Knapsack sackB = (bestKnapsack[w][n + 1] == null) ?
                new Knapsack(findBestCombo(w, n + 1))
                : new Knapsack(bestKnapsack[w][n + 1]);

        if (sackA.benefit > sackB.benefit) {

            bestKnapsack[w][n] = new Knapsack(sackA);
            return sackA;

        }
        bestKnapsack[w][n] = new Knapsack(sackB);
        return sackB;

    }

}