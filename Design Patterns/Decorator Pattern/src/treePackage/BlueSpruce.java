package treePackage;

public class BlueSpruce extends Tree {

    public BlueSpruce() {
        super("Colorado Blue Spruce");
        setStar(false);
    }

    @Override
    public double cost() {
        return 50;
    }

}
