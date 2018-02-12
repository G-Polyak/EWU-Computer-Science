package shapes;

class Square extends Shape {

    private double length;

    Square(double length) {
        this.length = length;
    }

    @Override
    public String name() {
        return "Square";
    }

    @Override
    public double area() {
        return length * length;
    }

}
