import shapes.Shape;
import shapes.ShapeFactory;

import java.util.ArrayList;
import java.util.Collections;

public class ShapeTester {

    public static void main(String[] args) {

        ArrayList<Shape> list = new ArrayList<>();
        String c = "Circle";
        String r = "Rectangle";
        String s = "Square";
        list.add(ShapeFactory.makeShape(c, 3));
        list.add(ShapeFactory.makeShape(r, 3, 4));
        list.add(ShapeFactory.makeShape(s, 5));
        list.add(ShapeFactory.makeShape(c, 5));
        list.add(ShapeFactory.makeShape(r, 4, 3));
        list.add(ShapeFactory.makeShape(s, 2));
        list.add(ShapeFactory.makeShape(c, 6));
        list.add(ShapeFactory.makeShape(r, 11, 2));
        list.add(ShapeFactory.makeShape(s, 7));
        list.add(ShapeFactory.makeShape(c, 7));
        list.add(ShapeFactory.makeShape(r, 3, 10));
        list.add(ShapeFactory.makeShape(s, 6));
        printList(list);
        Collections.sort(list);
        printList(list);

    }

    private static void printList(ArrayList<Shape> list) {

        for (Shape s : list) {
            System.out.printf("Name:\t%s\nArea:\t%.4f\n\n", s.name(), s.area());
        }
        System.out.println();

    }

}
