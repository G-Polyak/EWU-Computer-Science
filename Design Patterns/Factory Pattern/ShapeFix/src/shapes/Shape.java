package shapes;

public abstract class Shape implements Comparable<Shape> {

    public abstract String name();

    public abstract double area();

    @Override
    public int compareTo(Shape o) {

        int ret = name().compareTo(o.name());
        if (ret == 0) {
            return (int) (area() - o.area());
        }
        return ret;

    }

}
