//George Polyak

public class Point {

    public int x;
    public int y;

    public Point() {

        this.x = 0;
        this.y = 0;

    }

    public Point(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {

            Point that = (Point) obj;
            return this.getX() == that.getX() && this.getY() == that.getY();

        }

        return false;

    }

}