package shapes;

class Rectangle extends Shape {

    private double length;

    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public String name() {
        return "Rectangle";
    }

    @Override
    public double area() {
        return length * width;
    }

}
