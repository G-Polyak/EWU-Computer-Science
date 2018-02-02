package treePackage;

public class FraserFir extends Tree {

    public FraserFir() {
        super("Fraser Fir");
        setStar(false);
    }

    @Override
    public double cost() {
        return 35;
    }

}
