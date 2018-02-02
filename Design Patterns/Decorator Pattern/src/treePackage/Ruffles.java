package treePackage;

public class Ruffles extends TreeDecorator {

    private Tree tree;

    public Ruffles(Tree tree) {
        this.tree = tree;
    }

    @Override
    public String name() {
        return tree.name() + ", ruffles";
    }

    @Override
    public double cost() {
        return 1 + tree.cost();
    }

}
