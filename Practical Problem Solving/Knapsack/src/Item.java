public class Item implements Comparable {

    private final double value;
    final int weight;
    final int benefit;

    Item(int weight, int benefit) {

        this.weight = weight;
        this.benefit = benefit;
        value = (double) benefit / weight;

    }

    @Override
    public int compareTo(Object o) {

        Item that = (Item) o;
        if (this.value == that.value) return 0;
        if ((this.value - that.value) < 0) return 1;
        return -1;

    }

}