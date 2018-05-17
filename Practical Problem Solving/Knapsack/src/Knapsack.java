import java.util.ArrayList;

public class Knapsack {

    private final ArrayList<Item> items;
    int realWeight;
    int benefit;

    Knapsack() {

        items = new ArrayList<>();
        realWeight = 0;
        benefit = 0;

    }

    Knapsack(Knapsack b) {

        items = new ArrayList<>();
        realWeight = 0;
        benefit = 0;
        b.items.forEach(this::addItem);

    }

    public String toString() {

        return "Knapsack weight: " + Main.maxWeight +
                ", Loaded weight: " + realWeight +
                ", Benefit: " + benefit;

    }

    void addItem(Item item) {

        items.add(item);
        realWeight += item.weight;
        benefit += item.benefit;

    }

}