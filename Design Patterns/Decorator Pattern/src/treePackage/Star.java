package treePackage;

public class Star extends TreeDecorator {

    private Tree tree;

    public Star(Tree tree) {
        this.tree = tree;
    }

    public static Tree addStar(Tree tree) {

        if (!tree.name().contains("star")) {
            return new Star(tree);
        }
        System.out.println("Tree already has a star!");
        return tree;

    }

    @Override
    public String name() {
        return tree.name() + ", a star";
    }

    @Override
    public double cost() {
        return 4 + tree.cost();
    }

}
