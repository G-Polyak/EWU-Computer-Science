//George Polyak
//Day Class

public class Day {

    private int dayNum;

    public Day() {
        this.dayNum = 1;
    }

    public Day(int dayNum) {
        this.dayNum = dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getDayNum() {
        return this.dayNum;
    }

    @Override
    public String toString() {
        return "" + this.dayNum;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        } else if(!(obj instanceof Day)) {
            return false;
        }
        Day that = (Day) obj;
        if(this.dayNum == that.dayNum) {
            return true;
        }
        return false;

    }

}