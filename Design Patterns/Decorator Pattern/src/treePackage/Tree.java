package treePackage;

public abstract class Tree {

    private String name;
    private double cost;

    public Tree() {

    }

    public Tree(String name) {
        this.name = name;
        cost = 0;
    }

    public String name() {
        return name + " decorated with";
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public abstract double cost();

}
