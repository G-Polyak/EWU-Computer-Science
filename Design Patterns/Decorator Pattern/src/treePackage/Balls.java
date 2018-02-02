package treePackage;

public class Balls extends TreeDecorator {

    private Tree tree;
    private final String[] COLORS = {"red", "silver", "blue"};
    private int col;

    public Balls(Tree tree, int col) {
        this.tree = tree;
        this.col = col;
    }

    @Override
    public String name() {
        return tree.name() + ", " + COLORS[col] + " balls";
    }

    @Override
    public double cost() {

        double cost = 0;
        switch (col) {

            case 0: cost = 1; break;
            case 1: cost = 3; break;
            case 2: cost = 2; break;

        }
        return cost + tree.cost();

    }

}
