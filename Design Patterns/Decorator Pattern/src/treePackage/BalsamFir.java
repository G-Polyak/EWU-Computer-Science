package treePackage;

public class BalsamFir extends Tree {

    public BalsamFir() {
        super("Balsam Fir");
        setStar(false);
    }

    @Override
    public double cost() {
        return 25;
    }

}
