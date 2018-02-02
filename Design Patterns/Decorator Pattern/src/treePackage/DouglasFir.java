package treePackage;

public class DouglasFir extends Tree {

    public DouglasFir() {
        super("Douglas Fir");
        setStar(false);
    }

    @Override
    public double cost() {
        return 30;
    }

}