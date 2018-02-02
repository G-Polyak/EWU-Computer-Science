package treePackage;

public class Lights extends TreeDecorator {

    private Tree tree;

    public Lights(Tree tree) {
        this.tree = tree;
    }

    @Override
    public String name() {
        return tree.name() + ", lights";
    }

    @Override
    public double cost() {
        return 5 + tree.cost();
    }

}
