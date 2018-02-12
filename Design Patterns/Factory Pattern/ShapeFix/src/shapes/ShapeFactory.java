package shapes;

public class ShapeFactory {

    public static Shape makeShape(String name, double... parms) {

        if (parms.length > 2) {
            throw new IllegalArgumentException("Too many parms");
        }
        name = name.toLowerCase();
        switch (name) {

            case "circle":
                return new Circle(parms[0]);
            case "square":
                return new Square(parms[0]);
            case "rectangle":
                return new Rectangle(parms[0], parms[1]);
            default:
                throw new IllegalArgumentException("Bad parms");

        }

    }

}
