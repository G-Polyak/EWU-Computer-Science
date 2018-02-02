package treePackage;

public class LEDs extends TreeDecorator {

    private Tree tree;

    public LEDs(Tree tree) {
        this.tree = tree;
    }

    @Override
    public String name() {
        return tree.name() + ", LEDs";
    }

    @Override
    public double cost() {
        return 10 + tree.cost();
    }

}
