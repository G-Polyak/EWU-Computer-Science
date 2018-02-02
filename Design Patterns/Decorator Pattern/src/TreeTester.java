import treePackage.*;

public class TreeTester {

    public static void main(String[] args) {

        Tree myTree = new BlueSpruce();
        myTree = Star.addStar(myTree);
        myTree = new Ruffles(myTree);
        myTree = Star.addStar(myTree);
        myTree = new Ruffles(myTree);
        System.out.printf("%s\nCost: %.2f", myTree.name(), myTree.cost());

    }

}
