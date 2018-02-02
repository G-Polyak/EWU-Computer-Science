package treePackage;

public class Ribbons extends TreeDecorator {

    private Tree tree;

    public Ribbons(Tree tree) {
        this.tree = tree;
    }

    @Override
    public String name() {
        return tree.name() + ", ribbons";
    }

    @Override
    public double cost() {
        return 1 + tree.cost();
    }

}
